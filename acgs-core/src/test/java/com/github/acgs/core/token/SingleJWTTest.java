package com.github.acgs.core.token;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.github.acgs.core.util.DateUtil;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * @author John@acgs-org
 * create time is 2021/4/26
 */
public class SingleJWTTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(SingleJWT.class);

    public void testGenerateToken() {
        SingleJWT jwt = new SingleJWT("secret", 1000);
        String token = jwt.generateToken("test", 1, "test", 1000);
        assertNotNull(token);
        log.info(token);
    }

    public void testGetSpecifyToken() {
        SingleJWT jwt = new SingleJWT("secret", 1000);
        String token = jwt.getBuilder()
                .withClaim("type", "test")
                .withClaim("identity", 1)
                .withClaim("scope", "test")
                .withExpiresAt(DateUtil.getDurationDate(10000))
                .sign(jwt.getAlgorithm());
        assertNotNull(token);
        log.info(token);
    }

    public void testDecodeToken() {
        SingleJWT jwt = new SingleJWT("secret", 1000);
        String token = jwt.getBuilder()
                .withClaim("type", "test")
                .withClaim("identity", 1)
                .withClaim("scope", "test")
                .withExpiresAt(DateUtil.getDurationDate(10000))
                .sign(jwt.getAlgorithm());
        assertNotNull(token);
        log.info(token);
        Map<String, Claim> claimMap = jwt.decodeToken(token);
        log.info("{}", claimMap);
        assertEquals(claimMap.get("type").asString(), "test");
        assertEquals(claimMap.get("scope").asString(), "test");
    }

    public void testGetAlgorithm() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        SingleJWT jwt = new SingleJWT(algorithm, 1000);
        assertNotNull(jwt.getAlgorithm());
    }

    public void testGetExpire() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        SingleJWT jwt = new SingleJWT(algorithm, 1000);
        assertEquals(1000, jwt.getExpire());
    }

    public void testGetBuilder() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        SingleJWT jwt = new SingleJWT(algorithm, 1000);
        assertNotNull(jwt.getBuilder());
    }

    public void testGetVerifier() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        SingleJWT jwt = new SingleJWT(algorithm, 1000);
        assertNotNull(jwt.getVerifier());
    }
}