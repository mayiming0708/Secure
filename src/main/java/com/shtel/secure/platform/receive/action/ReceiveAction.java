package com.shtel.secure.platform.receive.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.enumType.model.EnumType;
import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.service.FinishTypeService;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.service.ResultEventService;
import com.shtel.secure.platform.risk.model.Risk;
import com.shtel.secure.platform.risk.service.RiskService;
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
    @Autowired
    private RiskService riskService;


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

            JSONArray values = oneJsonObject.getJSONArray("values");
            resultEvent.setValue(values.toString());
            resultEvent.setTotal(oneJsonObject.getString("total"));
            resultEvent.setStartAt(simpleDateFormat.parse(oneJsonObject.getString("start_at")));
            resultEvent.setTaskId(oneJsonObject.getString("task_id"));

            String moduleType = oneJsonObject.getString("module_type");
            resultEvent.setModuleType(moduleType);


            String groupId = oneJsonObject.getString("group_id");
            resultEvent.setGroupId(groupId);
            resultEvent.setSiteId(oneJsonObject.getString("site_id"));
            resultEvent.setSite(oneJsonObject.getString("site"));
            resultEvent.setEndAt(simpleDateFormat.parse(oneJsonObject.getString("end_at")));
            resultEvent.setReportUrl(oneJsonObject.getString("report_url"));

            resultEventService.resultEventInsert(resultEvent);


            //结果存入finishtype
            FinishType finishType = new FinishType();
            finishType.setVirtualGroupId(oneJsonObject.getString("virtual_group_id"));
            finishType.setUrl(oneJsonObject.getString("site"));
            FinishType tmp = finishTypeService.getFinishTypeByGourpIdAndUrl(finishType.getVirtualGroupId(), finishType.getUrl());
            if (tmp == null) {
                finishType.setRiskInfoCount(0);
                finishType.setRiskHighCount(0);
                finishType.setRiskMiddleCount(0);
                finishType.setRiskLowCount(0);
                finishType.setRiskUrlCount(0);
                finishType.setScore(0);
                finishTypeService.insertFinishType(finishType);
            } else {
                finishType = tmp;
            }

            Integer riskHighCount = finishType.getRiskHighCount();
            Integer riskMiddleCount = finishType.getRiskMiddleCount();
            Integer riskLowCount = finishType.getRiskLowCount();
            Integer riskInfoCount = finishType.getRiskInfoCount();
            Integer riskUrlCount = finishType.getRiskUrlCount();

            Integer highLevel = riskLevelService.getRiskLevel("high").getLevel();
            Integer middleLevel = riskLevelService.getRiskLevel("middle").getLevel();
            Integer lowLevel = riskLevelService.getRiskLevel("low").getLevel();
            Integer infoLevel = riskLevelService.getRiskLevel("info").getLevel();


            //解析values

            for (Object object : values) {
                JSONObject jsonObject = (JSONObject) object;

                try {
                    try {
                        Integer riskLevel = jsonObject.getJSONObject("value").getInteger("level");
                        if (riskLevel == null) {
                            Type type = typeService.getTypeByNameEn(jsonObject.getString("type"));
                            if (type == null) {
                                Integer total = jsonObject.getJSONObject("value").getInteger("total");
                                riskUrlCount += total;
                            }else{
                                Risk risk = riskService.getRisk(type.getRiskLevelId());
                                if (risk == null) {
                                    Integer total = jsonObject.getJSONObject("value").getInteger("total");
                                    riskUrlCount += total;
                                }else{
                                    riskLevel = risk.getLevel();
                                }
                            }

                        }
                        if (riskLevel != null) {
                            if (riskLevel >= highLevel) {
                                riskHighCount++;
                            } else if (riskLevel >= middleLevel) {
                                riskMiddleCount++;
                            } else if (riskLevel >= lowLevel) {
                                riskLowCount++;
                            } else if (riskLevel >= infoLevel) {
                                riskInfoCount++;
                            }
                        }
                    } catch (Exception e) {
                        logger.info("|统计出错" + e);
                    }


                    switch (jsonObject.getString("type")) {
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
                } catch (Exception e) {
                    logger.info("|解析" + jsonObject + "失败：" + e);
                }

            }

            finishType.setRiskHighCount(riskHighCount);
            finishType.setRiskMiddleCount(riskMiddleCount);
            finishType.setRiskLowCount(riskLowCount);
            finishType.setRiskInfoCount(riskInfoCount);
            finishType.setRiskUrlCount(riskUrlCount);
            finishType.setScore(resultEventService.calculationScore(finishType));
            finishTypeService.updateFinishType(finishType);
        } catch (Exception e) {
            logger.info("|回调接口接收数据失败：" + e);
        }


        resultEventService.insert(parameter);

        return ResultUtil.Result(EnumType.SUCCESS);
    }

}
