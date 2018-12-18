package com.shtel.secure.config.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Object user = request.getSession().getAttribute("user");
        //TODO 需要添加用户认证功能
        if (user == null || user.equals(""))  {
            response.sendRedirect("/index");
            return false;
        }
        return true;
    }
}