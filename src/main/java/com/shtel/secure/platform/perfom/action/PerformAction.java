package com.shtel.secure.platform.perfom.action;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.perfom.service.PerformService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther: 陈文强
 * @Date: 2019/1/15 13:41
 * @Description: 页面交互接口
 */
@RestController
@RequestMapping("/perform")
public class PerformAction {
    private static Logger logger = LogManager.getLogger(PerformAction.class.getName());

    @Autowired
    private PerformService performService;

    @PostMapping("/taskList")
    public JSONObject taskList(@RequestParam("userId") String userId){
        return performService.selectTaskByUserId(userId);
    }

    @PostMapping("/urlList")
    public JSONObject taskList(@RequestParam("userId") String userId,@RequestParam("isPeriod") Integer isPeriod){
        return performService.selectWebDetail(userId);
    }

    @PostMapping("/webListPage")
    public JSONObject webListPage(@RequestParam("userId") String userId,@RequestParam("currentPage") Integer currentPage,@RequestParam("pageSize") int pageSize){
        return performService.selectWebPage(currentPage,pageSize,userId);
    }

    @PostMapping("/taskListPage")
    public JSONObject taskListPage(@RequestParam("userId") String userId,@RequestParam("currentPage") Integer currentPage,@RequestParam("pageSize") int pageSize){
        return performService.selectTaskPage(currentPage,pageSize,userId);
    }

    @PostMapping("/performData")
    public JSONObject performData(){
        return performService.getPerformData();
    }
}
