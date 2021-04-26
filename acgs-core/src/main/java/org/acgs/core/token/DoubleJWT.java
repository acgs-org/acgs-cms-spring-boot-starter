package org.acgs.core.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.acgs.core.constant.TokenConstant;
import org.acgs.core.util.DateUtil;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 双令牌验证模式
 *
 * @Author John@acgs-org
 * @Date 2021/4/26
 */
public class DoubleJWT {

    /**
     * 加密算法
     */
    private Algorithm algorithm;

    /**
     * 过期时间
     */
    private long accessExpire;
    private long refreshExpire;

    /**
     * 令牌构建器
     */
    private JWTCreator.Builder builder;

    /**
     * 令牌验证器
     */
    private JWTVerifier accessVerifier;
    private JWTVerifier refreshVerifier;

    /**
     * 双 token 模式
     * 传入加密算法
     *
     * @param algorithm     加密算法
     * @param accessExpire  access_token 过期时间
     * @param refreshExpire refresh_token 过期时间
     */
    public DoubleJWT(Algorithm algorithm, long accessExpire, long refreshExpire) {
        this.algorithm = algorithm;
        this.accessExpire = accessExpire;
        this.refreshExpire = refreshExpire;
        this.initBuilderAndVerifier();
    }

    /**
     * 双 token 模式
     * 不传入加密算法
     * 默认采用 HMAC256 加密算法
     *
     * @param secret        密匙
     * @param accessExpire  access_token 过期时间
     * @param refreshExpire refresh_token 过期时间
     */
    public DoubleJWT(String secret, long accessExpire, long refreshExpire) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.accessExpire = accessExpire;
        this.refreshExpire = refreshExpire;
        this.initBuilderAndVerifier();
    }

    /**
     * 创建 token
     *
     * @param tokenType 令牌类型
     * @param identity  身份
     * @param scope     范围
     * @param expire    过期时间，单位 s
     * @return
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

    public String generateToken(String tokenType, String identity, String scope, long expire) {
        Date expireDate = DateUtil.getDurationDate(expire);
        return builder
                .withClaim("type", tokenType)
                .withClaim("identity", identity)
                .withClaim("scope", scope)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    public Map<String, Claim> decodeAccessToken(String token) {
        DecodedJWT jwt = accessVerifier.verify(token);
        checkTokenType(jwt.getClaim("type").asString(), TokenConstant.ACCESS_TYPE);
        checkTokenScope(jwt.getClaim("scope").asString());
        checkTokenExpired(jwt.getExpiresAt());
        return jwt.getClaims();
    }

    public Map<String, Claim> decodeRefreshToken(String token) {
        DecodedJWT jwt = refreshVerifier.verify(token);
        checkTokenType(jwt.getClaim("type").asString(), TokenConstant.REFRESH_TYPE);
        checkTokenScope(jwt.getClaim("scope").asString());
        checkTokenExpired(jwt.getExpiresAt());
        return jwt.getClaims();
    }

    private void checkTokenType(String type, String accessType) {
        if (!Objects.equals(type, accessType)) {
            throw new InvalidClaimException("token type is invalid");
        }
    }

    private void checkTokenScope(String scope) {
        if (!Objects.equals(TokenConstant.SCOPE, scope)) {
            throw new InvalidClaimException("token scope is invalid");
        }
    }

    private void checkTokenExpired(Date expireAt) {
        long now = System.currentTimeMillis();
        if (expireAt.getTime() < now) {
            throw new TokenExpiredException("token is expire");
        }
    }

    public String generateAccessToken(String identity) {
        return generateToken(TokenConstant.ACCESS_TYPE, identity, TokenConstant.SCOPE, this.accessExpire);
    }

    public String generateAccessToken(long identity) {
        return generateToken(TokenConstant.ACCESS_TYPE, identity, TokenConstant.SCOPE, this.accessExpire);
    }

    public String generateRefreshToken(String identity) {
        return generateToken(TokenConstant.REFRESH_TYPE, identity, TokenConstant.SCOPE, this.refreshExpire);
    }

    public String generateRefreshToken(long identity) {
        return generateToken(TokenConstant.REFRESH_TYPE, identity, TokenConstant.SCOPE, this.refreshExpire);
    }

    public Tokens generateTokens(long identity) {
        String access = this.generateAccessToken(identity);
        String refresh = this.generateRefreshToken(identity);
        return new Tokens(access, refresh);
    }

    public Tokens generateTokens(String identity) {
        String access = this.generateAccessToken(identity);
        String refresh = this.generateRefreshToken(identity);
        return new Tokens(access, refresh);
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public long getAccessExpire() {
        return accessExpire;
    }

    public long getRefreshExpire() {
        return refreshExpire;
    }

    public JWTCreator.Builder getBuilder() {
        return builder;
    }

    public JWTVerifier getAccessVerifier() {
        return accessVerifier;
    }

    public JWTVerifier getRefreshVerifier() {
        return refreshVerifier;
    }

    private void initBuilderAndVerifier() {
        accessVerifier = JWT.require(algorithm)
                .acceptExpiresAt(accessExpire)
                .build();
        refreshVerifier = JWT.require(algorithm)
                .acceptExpiresAt(refreshExpire)
                .build();
        builder = JWT.create();
    }

}
