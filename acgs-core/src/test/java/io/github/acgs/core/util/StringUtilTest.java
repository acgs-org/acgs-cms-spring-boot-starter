package io.github.acgs.core.util;

import io.github.acgs.core.constant.BuilderConstant;
import junit.framework.TestCase;

/**
 * @author John@acgs-org
 * create time 2021/4/27
 */
public class StringUtilTest extends TestCase {

    public void testToUpper() {
        assertEquals("Class", StringUtil.toUpper("class"));
    }

    public void testBaseToPath() {
        String base = "org.acgs.core";
        String path = "org" + BuilderConstant.SEPARATOR + "acgs" + BuilderConstant.SEPARATOR + "core";
        assertEquals(path, StringUtil.BaseToPath(base));
    }
}