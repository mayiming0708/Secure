package com.shtel.secure.platform.receive.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.enumType.model.EnumType;
import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.service.FinishTypeService;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.service.IssueService;
import com.shtel.secure.platform.login.model.User;
import com.shtel.secure.platform.login.service.UserService;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.model.ResultLevelCount;
import com.shtel.secure.platform.receive.service.ResultEventService;
import com.shtel.secure.platform.risk.model.Risk;
import com.shtel.secure.platform.risk.service.RiskService;
import com.shtel.secure.platform.riskLevel.service.RiskLevelService;
import com.shtel.secure.platform.type.model.Type;
import com.shtel.secure.platform.type.service.TypeService;
import com.shtel.secure.utils.EmailUtil;
import com.shtel.secure.utils.ResultUtil;
import io.swagger.annotations.*;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private IssueService issueService;
    @Autowired
    private UserService userService;


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

            //即将存储的返回结果
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
                finishTypeService.insertFinishType(finishType);
            } else {
                finishType = tmp;
            }


            if ("weakness".equals(moduleType)) {
                finishType.setZero();
            } else if ("content".equals(moduleType)) {
                finishType.setContentUrlCount(0);
            } else if ("siteinfo".equals(moduleType)) {
                finishType.setSiteinfo(0);
            } else if ("availability".equals(moduleType)) {
                finishType.setAvailability(0);
            }

            Integer highLevel = riskLevelService.getRiskLevel("high").getLevel();
            Integer middleLevel = riskLevelService.getRiskLevel("middle").getLevel();
            Integer lowLevel = riskLevelService.getRiskLevel("low").getLevel();
            Integer infoLevel = riskLevelService.getRiskLevel("info").getLevel();

            JSONArray resultValues = new JSONArray();

            //任务
            Task task = issueService.getUserByVirtualGroupId(oneJsonObject.getString("virtual_group_id"));
            //用户
            User user = userService.getUserById(task.getUserId());

            //ResultLevelCount存储用户的各种漏洞数量
            ResultLevelCount resultLevelCount = resultEventService.getResultLevelCountByUserId(user.getId());
            if (resultLevelCount == null) {
                resultLevelCount = new ResultLevelCount();
                resultLevelCount.setUserId(user.getId());
                resultEventService.insertResultLevelCount(resultLevelCount);
            }
            //解析values
            for (Object object : values) {
                JSONObject jsonObject = (JSONObject) object;

                try {
                    Type type = typeService.getTypeByNameEn(jsonObject.getString("type"));
                    Integer riskLevel = jsonObject.getJSONObject("value").getInteger("level");
                    if (riskLevel == null) {
                        if (type == null) {
                            Integer total = jsonObject.getJSONObject("value").getInteger("total");
                            if (total != null) {
                                if ("weakness".equals(moduleType)) {
                                    finishType.setRiskUrlCount(finishType.getRiskUrlCount() + total);
                                } else if ("content".equals(moduleType)) {
                                    finishType.setContentUrlCount(finishType.getContentUrlCount() + total);
                                }
                            }
                        } else {
                            Risk risk = riskService.getRisk(type.getRiskLevelId());
                            if (risk == null) {
                                Integer total = jsonObject.getJSONObject("value").getInteger("total");
                                if (total != null)
                                    finishType.setRiskUrlCount(finishType.getRiskUrlCount() + total);
                            } else {
                                riskLevel = risk.getLevel();
                            }
                        }

                    }
                    if (riskLevel != null) {
                        if (riskLevel >= highLevel) {
                            finishType.setRiskHighCount(finishType.getRiskHighCount() + 1);
                            jsonObject.put("risk_level", highLevel);
                        } else if (riskLevel >= middleLevel) {
                            finishType.setRiskMiddleCount(finishType.getRiskMiddleCount() + 1);
                            jsonObject.put("risk_level", middleLevel);
                        } else if (riskLevel >= lowLevel) {
                            finishType.setRiskLowCount(finishType.getRiskLowCount() + 1);
                            jsonObject.put("risk_level", lowLevel);
                        } else if (riskLevel >= infoLevel) {
                            finishType.setRiskInfoCount(finishType.getRiskInfoCount() + 1);
                            jsonObject.put("risk_level", infoLevel);
                        }
                    }

                    switch (jsonObject.getString("type")) {
                        case "siteinfo":
                            finishType.setSiteinfo(finishType.getSiteinfo() + 1);
                            break;
                        case "availability":
                            finishType.setAvailability(finishType.getAvailability() + 1);
                            break;
                        case "blackLinks":
                            if (riskLevel >= highLevel) {
                                resultLevelCount.setBlackLinksHigh(resultLevelCount.getBlackLinksHigh() + 1);
                            } else if (riskLevel >= middleLevel) {
                                resultLevelCount.setBlackLinksMiddle(resultLevelCount.getBlackLinksMiddle() + 1);
                            } else if (riskLevel >= lowLevel) {
                                resultLevelCount.setBlackLinksLow(resultLevelCount.getBlackLinksLow() + 1);
                            }
                            finishType.setBlackLinks(finishType.getBlackLinks() + 1);
                            break;
                        case "malscan":
                            if (riskLevel >= highLevel) {
                                resultLevelCount.setMalscanHigh(resultLevelCount.getBlackLinksHigh() + 1);
                            } else if (riskLevel >= middleLevel) {
                                resultLevelCount.setMalscanMiddle(resultLevelCount.getBlackLinksMiddle() + 1);
                            } else if (riskLevel >= lowLevel) {
                                resultLevelCount.setMalscanLow(resultLevelCount.getBlackLinksLow() + 1);
                            }
                            finishType.setMalscan(finishType.getMalscan() + 1);
                            break;
                        case "keyword":
                            if (riskLevel >= highLevel) {
                                resultLevelCount.setBlackLinksHigh(resultLevelCount.getBlackLinksHigh() + 1);
                            } else if (riskLevel >= middleLevel) {
                                resultLevelCount.setBlackLinksHigh(resultLevelCount.getBlackLinksMiddle() + 1);
                            } else if (riskLevel >= lowLevel) {
                                resultLevelCount.setBlackLinksHigh(resultLevelCount.getBlackLinksLow() + 1);
                            }
                            finishType.setKeyword(finishType.getKeyword() + 1);
                            break;
                        case "sql":
                            if (riskLevel >= highLevel) {
                                resultLevelCount.setSqlInjectionHigh(resultLevelCount.getSqlInjectionHigh() + 1);
                            } else if (riskLevel >= middleLevel) {
                                resultLevelCount.setSqlInjectionMiddle(resultLevelCount.getSqlInjectionMiddle() + 1);
                            } else if (riskLevel >= lowLevel) {
                                resultLevelCount.setSqlInjectionLow(resultLevelCount.getSqlInjectionLow() + 1);
                            }
                            finishType.setSqlInjection(finishType.getSqlInjection() + 1);
                            break;
                        case "xss":
                            if (riskLevel >= highLevel) {
                                resultLevelCount.setXssHigh(resultLevelCount.getXssHigh() + 1);
                            } else if (riskLevel >= middleLevel) {
                                resultLevelCount.setXssMiddle(resultLevelCount.getXssMiddle() + 1);
                            } else if (riskLevel >= lowLevel) {
                                resultLevelCount.setXssLow(resultLevelCount.getXssLow() + 1);
                            }
                            finishType.setXss(finishType.getXss() + 1);
                            break;
                        case "webvul":
                            if (riskLevel >= highLevel) {
                                resultLevelCount.setWebvulHigh(resultLevelCount.getWebvulHigh() + 1);
                            } else if (riskLevel >= middleLevel) {
                                resultLevelCount.setWebvulMiddle(resultLevelCount.getWebvulMiddle() + 1);
                            } else if (riskLevel >= lowLevel) {
                                resultLevelCount.setWebvulLow(resultLevelCount.getWebvulLow() + 1);
                            }
                            finishType.setWebvul(finishType.getWebvul() + 1);
                            break;
                        case "infoLeak":
                            if (riskLevel >= highLevel) {
                                resultLevelCount.setInfoLeakHigh(resultLevelCount.getInfoLeakHigh() + 1);
                            } else if (riskLevel >= middleLevel) {
                                resultLevelCount.setInfoLeakMiddle(resultLevelCount.getInfoLeakMiddle() + 1);
                            } else if (riskLevel >= lowLevel) {
                                resultLevelCount.setInfoLeakLow(resultLevelCount.getInfoLeakLow() + 1);
                            }
                            finishType.setInfoLeak(finishType.getInfoLeak() + 1);
                            break;
                        case "cgi":
                            if (riskLevel >= highLevel) {
                                resultLevelCount.setCgiHigh(resultLevelCount.getCgiHigh() + 1);
                            } else if (riskLevel >= middleLevel) {
                                resultLevelCount.setCgiMiddle(resultLevelCount.getCgiMiddle() + 1);
                            } else if (riskLevel >= lowLevel) {
                                resultLevelCount.setCgiLow(resultLevelCount.getCgiLow() + 1);
                            }
                            finishType.setCgi(finishType.getCgi() + 1);
                            break;
                        case "csrf":
                            if (riskLevel >= highLevel) {
                                resultLevelCount.setCsrfHigh(resultLevelCount.getCsrfHigh() + 1);
                            } else if (riskLevel >= middleLevel) {
                                resultLevelCount.setCsrfMiddle(resultLevelCount.getCsrfMiddle() + 1);
                            } else if (riskLevel >= lowLevel) {
                                resultLevelCount.setCsrfLow(resultLevelCount.getCsrfLow() + 1);
                            }
                            finishType.setCsrf(finishType.getCsrf() + 1);
                            break;
                        case "formCrack":
                            if (riskLevel >= highLevel) {
                                resultLevelCount.setFormCrackHigh(resultLevelCount.getFormCrackHigh() + 1);
                            } else if (riskLevel >= middleLevel) {
                                resultLevelCount.setFormCrackMiddle(resultLevelCount.getFormCrackMiddle() + 1);
                            } else if (riskLevel >= lowLevel) {
                                resultLevelCount.setFormCrackLow(resultLevelCount.getFormCrackLow() + 1);
                            }
                            finishType.setFormCrack(finishType.getFormCrack() + 1);
                            break;
                    }
                    resultValues.add(jsonObject);

                } catch (Exception e) {
                    logger.info("|解析" + jsonObject + "失败：" + e);
                }


            }

            resultEvent.setValue(resultValues.toJSONString());

            finishType.setScore(resultEventService.calculationScore(finishType));

            //插入一条结果记录
            resultEventService.resultEventInsert(resultEvent);

            //更新finishtype记录
            finishTypeService.updateFinishType(finishType);

            issueService.updateFinishRate(oneJsonObject.getString("virtual_group_id"));


            if (task.getFinishRate() == 1.00) {
                Task taskData=issueService.getTaskById(oneJsonObject.getInteger("task_id"));
                resultEventService.sendFinishMail(user.getEmail(),taskData.getCreateTime().toString(),taskData.getUpdateTime().toString());
                resultEventService.sendSMS(user.getPhoneNum(),taskData.getCreateTime().toString(),taskData.getUpdateTime().toString());
            }
            resultEventService.updateResultLevelCount(resultLevelCount);
        } catch (Exception e) {
            logger.info("|回调接口接收数据失败：" + e);
        }

        resultEventService.insert(parameter);

        return ResultUtil.Result(EnumType.SUCCESS);
    }
}
