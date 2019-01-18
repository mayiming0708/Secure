package com.shtel.secure.platform.perfom.action;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.perfom.model.PerformReq;
import com.shtel.secure.platform.perfom.service.PerformService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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
    public JSONObject taskList(@RequestParam("userId") String userId) {
        return performService.selectTaskByUserId(userId);
    }

    @PostMapping("/urlList")
    public JSONObject taskList(@RequestParam("userId") String userId, @RequestParam("isPeriod") Integer isPeriod) {
        return performService.selectWebDetail(userId);
    }

    @PostMapping("/webListPage")
    public JSONObject webListPage(@RequestBody PerformReq performReq) {
        return performService.selectWebPage(performReq);
    }

    @PostMapping("/taskListPage")
    public JSONObject taskListPage(@RequestBody PerformReq performReq) throws ParseException {
        if (performReq.getCreateTime() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(performReq.getCreateTime());
            performReq.setCreateTime(simpleDateFormat1.format(date));
        }
        return performService.selectTaskPage(performReq);
    }

    @PostMapping("/performData")
    public JSONObject performData() {
        return performService.getPerformData();
    }

    @GetMapping("/deleteTask")
    public JSONObject deleteTask(@RequestParam("virtualGroupId")String virtual_group_id){
        return performService.deleteRecord(virtual_group_id);
    }

    @PostMapping("/getWebetailDesc")
    public List<PerformReq> getWebetailDesc(@RequestParam("url")String url){
        return performService.getWebDetailListDESC(url);
    }
}