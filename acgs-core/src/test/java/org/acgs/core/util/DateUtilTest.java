package org.acgs.core.util;

import junit.framework.TestCase;

import java.util.Date;


/**
 * @Author John@acgs-org
 * @Date 2021/4/26
 */
public class DateUtilTest extends TestCase {

    public void testGetDurationDate() {
        Date duration = DateUtil.getDurationDate(10);
        long now = new Date().getTime();
        assertTrue(duration.getTime() > now);
        assertTrue(duration.getTime() - now > 5 * 1000);
        assertTrue(duration.getTime() - now <= 10 * 1000);
    }
}
