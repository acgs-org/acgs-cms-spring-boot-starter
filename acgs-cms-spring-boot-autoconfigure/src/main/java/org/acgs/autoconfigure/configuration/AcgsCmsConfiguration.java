package org.acgs.autoconfigure.configuration;

import org.acgs.core.token.DoubleJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * acgs cms 配置文件
 *
 * @author John@acgs-org
 * create time 2021/4/26
 */
@Configuration(proxyBeanMethods = false)
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(AcgsCmsProperties.class)
public class AcgsCmsConfiguration {

    @Autowired
    private AcgsCmsProperties properties;

    /**
     * @return jwt bean
     */
    @Bean
    public DoubleJWT jwt() {
        String secret = properties.getTokenSecret();
        Long accessExpire = properties.getTokenAccessExpire();
        Long refreshExpire = properties.getTokenRefreshExpire();
        if (accessExpire == null) {
            // 一个小时
            accessExpire = 60 * 60L;
        }
        if (refreshExpire == null) {
            // 一周
            refreshExpire = 60 * 60 * 24 * 7L;
        }
        return new DoubleJWT(secret, accessExpire, refreshExpire);
    }

}