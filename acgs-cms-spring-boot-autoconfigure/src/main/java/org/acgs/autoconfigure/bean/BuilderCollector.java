package org.acgs.autoconfigure.bean;

import org.acgs.autoconfigure.annotation.AcgsBuild;
import org.acgs.autoconfigure.configuration.AcgsCmsBuildProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * 自动构建注解信息采集器
 *
 * @author John@acgs-org
 * create time 2021/4/26
 */
@Component
@EnableConfigurationProperties(AcgsCmsBuildProperties.class)
public class BuilderCollector implements BeanPostProcessor {

    @Autowired
    AcgsCmsBuildProperties build;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        AcgsBuild acgsBuildAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), AcgsBuild.class);
        // 非自定义注解类，直接返回
        if (acgsBuildAnnotation == null) {
            return bean;
        }
        System.out.println("=====自定义注解生效=====");
        System.out.println("Bean 加载: " + bean.getClass().getName());
        System.out.println("=====properties配置=====");
        System.out.println("acgs.build.path: " + build.getPath());
        System.out.println("acgs.build.driver: " + build.getDriver());
        System.out.println("acgs.build.all: " + build.isAll());
        return bean;
    }
}
