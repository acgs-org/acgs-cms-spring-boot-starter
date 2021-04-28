package org.acgs.autoconfigure.bean;

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

    public Map<String, String> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, String> templates) {
        this.templates = templates;
    }
}
