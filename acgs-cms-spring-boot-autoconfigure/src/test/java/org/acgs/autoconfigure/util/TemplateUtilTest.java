package org.acgs.autoconfigure.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
        assertNotNull(template);
        for (String str : template.values()) {
            System.out.println(str);
            System.out.println("==========");
        }
    }
}