package com.shtel.secure.platform.demo.aciton;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.demo.service.DemoService;
import com.shtel.secure.platform.enumType.model.EnumType;
import com.shtel.secure.utils.ResultUtil;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/demo")
public class DemoAction {

    @Autowired
    public DemoService demoService;

    @RequestMapping(value = "/demo",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject demo() throws Exception {
        return demoService.start();
    }

    @RequestMapping(value = "/jobs",method = RequestMethod.DELETE)
    @ResponseBody
    public String stopJobs() throws Exception {
        demoService.stopJobs();
        return ResultUtil.Result(EnumType.SUCCESS);
    }

    @RequestMapping(value = "/jobs",method = RequestMethod.GET)
    public void getJobs() throws Exception {
        demoService.getJobs();
    }

    @RequestMapping(value = "/job", method = RequestMethod.DELETE)
    public void stopJob(@RequestBody String jobKeyString) throws Exception {
        JSONObject jobKeyJson = JSON.parseObject(jobKeyString);
        demoService.stopJob(jobKeyJson.getString("jobName"),jobKeyJson.getString("jobGroup"));
    }
}
