package com.shtel.secure.platform.issue.action;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.service.FinishTypeService;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.service.IssueService;
import io.swagger.annotations.*;
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
@Api(tags = "issue-action", description = "下发任务接口")
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
    @ApiOperation(value = "下发临时任务", notes = "下发任务:urls必填，(10种必填)字段0是不监测，1是监测")
    @ApiResponses({
            @ApiResponse(code = 100, message = "{错误信息}"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/issue/temp")
    public String issueTempTask(@RequestBody @ApiParam(name = "临时任务对象", value = "传入json格式"
            , allowableValues = "[urls,blackLinks,malscan,keyword,xss,sqlInjection,webvul,info_leak,cgi,csrf,formCrack]",
            required = true) Task task, HttpServletRequest request) {
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
        return IssueService.Response(response.getString("message"),response.getInteger("code"),response.getJSONObject("result")).toJSONString();
    }

    /**
     * <p>下发周期任务</p>
     *
     * @param task
     * @return
     */
    @ApiOperation(value = "下发周期任务", notes = "下发任务:urls必填，(10种必填)字段0是不监测，1是监测")
    @ApiResponses({
            @ApiResponse(code = 100, message = "{错误信息}"),
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/issue/period")
    public String issueTempPeriod(@RequestBody @ApiParam(name = "周期任务对象", value = "传入json格式",
            allowableValues = "range[1,5]",
            required = true) Task task, HttpServletRequest request) {
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
        return IssueService.Response(response.getString("message"),response.getInteger("code"),response.getJSONObject("result")).toJSONString();
    }

    /**
     * <p>获取临时组检测进度</p>
     *
     * @param virtual_group_id
     * @return
     */
    @ApiOperation(value = "获取临时组监测接口", notes = "virtual_group_id：临时任务id必填")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "virtual_group_id", value = "任务id", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 100, message = "临时任务不存在|用户为空"),
            @ApiResponse(code = 0, message = "返回站点信息")
    })
    @PostMapping("/issue/processPeriod")
    public String progressTemp(@RequestParam("virtual_group_id") String virtual_group_id) {
        logger.info("获取临时组检测进度");
        JSONObject validateResponse = issueService.validateNULL(virtual_group_id);
        if (validateResponse.getInteger("code") == 0) {
            JSONObject response = issueService.progressTempResponse(virtual_group_id).getJSONObject("content");
            JSONObject endResponse=IssueService.Response(response.getString("message"),response.getInteger("code"),response.getJSONObject("result"));
            return endResponse.toJSONString();
        }
        return validateResponse.toString();
    }

}
