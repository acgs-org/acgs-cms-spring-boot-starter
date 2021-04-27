package org.acgs.core.util;

import junit.framework.TestCase;
import org.acgs.core.constant.BuilderConstant;

/**
 * @author John@acgs-org
 * create time 2021/4/27
 */
public class BuilderUtilTest extends TestCase {

    public void testToUpper() {
        assertEquals("Class", BuilderUtil.toUpper("class"));
    }

    public void testBaseToPath() {
        String base = "org.acgs.core";
        String path = "org" + BuilderConstant.SEPARATOR + "acgs" + BuilderConstant.SEPARATOR + "core";
        assertEquals(path, BuilderUtil.BaseToPath(base));
    }
}