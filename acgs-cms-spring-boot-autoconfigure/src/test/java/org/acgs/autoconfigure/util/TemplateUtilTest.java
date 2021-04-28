package org.acgs.autoconfigure.util;

import org.junit.jupiter.api.Test;

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
}