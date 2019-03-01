package com.shtel.secure.config.metadata;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 任务下发自定义配置
 *
 * @author antking
 * @date 2019-03-01 14:48
 */
@Component
@ConfigurationProperties(prefix = "props")
public class PropsConfig {
    private String tempURL;
    private String periodURL;
    private String loginAuthURL;
    private String progressTempURL;

    public String getTempURL() {
        return tempURL;
    }

    public void setTempURL(String tempURL) {
        this.tempURL = tempURL;
    }

    public String getPeriodURL() {
        return periodURL;
    }

    public void setPeriodURL(String periodURL) {
        this.periodURL = periodURL;
    }

    public String getLoginAuthURL() {
        return loginAuthURL;
    }

    public void setLoginAuthURL(String loginAuthURL) {
        this.loginAuthURL = loginAuthURL;
    }

    public String getProgressTempURL() {
        return progressTempURL;
    }

    public void setProgressTempURL(String progressTempURL) {
        this.progressTempURL = progressTempURL;
    }
}
