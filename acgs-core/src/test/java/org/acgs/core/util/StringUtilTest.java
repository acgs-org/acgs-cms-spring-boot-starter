package org.acgs.core.util;

import junit.framework.TestCase;
import org.acgs.core.constant.BuilderConstant;

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