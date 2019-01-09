package com.shtel.secure.platform.issue.action;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.service.FinishTypeService;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.service.IssueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/7 15:37
 * @Description: 下发临时周期任务
 */
@RestController
public class IssueAction {
    private static Logger logger = LogManager.getLogger(IssueAction.class.getName());
    @Autowired
    private IssueService issueService;
    @Autowired
    private FinishTypeService finishTypeService;

    /**
     * <p>下发临时任务</p>
     *
     * @param task
     * @return
     */
    @PostMapping("/issue/temp")
    public String issueTempTask(@RequestBody Task task, HttpServletRequest request) {
        String userId = String.valueOf(request.getSession().getAttribute("USERID"));
        JSONObject response = issueService.issueTemporaryTask(task).getJSONObject("content");
        issueService.taskProcessRecord(response, task, userId);
        task.setIsPeriod(0);
        issueService.innsert(task);
        logger.info("下发临时任务");
        if (1 == task.getIsSuccess()) {
            String[] urls = task.getUrls().split(",");
            for (String url : urls) {
                FinishType finishType = new FinishType();
                finishType.setVirtualGroupId(task.getVirtualGroupId());
                finishType.setUrl(url);
                finishType.setRiskLowCount(0);
                finishType.setRiskMiddleCount(0);
                finishType.setRiskHighCount(0);
                finishType.setScore(0);
                finishTypeService.insertFinishType(finishType);
            }
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
        String userId = String.valueOf(request.getSession().getAttribute("USERID"));
        JSONObject response = issueService.issuePeriodTask(task).getJSONObject("content");
        issueService.taskProcessRecord(response, task, userId);
        task.setIsPeriod(1);
        logger.info("下发周期任务");
        issueService.innsert(task);
        if (1 == task.getIsSuccess()) {
            String[] urls = task.getUrls().split(",");
            for (String url : urls) {
                FinishType finishType = new FinishType();
                finishType.setVirtualGroupId(task.getVirtualGroupId());
                finishType.setUrl(url);
                finishType.setRiskLowCount(0);
                finishType.setRiskMiddleCount(0);
                finishType.setRiskHighCount(0);
                finishType.setScore(0);
                finishTypeService.insertFinishType(finishType);
            }
        }
        return response.toJSONString();
    }

    /**
     * <p>获取临时组检测进度</p>
     *
     * @param virtual_group_id
     * @return
     */
    @PostMapping("/issue/processPeriod")
    public String progressTemp(@RequestParam("virtual_group_id") String virtual_group_id) {
        logger.info("获取临时组检测进度");
        JSONObject validateResponse = issueService.validateNULL(virtual_group_id);
        if (validateResponse.getInteger("code") == 0) {
            String response = issueService.progressTempResponse(virtual_group_id);
            return response;
        }
        return validateResponse.toString();
    }

}
