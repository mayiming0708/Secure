package com.shtel.secure.platform.issue.action;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.enumType.model.EnumType;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.service.issueService;
import com.shtel.secure.utils.ResultUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        return ResultUtil.Result(EnumType.SUCCESS);
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
        return ResultUtil.Result(EnumType.SUCCESS);
    }

}
