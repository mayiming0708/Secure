package com.shtel.secure.platform.demo.aciton;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/websock-server")
@Controller
public class AcceptAction {
    @PostMapping("/sock/v1/inform")
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
