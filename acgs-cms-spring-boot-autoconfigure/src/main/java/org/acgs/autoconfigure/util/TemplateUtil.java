package org.acgs.autoconfigure.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Map;

import static org.acgs.core.constant.BuilderConstant.*;


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
        return IoUtil.read(new ClassPathResource(SEPARATOR + "template" + SEPARATOR + name + ".template").getStream()).toString();
    }

    /**
     * @param name 组件名称
     * @return String 表示的组件字符串
     */
    public static String getComponent(String name) {
        return IoUtil.read(new ClassPathResource(SEPARATOR + "template" + SEPARATOR + "component" + SEPARATOR + name + ".template").getStream()).toString();
    }

    /**
     * 获取实体类字段
     *
     * @param values 字段 map
     */
    public static String getEntityItem(Map<String, String> values) {
        StringBuilder builder = StrUtil.builder();
        for (String key : values.keySet()) {
            builder.append(SPACE_4).append("private ").append(values.get(key)).append(SPACE).append(key).append(";\n");
        }
        return String.valueOf(builder);
    }

    /**
     * 获取指定接口方法
     *
     * @param methods 接口方法 list
     * @param entity  实体类映射
     */
    public static String getMethodItem(List<String> methods, Map<String, String> entity) {
        StringBuilder builder = StrUtil.builder();

        for (String method : methods) {
            switch (method) {
                case "GET" -> builder.append(StrUtil.format(getComponent("controller-get-item"), entity));
                case "POST" -> builder.append(StrUtil.format(getComponent("controller-post-item"), entity));
            }
        }
        return String.valueOf(builder);
    }

}
