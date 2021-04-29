package org.acgs.autoconfigure.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import org.acgs.core.constant.BuilderConstant;

/**
 * 模版工具类
 *
 * @author John@acgs-org
 * create time 2021/4/27
 */
public class TemplateUtil {

    /**
     * @param name 模版名称
     * @return String 表示的模版字符串
     */
    public static String getTemplate(String name) {
        return IoUtil.read(new ClassPathResource(BuilderConstant.SEPARATOR + "template" + BuilderConstant.SEPARATOR + name + ".template").getStream()).toString();
    }

    /**
     * @param name 组件名称
     * @return String 表示的组件字符串
     */
    public static String getComponent(String name) {
        return IoUtil.read(new ClassPathResource(BuilderConstant.SEPARATOR + "template" + BuilderConstant.SEPARATOR + "component" + BuilderConstant.SEPARATOR + name + ".template").getStream()).toString();
    }

}
