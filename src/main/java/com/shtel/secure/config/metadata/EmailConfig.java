package com.shtel.secure.config.metadata;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * email自定义配置
 *
 * @author antking
 * @date 2019-03-01 15:16
 */
@Component
@ConfigurationProperties(prefix = "email")
public class EmailConfig {
    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
