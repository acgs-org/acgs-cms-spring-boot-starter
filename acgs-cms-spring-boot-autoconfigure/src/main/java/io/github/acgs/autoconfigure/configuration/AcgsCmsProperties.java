package io.github.acgs.autoconfigure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * acgs 配置文件
 *
 * @author John@acgs-org
 * create time is 2021/4/26
 */
@ConfigurationProperties("acgs.cms")
public class AcgsCmsProperties {

    private static final String[] DEFAULT_EXCLUDE_METHODS = new String[]{"OPTIONS"};

    private String[] excludeMethods = DEFAULT_EXCLUDE_METHODS;

    private String tokenSecret = "";

    private Long tokenAccessExpire = 3600L;

    private Long tokenRefreshExpire = 2592000L;

    public AcgsCmsProperties() {
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public Long getTokenAccessExpire() {
        return tokenAccessExpire;
    }

    public void setTokenAccessExpire(Long tokenAccessExpire) {
        this.tokenAccessExpire = tokenAccessExpire;
    }

    public Long getTokenRefreshExpire() {
        return tokenRefreshExpire;
    }

    public void setTokenRefreshExpire(Long tokenRefreshExpire) {
        this.tokenRefreshExpire = tokenRefreshExpire;
    }

    public String[] getExcludeMethods() {
        return excludeMethods;
    }

    public void setExcludeMethods(String[] excludeMethods) {
        this.excludeMethods = excludeMethods;
    }
}
