package org.acgs.core.util;

import org.acgs.core.constant.BuilderConstant;

/**
 * 构建工具类
 * 定义了一系列通用方法
 *
 * @author John@acgs-org
 * create time 2021/4/27
 */
public class StringUtil {

    /**
     * 字符串首字母大写
     */
    public static String toUpper(String str) {
        if (str.isEmpty()) {
            return str;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将包路径的 '.' 分隔符 转化为平台相关的 path 分隔符
     * 例: com.example -> com/example
     *
     * @param base 包路径
     */
    public static String BaseToPath(String base) {
        return base.replace(".", BuilderConstant.SEPARATOR);
    }

}
