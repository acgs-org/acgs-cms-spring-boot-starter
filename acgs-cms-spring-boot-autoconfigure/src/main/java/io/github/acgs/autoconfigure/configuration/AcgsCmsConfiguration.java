package io.github.acgs.autoconfigure.configuration;

import io.github.acgs.autoconfigure.bean.TemplateBuilder;
import io.github.acgs.autoconfigure.bean.Templates;
import io.github.acgs.autoconfigure.util.TemplateUtil;
import io.github.acgs.core.token.DoubleJWT;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * acgs cms 配置文件
 *
 * @author John@acgs-org
 * create time 2021/4/26
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties({AcgsCmsProperties.class, AcgsCmsBuildProperties.class})
public class AcgsCmsConfiguration {

    private final AcgsCmsProperties properties;

    private final AcgsCmsBuildProperties buildProperties;

    public AcgsCmsConfiguration(AcgsCmsProperties properties, AcgsCmsBuildProperties buildProperties) {
        this.properties = properties;
        this.buildProperties = buildProperties;
    }

    /**
     * @return jwt bean
     */
    @Bean
    public DoubleJWT jwt() {
        String secret = properties.getTokenSecret();
        Long accessExpire = properties.getTokenAccessExpire();
        Long refreshExpire = properties.getTokenRefreshExpire();
        if (accessExpire == null) {
            // 一小时
            accessExpire = 60 * 60L;
        }
        if (refreshExpire == null) {
            // 一周
            refreshExpire = 60 * 60 * 24 * 7L;
        }
        return new DoubleJWT(secret, accessExpire, refreshExpire);
    }

    @Bean
    public Templates templates() {
        Map<String, String> template = new HashMap<>();
        template.put("entity", TemplateUtil.getTemplate("entity"));
        template.put("mongo-repository", TemplateUtil.getTemplate("mongo-repository"));
        template.put("mongo-controller", TemplateUtil.getTemplate("mongo-controller"));
        template.put("controller-get-item", TemplateUtil.getComponent("controller-get-item"));
        template.put("controller-post-item", TemplateUtil.getComponent("controller-post-item"));
        return new Templates(template);
    }

    @Bean
    public TemplateBuilder builder() {
        String basePath = buildProperties.getBasePath();
        String driverType = buildProperties.getDriverType();
        boolean buildAll = buildProperties.isBuildAll();
        return new TemplateBuilder(basePath, driverType, buildAll, templates());
    }

}