package com.shtel.secure.config.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String user =(String) request.getSession().getAttribute("USERACCOUNT");
        if (user == null || user.equals(""))  {
            request.getRequestDispatcher("/login/loginAuth").forward
                    (request, response);
            return false;
        }
        return true;
    }
}