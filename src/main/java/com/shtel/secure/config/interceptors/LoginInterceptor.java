package com.shtel.secure.config.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Object user = request.getSession().getAttribute("USERID");
        if (user == null || user.equals(""))  {
            request.getRequestDispatcher("/login/loginAuth").forward
                    (request, response);
            return false;
        }
        return true;
    }
}