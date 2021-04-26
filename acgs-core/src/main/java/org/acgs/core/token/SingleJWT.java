package org.acgs.core.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.acgs.core.util.DateUtil;

import java.util.Date;
import java.util.Map;

/**
 * 单令牌验证模式
 *
 * @author John@acgs-org
 * create time is 2021/4/26
 */
public class SingleJWT {

    /**
     * 加密算法
     */
    private Algorithm algorithm;

    /**
     * 过期时间
     */
    private long expire;

    /**
     * 令牌构建器
     */
    private JWTCreator.Builder builder;

    /**
     * 令牌验证器
     */
    private JWTVerifier verifier;

    /**
     * 单 token 模式
     * 传入加密算法
     *
     * @param algorithm 加密算法
     * @param expire    token 过期时间
     */
    public SingleJWT(Algorithm algorithm, long expire) {
        this.algorithm = algorithm;
        this.expire = expire;
        this.initBuilderAndVerifier();
    }

    /**
     * 单 token 模式
     * 不传入加密算法
     * 默认采用 HMAC256 加密算法
     *
     * @param secret 密匙
     * @param expire token 过期时间
     */
    public SingleJWT(String secret, long expire) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.expire = expire;
        this.initBuilderAndVerifier();
    }

    /**
     * 创建 token
     *
     * @param tokenType 令牌类型
     * @param identity  身份
     * @param scope     范围
     * @param expire    过期时间，单位 s
     */
    public String generateToken(String tokenType, long identity, String scope, long expire) {
        Date expireDate = DateUtil.getDurationDate(expire);
        return builder
                .withClaim("type", tokenType)
                .withClaim("identity", identity)
                .withClaim("scope", scope)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    /**
     * 验证 token
     *
     * @param token 待验证 token
     */
    public Map<String, Claim> decodeToken(String token) {
        DecodedJWT jwt = verifier.verify(token);
        checkTokenExpired(jwt.getExpiresAt());
        return jwt.getClaims();
    }

    /**
     * 验证 token 过期
     *
     * @param expireAt 到期时间
     */
    private void checkTokenExpired(Date expireAt) {
        long now = System.currentTimeMillis();
        if (expireAt.getTime() < now) {
            throw new TokenExpiredException("token is expire");
        }
    }

    /**
     * 获得加密算法
     *
     * @return Algorithm
     */
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * 获取过期时间
     *
     * @return long
     */
    public long getExpire() {
        return expire;
    }

    /**
     * 获取令牌构造器
     *
     * @return Builder
     */
    public JWTCreator.Builder getBuilder() {
        return builder;
    }

    /**
     * 获取令牌验证器
     *
     * @return JWTVerifier
     */
    public JWTVerifier getVerifier() {
        return verifier;
    }

    private void initBuilderAndVerifier() {
        verifier = JWT.require(algorithm)
                .acceptExpiresAt(expire)
                .build();
        builder = JWT.create();
    }
}
