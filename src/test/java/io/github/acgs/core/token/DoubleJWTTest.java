package io.github.acgs.core.token;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import io.github.acgs.core.constant.TokenConstant;
import junit.framework.TestCase;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author John@acgs-org
 * create time is 2021/4/26
 */
public class DoubleJWTTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(DoubleJWT.class);

    public void testGenerateToken() {
        DoubleJWT jwt = new DoubleJWT("secret", 1000, 2000);
        String token = jwt.generateToken("test", 1, "test", 1000);
        assertNotNull(token);
        log.info(token);
    }

    public void testTestGenerateToken() {
        DoubleJWT jwt = new DoubleJWT("secret", 10000, 20000);
        String token = jwt.generateAccessToken(1);
        assertNotNull(token);
        log.info(token);
    }

    public void testDecodeAccessToken() {
        DoubleJWT jwt = new DoubleJWT("secret", 10000, 20000);
        String token = jwt.generateAccessToken(1);
        assertNotNull(token);
        log.info(token);
        Map<String, Claim> claimMap = jwt.decodeAccessToken(token);
        Assert.assertEquals(TokenConstant.SCOPE, claimMap.get("scope").asString());
        Assert.assertEquals(TokenConstant.ACCESS_TYPE, claimMap.get("type").asString());
    }

    public void testDecodeRefreshToken() {
        DoubleJWT jwt = new DoubleJWT("secret", 10000, 20000);
        String token = jwt.generateRefreshToken(1);
        assertNotNull(token);
        log.info(token);
        Map<String, Claim> claimMap = jwt.decodeRefreshToken(token);
        Assert.assertEquals(TokenConstant.SCOPE, claimMap.get("scope").asString());
        Assert.assertEquals(TokenConstant.REFRESH_TYPE, claimMap.get("type").asString());
    }

    public void testGenerateAccessToken() {
        DoubleJWT jwt = new DoubleJWT("secret", 10000, 20000);
        String token = jwt.generateAccessToken(1);
        assertNotNull(token);
        log.info(token);
    }

    public void testGenerateTokens() {
        DoubleJWT jwt = new DoubleJWT("secret", 10000, 20000);
        Tokens tokens = jwt.generateTokens(1);
        assertNotNull(tokens.getAccessToken());
        assertNotNull(tokens.getRefreshToken());
        log.info("{}", tokens);

        Map<String, Claim> claimMap = jwt.decodeAccessToken(tokens.getAccessToken());
        Assert.assertEquals(TokenConstant.SCOPE, claimMap.get("scope").asString());
        Assert.assertEquals(TokenConstant.ACCESS_TYPE, claimMap.get("type").asString());
    }

    public void testTestGenerateTokens() {
        DoubleJWT jwt = new DoubleJWT("secret", 10000, 20000);
        Tokens tokens = jwt.generateTokens("color");
        assertNotNull(tokens.getAccessToken());
        assertNotNull(tokens.getRefreshToken());
        log.info("{}", tokens);

        Map<String, Claim> claimMap = jwt.decodeAccessToken(tokens.getAccessToken());
        Assert.assertEquals(TokenConstant.SCOPE, claimMap.get("scope").asString());
        Assert.assertEquals(TokenConstant.ACCESS_TYPE, claimMap.get("type").asString());
    }

    public void testGetAlgorithm() {
        DoubleJWT jwt = new DoubleJWT("secret", 1000, 2000);
        assertNotNull(jwt.getAlgorithm());
    }

    public void testGetAccessExpire() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        DoubleJWT jwt = new DoubleJWT(algorithm, 1000, 2000);
        assertEquals(1000L, jwt.getAccessExpire());
    }

    public void testGetRefreshExpire() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        DoubleJWT jwt = new DoubleJWT(algorithm, 1000, 2000);
        assertEquals(2000, jwt.getRefreshExpire());
    }

    public void testGetBuilder() {
        DoubleJWT jwt = new DoubleJWT("secret", 1000, 2000);
        assertNotNull(jwt.getBuilder());
    }

    public void testGetAccessVerifier() {
        DoubleJWT jwt = new DoubleJWT("secret", 1000, 2000);
        assertNotNull(jwt.getAccessVerifier());
    }

    public void testGetRefreshVerifier() {
        DoubleJWT jwt = new DoubleJWT("secret", 1000, 2000);
        assertNotNull(jwt.getRefreshVerifier());
    }
}