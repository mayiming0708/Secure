package com.shtel.secure.platform.demo.userModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "userinfo")
@Getter
@Setter
@ToString
public class UserInfo {
    private int id;

    private String username;

    private String password;

}
