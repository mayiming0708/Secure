package com.shtel.secure.platform.demo.aciton;

import com.shtel.secure.platform.enumType.model.EnumType;
import com.shtel.secure.utils.ResultUtil;
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
    public static final String ACCOUNT="CWQ";
    public static final String PASSWORD="1";

    @PostMapping("/loginAuth")
    public String loginAuth(HttpServletRequest request, HttpServletResponse response){
       return ResultUtil.Result(EnumType.UNAUTH);
    }

    @PostMapping("/loginIndex")
    public String login(@RequestParam("account") String account,@RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response){
        if(account==null||"".equals(account)||password==null||"".equals(password))
            return ResultUtil.Result(EnumType.LOGINFAIL);
        if (account.equals(ACCOUNT)||password.equals(PASSWORD)){
            request.getSession().setAttribute("USERACCOUNT",account);
            request.getSession().setMaxInactiveInterval(60);
            return ResultUtil.Result(EnumType.SUCCESS);
        }
        return ResultUtil.Result(EnumType.LOGINFAIL);
    }

    @PostMapping("/quit")
    public String quit(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("USERACCOUNT");
        return ResultUtil.Result(EnumType.QUIT);
    }



}
