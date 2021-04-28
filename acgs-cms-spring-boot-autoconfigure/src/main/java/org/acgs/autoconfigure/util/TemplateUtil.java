package org.acgs.autoconfigure.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import org.acgs.core.constant.BuilderConstant;

/**
 * @author John@acgs-org
 * create time 2021/4/27
 */
public class TemplateUtil {

    public static String getTemplate(String name) {
        return IoUtil.read(new ClassPathResource(BuilderConstant.SEPARATOR + "template" + BuilderConstant.SEPARATOR + name + ".template").getStream()).toString();
    }

}
