package org.acgs.autoconfigure.bean;

import org.acgs.autoconfigure.util.TemplateUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author John@acgs-org
 * create time 2021/4/30
 */
class TemplateBuilderTest {

    @Test
    void build() {
        Map<String, String> template = new HashMap<>();
        template.put("entity", TemplateUtil.getTemplate("entity"));
        template.put("mongo-repository", TemplateUtil.getTemplate("mongo-repository"));
        template.put("mongo-controller", TemplateUtil.getTemplate("mongo-controller"));
        template.put("controller-get-item", TemplateUtil.getComponent("controller-get-item"));
        template.put("controller-post-item", TemplateUtil.getComponent("controller-post-item"));
        Templates templates = new Templates(template);

        Entity entity = new Entity();
        entity.setId("1");
        entity.setName("book");
        Map<String, String> values = new HashMap<>();
        values.put("id", "Long");
        values.put("name", "String");
        entity.setValues(values);
        List<String> methods = new ArrayList<>();
        methods.add("GET");
        methods.add("POST");
        entity.setMethods(methods);

        TemplateBuilder builder = new TemplateBuilder("org.acgs.autoconfigure.test", "mongo", true, templates);
        builder.build(entity);
    }
}