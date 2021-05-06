package com.github.acgs.core.constant;

import java.io.File;

/**
 * 自动构建常量类
 *
 * @author John@acgs-org
 * create time 2021/4/27
 */
public class BuilderConstant {

    public static final String SPACE = " ";

    public static final String SPACE_4 = "    ";

    public static final String SPACE_8 = SPACE_4 + SPACE_4;

    public static final String SEPARATOR = File.separator;

    public static final String COMMON_PATH = System.getProperty("user.dir")
            + SEPARATOR + "src" + SEPARATOR + "main" + SEPARATOR + "java" + SEPARATOR;

}
