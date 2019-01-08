package com.shtel.secure.platform.login.aciton;

import com.shtel.secure.platform.login.model.User;
import com.shtel.secure.platform.login.model.mapper.UserMapper;
import com.shtel.secure.platform.enumType.model.EnumType;
import com.shtel.secure.utils.ResultUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: chenwq
 * @Date: 2018/12/27
 * @Description: 登陆接口
 */
@RestController
@RequestMapping("/login")
public class LoginAction {
    private static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/loginAuth")
    public String loginAuth(HttpServletRequest request, HttpServletResponse response) {
        logger.info("请登录账号");
        return ResultUtil.Result(EnumType.UNAUTH);
    }

    @PostMapping("/loginIndex")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response) {
        User user=new User();
        if (account == null || "".equals(account) || password == null || "".equals(password))
            return ResultUtil.Result(EnumType.LOGINFAIL);
        user.setAccount(account);
        User result=userMapper.selectOne(user);
        if (account.equals(result.getAccount()) || password.equals(result.getPassword())) {
            request.getSession().setAttribute("USERID", result.getId());
            request.getSession().setMaxInactiveInterval(60*30);
            logger.info("登录成功");
            return ResultUtil.Result(EnumType.SUCCESS);
        }
        return ResultUtil.Result(EnumType.LOGINFAIL);
    }

    @PostMapping("/quit")
    public String quit(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("USERACCOUNT");
        return ResultUtil.Result(EnumType.QUIT);
    }

    @PostMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(userMapper.selectByPrimaryKey(1));
    }


}
