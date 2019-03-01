package com.shtel.secure.config.metadata;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * basic自定义配置
 *
 * @author antking
 * @date 2019-03-01 15:12
 */
@Component
@ConfigurationProperties(prefix = "basic")
public class BasicConfig {
    private String websocURL;
    private String username;
    private String password;

    public String getWebsocURL() {
        return websocURL;
    }

    public void setWebsocURL(String websocURL) {
        this.websocURL = websocURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
