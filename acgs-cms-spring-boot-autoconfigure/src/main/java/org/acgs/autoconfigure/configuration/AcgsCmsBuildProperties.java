package org.acgs.autoconfigure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author John@acgs-org
 * create time 2021/4/26
 */
@ConfigurationProperties("acgs.cms.build")
public class AcgsCmsBuildProperties {

    private String path;

    private String driver;

    private boolean all;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }
}
