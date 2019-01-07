package com.shtel.secure.platform.login.model.mapper;

import com.shtel.secure.platform.login.model.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/7 13:35
 * @Description: UserMapper接口
 */
@Component
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {
}
