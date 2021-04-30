package org.acgs.autoconfigure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author John@acgs-org
 * create time 2021/5/1
 */
@ConfigurationProperties("acgs.build")
public class AcgsCmsBuildProperties {

    private String basePath = "";

    private String driverType = "mongo";

    private boolean buildAll = false;

    public AcgsCmsBuildProperties() {
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    public boolean isBuildAll() {
        return buildAll;
    }

    public void setBuildAll(boolean buildAll) {
        this.buildAll = buildAll;
    }
}
