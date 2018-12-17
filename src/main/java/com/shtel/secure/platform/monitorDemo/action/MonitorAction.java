package com.shtel.secure.platform.monitorDemo.action;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.monitorDemo.model.bo.Monitor;
import com.shtel.secure.platform.monitorDemo.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MonitorAction {

    @Autowired
    private MonitorService monitorService;

    @RequestMapping("monitor")
    @ResponseBody
    public String selectMonitorByMonitorID(){
        Monitor monitor = monitorService.selectByMonitorID("0005EC8790A64356A6ABA43BE34AF918");
        String monitorString = JSONObject.toJSONString(monitor);
        return monitorString;
    }
}
