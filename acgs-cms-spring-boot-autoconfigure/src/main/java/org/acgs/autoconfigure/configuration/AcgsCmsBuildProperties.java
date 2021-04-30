package org.acgs.autoconfigure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author John@acgs-org
 * create time 2021/4/26
 */
@ConfigurationProperties("acgs.cms.build")
public class AcgsCmsBuildProperties {

    private String basePath = "module";

    private String driver = "mongo";

    private boolean buildAll = false;

    public AcgsCmsBuildProperties() {
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public boolean isBuildAll() {
        return buildAll;
    }

    public void setBuildAll(boolean buildAll) {
        this.buildAll = buildAll;
    }
}
