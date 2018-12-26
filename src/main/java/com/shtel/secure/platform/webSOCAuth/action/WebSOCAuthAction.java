package com.shtel.secure.platform.webSOCAuth.action;

import com.shtel.secure.platform.webSOCAuth.service.WebSOCAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/websoc")
public class WebSOCAuthAction {

    @Autowired
    WebSOCAuthService webSOCAuthService;
    @Value("${basic.websocURL}")
    private String url;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String webSOCAuth(HttpServletRequest request, HttpServletResponse response, @RequestBody String parm) {
        return webSOCAuthService.getCookie();
    }
}
