package io.github.acgs.core.util;

import java.util.Date;

/**
 * 日期时间工具类
 *
 * @author John@acgs-org
 * create time is 2021/4/26
 */
public class DateUtil {

    /**
     * 获取过期时间
     *
     * @param duration 延时时间，单位 s
     * @return Date
     */
    public static Date getDurationDate(long duration) {
        long expireTime = System.currentTimeMillis() + duration * 1000;
        return new Date(expireTime);
    }

}
