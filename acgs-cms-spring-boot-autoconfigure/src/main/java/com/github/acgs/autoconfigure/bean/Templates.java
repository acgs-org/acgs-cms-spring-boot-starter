package com.github.acgs.autoconfigure.bean;

import java.util.Map;

/**
 * 模版文件集
 *
 * @author John@acgs-org
 * create time 2021/4/27
 */
public class Templates {

    private Map<String, String> templates;

    public Templates() {
    }

    public Templates(Map<String, String> templates) {
        this.templates = templates;
    }

    /**
     * 通过名称获取指定模版
     *
     * @param name 模版名称
     * @return 模版字符串
     */
    public String getTemplate(String name) {
        return templates.get(name);
    }

    public Map<String, String> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, String> templates) {
        this.templates = templates;
    }
}
