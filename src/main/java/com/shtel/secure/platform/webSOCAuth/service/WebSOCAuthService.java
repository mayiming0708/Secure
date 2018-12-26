package com.shtel.secure.platform.webSOCAuth.service;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.utils.ConsumeTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WebSOCAuthService {


    @Value("${basic.websocURL}")
    private String url;
    @Value("${basic.username}")
    private String username;
    @Value("${basic.password}")
    private String password;

    public String getCookie() {
        JSONObject parmJson = new JSONObject();
        parmJson.put("username", username);
        parmJson.put("password", password);
        ResponseEntity<String> responseEntity = ConsumeTemplate.commonPost(url + "/api/v2/login_auth/", parmJson.toJSONString(), String.class);
        return "{\"cookie\":\"" + responseEntity.getHeaders().get("Set-Cookie") + "\"}";
    }

}
