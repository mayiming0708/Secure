package com.shtel.secure.platform.receive.action;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.enumType.model.EnumType;
import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.service.FinishTypeService;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.service.ResultEventService;
import com.shtel.secure.platform.riskLevel.model.RiskLevel;
import com.shtel.secure.platform.riskLevel.service.RiskLevelService;
import com.shtel.secure.platform.type.model.Type;
import com.shtel.secure.platform.type.service.TypeService;
import com.shtel.secure.utils.ResultUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 */
@Api(tags = "ReceiveAction", description = "回调接口ACTION")
@RestController
@RequestMapping(value = "/sock/v1")
public class ReceiveAction {
    private static final Logger logger = LoggerFactory.getLogger(ReceiveAction.class);

    @Autowired
    private ResultEventService resultEventService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private FinishTypeService finishTypeService;
    @Autowired
    private RiskLevelService riskLevelService;


    /**
     * 回调接口
     *
     * @param request
     * @param response
     * @param parameter
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "success")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "parameter", value = "回调接口参数", required = true, dataType = "String")
    })
    @RequestMapping(value = "/inform", method = RequestMethod.POST)
    public String receiveData(HttpServletRequest request, HttpServletResponse response, @RequestParam("parameter") String parameter) {

        JSONObject oneJsonObject = (JSONObject) JSONObject.parse(parameter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ResultEvent resultEvent = new ResultEvent();
            String id = UUID.randomUUID().toString();
            resultEvent.setId(id);
            resultEvent.setReceiveTime(new Date());

            resultEvent.setVirtualGroupId(oneJsonObject.getString("virtual_group_id"));
            resultEvent.setValue(oneJsonObject.getJSONArray("values").toString());
            resultEvent.setTotal(oneJsonObject.getString("total"));
            resultEvent.setStartAt(simpleDateFormat.parse(oneJsonObject.getString("start_at")));
            resultEvent.setTaskId(oneJsonObject.getString("task_id"));

            String moduleType = oneJsonObject.getString("module_type");
            Type type = typeService.getTypeByNameEn(moduleType);
            resultEvent.setModuleType(String.valueOf(type.getId()));


            String groupId = oneJsonObject.getString("group_id");
            resultEvent.setGroupId(groupId);
            resultEvent.setSiteId(oneJsonObject.getString("site_id"));
            resultEvent.setSite(oneJsonObject.getString("site"));
            resultEvent.setEndAt(simpleDateFormat.parse(oneJsonObject.getString("end_at")));

            resultEventService.resultEventInsert(resultEvent);

            //结果存入finishtype
            FinishType finishType = new FinishType();
            finishType.setVirtualGroupId(oneJsonObject.getString("virtual_group_id"));
            finishType.setUrl(oneJsonObject.getString("site"));
            FinishType tmp = finishTypeService.getFinishTypeByGourpIdAndUrl(finishType.getVirtualGroupId(), finishType.getUrl());
            if (tmp == null) {
                finishType.setRiskHighCount(0);
                finishType.setRiskMiddleCount(0);
                finishType.setRiskLowCount(0);
                finishType.setScore(0);
                finishTypeService.insertFinishType(finishType);
            } else {
                finishType = tmp;
            }

            int riskHighCount = finishType.getRiskHighCount();
            int riskMiddleCount = finishType.getRiskMiddleCount();
            int riskLowCount = finishType.getRiskLowCount();
            try {
                RiskLevel riskLevel = riskLevelService.getRiskLevel(type.getRiskLevelId());
                if (riskLevel.getLevel() == 3) {
                    riskHighCount += Integer.valueOf(resultEvent.getTotal());
                } else if (riskLevel.getLevel() == 2) {
                    riskHighCount += Integer.valueOf(resultEvent.getTotal());
                } else if (riskLevel.getLevel() == 1) {
                    riskHighCount += Integer.valueOf(resultEvent.getTotal());
                }
            } catch (Exception e) {
                logger.info("|RiskLevel查询失败：" + e);

            }
            finishType.setRiskHighCount(riskHighCount);
            finishType.setRiskMiddleCount(riskMiddleCount);
            finishType.setRiskLowCount(riskLowCount);
            finishType.setScore(resultEventService.calculationScore(finishType));

            switch (moduleType) {
                case "siteinfo":
                    finishType.setSiteinfo(id);
                    break;
                case "availability":
                    finishType.setAvailability(id);
                    break;
                case "blackLinks":
                    finishType.setBlackLinks(id);
                    break;
                case "malscan":
                    finishType.setMalscan(id);
                    break;
                case "keyword":
                    finishType.setKeyword(id);
                    break;
                case "sql":
                    finishType.setSqlInjection(id);
                    break;
                case "xss":
                    finishType.setXss(id);
                    break;
                case "webvul":
                    finishType.setWebvul(id);
                    break;
                case "infoLeak":
                    finishType.setInfoLeak(id);
                    break;
                case "cgi":
                    finishType.setCgi(id);
                    break;
                case "csrf":
                    finishType.setCsrf(id);
                    break;
                case "formCrack":
                    finishType.setFormCrack(id);
                    break;
            }

            finishTypeService.updateFinishType(finishType);
        } catch (Exception e) {
            logger.info("|回调接口接收数据失败：" + e);
        }


        resultEventService.insert(parameter);

        return ResultUtil.Result(EnumType.SUCCESS);
    }

}
