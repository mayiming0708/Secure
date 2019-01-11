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

            JSONArray resultValues = new JSONArray();

            //解析values
            for (Object object : values) {
                JSONObject jsonObject = (JSONObject) object;

                try {
                    Type type = typeService.getTypeByNameEn(jsonObject.getString("type"));
                    Integer riskLevel = jsonObject.getJSONObject("value").getInteger("level");
                    if (riskLevel == null) {
                        if (type == null) {
                            Integer total = jsonObject.getJSONObject("value").getInteger("total");
                            if (total != null)
                                riskUrlCount += total;
                        } else {
                            Risk risk = riskService.getRisk(type.getRiskLevelId());
                            if (risk == null) {
                                Integer total = jsonObject.getJSONObject("value").getInteger("total");
                                if (total != null)
                                    riskUrlCount += total;
                            } else {
                                riskLevel = risk.getLevel();
                            }
                        }

                    }
                    if (riskLevel != null) {
                        if (riskLevel >= highLevel) {
                            riskHighCount++;
                            jsonObject.put("risk_level", highLevel);
                        } else if (riskLevel >= middleLevel) {
                            riskMiddleCount++;
                            jsonObject.put("risk_level", middleLevel);
                        } else if (riskLevel >= lowLevel) {
                            riskLowCount++;
                            jsonObject.put("risk_level", lowLevel);
                        } else if (riskLevel >= infoLevel) {
                            riskInfoCount++;
                            jsonObject.put("risk_level", infoLevel);
                        }
                    }

                    switch (jsonObject.getString("type")) {
                        case "siteinfo":
                            if(finishType.getSiteinfo()==null){
                                finishType.setSiteinfo(0);
                            }
                            finishType.setSiteinfo(finishType.getSiteinfo()+1);
                            break;
                        case "availability":
                            if (finishType.getAvailability() == null) {
                                finishType.setAvailability(0);
                            }
                            finishType.setAvailability(finishType.getAvailability()+1);
                            break;
                        case "blackLinks":
                            if (finishType.getBlackLinks() == null) {
                                finishType.setBlackLinks(0);
                            }
                            finishType.setBlackLinks(finishType.getBlackLinks()+1);
                            break;
                        case "malscan":
                            if (finishType.getMalscan() == null) {
                                finishType.setMalscan(0);
                            }
                            finishType.setMalscan(finishType.getMalscan()+1);
                            break;
                        case "keyword":
                            if (finishType.getKeyword() == null) {
                                finishType.setKeyword(0);
                            }
                            finishType.setKeyword(finishType.getKeyword()+1);
                            break;
                        case "sql":
                            if (finishType.getSqlInjection() == null) {
                                finishType.setSqlInjection(0);
                            }
                            finishType.setSqlInjection(finishType.getSqlInjection()+1);
                            break;
                        case "xss":
                            if (finishType.getXss() == null) {
                                finishType.setXss(0);
                            }
                            finishType.setXss(finishType.getXss()+1);
                            break;
                        case "webvul":
                            if (finishType.getWebvul() == null) {
                                finishType.setWebvul(0);
                            }
                            finishType.setWebvul(finishType.getWebvul()+1);
                            break;
                        case "infoLeak":
                            if (finishType.getInfoLeak() == null) {
                                finishType.setInfoLeak(0);
                            }
                            finishType.setInfoLeak(finishType.getInfoLeak()+1);
                            break;
                        case "cgi":
                            if (finishType.getCgi() == null) {
                                finishType.setCgi(0);
                            }
                            finishType.setCgi(finishType.getCgi()+1);
                            break;
                        case "csrf":
                            if (finishType.getCsrf() == null) {
                                finishType.setCsrf(0);
                            }
                            finishType.setCsrf(finishType.getCsrf()+1);
                            break;
                        case "formCrack":
                            if (finishType.getFormCrack() == null) {
                                finishType.setFormCrack(0);
                            }
                            finishType.setFormCrack(finishType.getFormCrack()+1);
                            break;
                    }
                    resultValues.add(jsonObject);

                } catch (Exception e) {
                    logger.info("|解析" + jsonObject + "失败：" + e);
                }


            }

            resultEvent.setValue(resultValues.toJSONString());

            finishType.setRiskHighCount(riskHighCount);
            finishType.setRiskMiddleCount(riskMiddleCount);
            finishType.setRiskLowCount(riskLowCount);
            finishType.setRiskInfoCount(riskInfoCount);
            finishType.setRiskUrlCount(riskUrlCount);
            finishType.setScore(resultEventService.calculationScore(finishType));

            //插入一条结果记录
            resultEventService.resultEventInsert(resultEvent);
            //更新finishtype记录
            finishTypeService.updateFinishType(finishType);
        } catch (Exception e) {
            logger.info("|回调接口接收数据失败：" + e);
        }


        resultEventService.insert(parameter);

        return ResultUtil.Result(EnumType.SUCCESS);
    }

}
