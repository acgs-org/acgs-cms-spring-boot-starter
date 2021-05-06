package com.github.acgs.autoconfigure.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author John@acgs-org
 * create time 2021/4/27
 */
class TemplateUtilTest {

    @Test
    void getTemplate() {
        String template = TemplateUtil.getTemplate("entity");
        System.out.println(template);
        assertNotNull(template);
    }

    @Test
    void getTemplates() {
        Map<String, String> template = new HashMap<>();
        template.put("entity", TemplateUtil.getTemplate("entity"));
        template.put("mongo-repository", TemplateUtil.getTemplate("mongo-repository"));
        template.put("mongo-controller", TemplateUtil.getTemplate("mongo-controller"));
        template.put("controller-get-item", TemplateUtil.getComponent("/controller-get-item"));
        template.put("controller-post-item", TemplateUtil.getComponent("controller-post-item"));
        for (String str : template.values()) {
            System.out.println(str);
            System.out.println("==========");
        }
    }

    @Test
    void getComponent() {
        Map<String, String> template = new HashMap<>();
        template.put("get", TemplateUtil.getComponent("controller-get-item"));
        template.put("post", TemplateUtil.getComponent("controller-post-item"));
        for (String str : template.values()) {
            System.out.println(str);
            System.out.println("==========");
        }
    }

    @Test
    void getEntityItem() {
        Map<String, String> values = new HashMap<>();
        values.put("id", "Long");
        values.put("name", "String");
        System.out.println(TemplateUtil.getEntityItem(values));
    }

    @Test
    void getMethodItem() {
        List<String> methods = new ArrayList<>();
        methods.add("GET");
        methods.add("POST");
        Map<String, String> entity = new HashMap<>();
        entity.put("name", "book");
        entity.put("upName", "Book");
        System.out.println(TemplateUtil.getMethodItem(methods, entity));
    }
}