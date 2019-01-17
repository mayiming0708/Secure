package com.shtel.secure.platform.login.service;

import com.shtel.secure.platform.login.model.User;
import com.shtel.secure.platform.login.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
