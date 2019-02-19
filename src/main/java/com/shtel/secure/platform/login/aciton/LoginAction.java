package com.shtel.secure.platform.login.aciton;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.issue.service.IssueService;
import com.shtel.secure.platform.login.model.User;
import com.shtel.secure.platform.login.model.mapper.UserMapper;
import com.shtel.secure.utils.MD5Utils;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: chenwq
 * @Date: 2018/12/27
 * @Description: 登陆接口
 */
@Api(tags = "login-action", description = "登录接口")
@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginAction {
    private static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    @Autowired
    private UserMapper userMapper;


    @ApiOperation(value = "未登录（登录过期）用户返回信息", notes = "用户未登录或登陆过期返回code:100")
    @ApiResponses({
            @ApiResponse(code = 100, message = "请登录账号")
    })
    @RequestMapping("/loginAuth")
    public String loginAuth(HttpServletRequest request, HttpServletResponse response) {
        logger.info("请登录账号");
        return IssueService.Response("请登录账号", 100, new JSONObject()).toJSONString();
    }

    @ApiOperation(value = "登录接口", notes = "用户账号密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account", value = "用户登录账号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "用户登录密码", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 100, message = "登录账号或密码为空"),
            @ApiResponse(code = 101, message = "该用户不存在"),
            @ApiResponse(code = 102, message = "密码错误，请重新输入"),
            @ApiResponse(code = 0, message = "登录成功")
    })
    @PostMapping("/loginIndex")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        User user = new User();
        if (account == null || "".equals(account) || password == null || "".equals(password))
            return IssueService.Response("登录账号或密码为空", 100, new JSONObject()).toJSONString();
        user.setAccount(account);
        User result = userMapper.selectOne(user);
        if (result == null)
            return IssueService.Response("该用户不存在", 100, new JSONObject()).toJSONString();
        user.setPassword(MD5Utils.MD5Encode(password,"utf-8"));
        User result2 = userMapper.selectOne(user);
        if (result2!=null) {
            request.getSession().setAttribute("USERID", result.getId());
            request.getSession().setMaxInactiveInterval(60 * 30);
            JSONObject uesrJSON=new JSONObject();
            uesrJSON.put("USERID",result.getId());
            logger.info("登录成功");
            return IssueService.Response("登录成功", 0, uesrJSON).toJSONString();
        }
        return IssueService.Response("密码错误，请重新输入", 100, new JSONObject()).toJSONString();
    }

    @ApiOperation(value = "登录退出", notes = "用户退出登录")
    @ApiResponses({
            @ApiResponse(code = 0, message = "退出账号成功")
    })
    @GetMapping("/quit")
    public String quit(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return IssueService.Response("退出账号成功", 0, new JSONObject()).toJSONString();
    }

    @PostMapping("/test")
    public void test(@RequestParam("account") String account, @RequestParam("password") String password) {
        User user=new User();
        user.setAccount(account);
        user.setPassword(MD5Utils.MD5Encode(password,"utf-8"));
        userMapper.insert(user);
    }

    @ApiOperation(value = "注册账号", notes = "注册账号")
    @ApiResponses({
            @ApiResponse(code = 0, message = "注册账号成功"),
            @ApiResponse(code = 100, message = "此账号已被注册")
    })
    @PostMapping("/register")
    public String register (@RequestParam("account") String account, @RequestParam("password") String password) {
        logger.info("账号注册");
        User user=new User();
        user.setAccount(account);
        if(userMapper.selectOne(user)!=null)
            return IssueService.Response("此账号已被注册",100,new JSONObject()).toJSONString();
        user.setPassword(MD5Utils.MD5Encode(password,"utf-8"));
        userMapper.insertSelective(user);
        return  IssueService.Response("注册成功",0,new JSONObject()).toJSONString();
    }


}
