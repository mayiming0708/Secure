package com.shtel.secure.platform.issue.action;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.service.FinishTypeService;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.service.issueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/7 15:37
 * @Description: 下发临时周期任务
 */
@RestController
public class issueAction {
    private static Logger logger = LogManager.getLogger(issueAction.class.getName());
    @Autowired
    private issueService issueService;
    @Autowired
    private FinishTypeService finishTypeService;
    /**
     * <p>下发临时任务</p>
     *
     * @param task
     * @return
     */
    @PostMapping("/issue/temp")
    public String issueTempTask(@RequestBody Task task, HttpServletRequest request){
        String userId =String.valueOf( request.getSession().getAttribute("USERID"));
        JSONObject response= issueService.issueTemporaryTask(task).getJSONObject("content");
        issueService.taskProcessRecord(response,task,userId);
        task.setIsPeriod(0);
        issueService.innsert(task);
        logger.info("下发临时任务");
        if(1==task.getIsSuccess()) {
            FinishType finishType=new FinishType();
            finishType.setVirtualGroupId(task.getVirtualGroupId());
            finishTypeService.insertFinishType(finishType);
        }
        return response.toJSONString();
    }

    /**
     * <p>下发周期任务</p>
     *
     * @param task
     * @return
     */
    @PostMapping("/issue/period")
    public String issueTempPeriod(@RequestBody Task task, HttpServletRequest request) {
        String userId =String.valueOf( request.getSession().getAttribute("USERID"));
        JSONObject response= issueService.issuePeriodTask(task).getJSONObject("content");
        issueService.taskProcessRecord(response,task,userId);
        task.setIsPeriod(1);
        logger.info("下发周期任务");
        if(1==task.getIsSuccess()) {
            FinishType finishType=new FinishType();
            finishType.setVirtualGroupId(task.getVirtualGroupId());
            finishTypeService.insertFinishType(finishType);
        }
        return  response.toJSONString();
    }

}
