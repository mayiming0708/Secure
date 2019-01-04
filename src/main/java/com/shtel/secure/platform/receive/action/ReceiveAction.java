package com.shtel.secure.platform.receive.action;

import com.shtel.secure.platform.receive.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ReceiveAction {

    @Autowired
    private ReceiveService receiveService;

    @RequestMapping(value = "/sock/v1/inform", method = RequestMethod.POST)
    public int receiveData(HttpServletRequest request, HttpServletResponse response, @RequestParam("parameter") String parameter) {

        return receiveService.insert(parameter);
    }

}
