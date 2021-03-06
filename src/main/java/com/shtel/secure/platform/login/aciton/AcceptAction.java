package com.shtel.secure.platform.login.aciton;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/3 14:35
 * @Description: 回调接口
 */
@Controller
public class AcceptAction {
    @GetMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("static/html/register.html").forward(request,response);
    }
    @GetMapping("/index")
    public void loginIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("static/html/login.html").forward(request,response);
    }
    @GetMapping("/newIndex")
    public void newIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("static/html/newIndex.html").forward(request,response);
    }
    @GetMapping("/taskprogress")
    public void taskprogress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("static/html/taskprogress.html").forward(request,response);
    }
    @GetMapping("/taskdetail")
    public void taskdetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("static/html/taskdetail.html").forward(request,response);
    }
    @GetMapping("/sitemonitor")
    public void sitemonitor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("static/html/sitemonitor.html").forward(request,response);
    }
    @GetMapping("/sitedetail")
    public void sitedetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("static/html/sitedetail.html").forward(request,response);
    }
    @PostMapping("/registerAccount")
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login/register").forward(request,response);
    }



    public void acceptData(HttpServletRequest request, HttpServletResponse response, @RequestParam("parameter") String parameter) {
        String str = parameter;
        System.out.println(str);
    }

    /**
     * <p>读取requestBody中的raw</p>
     * @param request
     * @return
     */
    public static String ReadAsChars(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
