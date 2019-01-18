package com.shtel.secure.platform.receive.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.finishType.service.FinishTypeService;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.model.ResultLevelCount;
import com.shtel.secure.platform.receive.service.ResultEventService;
import com.shtel.secure.platform.riskLevel.service.RiskLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "任务结果查询", description = "ResultEvent")
@RestController
@RequestMapping(value = "/service")
public class ResultEventAction {

    private static final Logger logger = LoggerFactory.getLogger(ResultEventAction.class);

    @Autowired
    private ResultEventService resultEventService;

    @Autowired
    private RiskLevelService riskLevelService;

    @ApiOperation(value = "根据resultEvent id查询返回的resultEvent结果", tags = "任务结果查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "resultEventId", required = true, dataType = "String")
    })
    @RequestMapping(value = "/resultEvent", method = RequestMethod.GET)
    public ResultEvent resultEventById(@RequestParam("id") String id) {
        return resultEventService.getResultEventById(id);
    }


//    /**
//     * 根据group_id查找一个group的所有resultEvent
//     *
//     * @param virtualGroupId
//     * @return
//     */
//    @ApiOperation(value = "根据FinishType中的virtualGroupID与url匹配一条结果并查询返回resultEvent结果", tags = "任务结果查询")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", name = "virtualGroupId", value = "任务ID", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType = "query", name = "url", value = "url", required = true, dataType = "String")
//    })
//    @RequestMapping(value = "/resultEvents", method = RequestMethod.GET)
//    public Map<String, ResultEvent> resultEventByGroup(@RequestParam("virtualGroupId") String virtualGroupId, @RequestParam("url") String url) {
//        Map<String, ResultEvent> map = new HashMap<>();
//        try {
//            FinishType finishType = finishTypeService.getFinishTypeByGourpIdAndUrl(virtualGroupId, url);
//            for (Field field : finishType.getClass().getDeclaredFields()) {
//                if ("id".equals(field.getName()) || "virtualGroupId".equals(field.getName()) || "url".equals(field.getName()) || "score".equals(field.getName()) || "riskHighCount".equals(field.getName()) || "riskMiddleCount".equals(field.getName()) || "riskLowCount".equals(field.getName())) {
//                    continue;
//                }
//                field.setAccessible(true);
//                map.put(field.getName(), resultEventService.getResultEventById((String) field.get(finishType)));
//            }
//        } catch (Exception e) {
//            logger.info("|获取任务结果失败：" + e);
//        }
//        return map;
//    }

    /**
     * 根据group_id查找一个group的所有resultEvent
     *
     * @param virtualGroupId
     * @return
     */
    @ApiOperation(value = "根据FinishType中的virtualGroupID与url匹配一条结果并查询返回resultEvent结果", tags = "任务结果查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "virtualGroupId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "url", value = "url", required = true, dataType = "String")
    })
    @RequestMapping(value = "/resultEvents", method = RequestMethod.GET)
    public List<ResultEvent> resultEventByGroupAndUrl(@RequestParam String virtualGroupId, @RequestParam(required = false) String url) {
        return resultEventService.getResultEventsByGroupAndUrl(virtualGroupId, url);
    }

    @ApiOperation(value = "根据userId与url返回resultEvent结果", tags = "任务结果查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "url", value = "url", required = true, dataType = "String")
    })
    @RequestMapping(value = "/user/resultEvents", method = RequestMethod.GET)
    public Map<String, Object> resultEventByUserIdAndUrl(@RequestParam Integer userId, @RequestParam(required = false) String url) {
        List<ResultEvent> resultEvents = resultEventService.getResultEventsByUserIdAndUrl(userId, url);

        Map<String, Object> map = new HashMap<>();
        map.put("resultEvent", resultEvents);
        ResultLevelCount resultLevelCount = new ResultLevelCount();
        resultLevelCount.setUserId(userId);
        resultLevelCount.setBlackLinksLow(0);
        resultLevelCount.setMalscanLow(0);
        resultLevelCount.setKeywordLow(0);
        resultLevelCount.setSqlInjectionLow(0);
        resultLevelCount.setXssLow(0);
        resultLevelCount.setWebvulLow(0);
        resultLevelCount.setInfoLeakLow(0);
        resultLevelCount.setCgiLow(0);
        resultLevelCount.setCsrfLow(0);
        resultLevelCount.setFormCrackLow(0);
        resultLevelCount.setBlackLinksMiddle(0);
        resultLevelCount.setMalscanMiddle(0);
        resultLevelCount.setKeywordMiddle(0);
        resultLevelCount.setSqlInjectionMiddle(0);
        resultLevelCount.setXssMiddle(0);
        resultLevelCount.setWebvulMiddle(0);
        resultLevelCount.setInfoLeakMiddle(0);
        resultLevelCount.setCgiMiddle(0);
        resultLevelCount.setCsrfMiddle(0);
        resultLevelCount.setFormCrackMiddle(0);
        resultLevelCount.setBlackLinksHigh(0);
        resultLevelCount.setMalscanHigh(0);
        resultLevelCount.setKeywordHigh(0);
        resultLevelCount.setSqlInjectionHigh(0);
        resultLevelCount.setXssHigh(0);
        resultLevelCount.setWebvulHigh(0);
        resultLevelCount.setInfoLeakHigh(0);
        resultLevelCount.setCgiHigh(0);
        resultLevelCount.setCsrfHigh(0);
        resultLevelCount.setFormCrackHigh(0);

        Integer highLevel = riskLevelService.getRiskLevel("high").getLevel();
        Integer middleLevel = riskLevelService.getRiskLevel("middle").getLevel();
        Integer lowLevel = riskLevelService.getRiskLevel("low").getLevel();

        for (ResultEvent resultEvent : resultEvents) {
            JSONArray values = JSONArray.parseArray(resultEvent.getValue());
            for (Object valueObject : values) {
                JSONObject value = (JSONObject) valueObject;
                Integer riskLevel = value.getInteger("risk_level");

                switch (value.getString("type")) {
                    case "blackLinks":
                        if (riskLevel >= highLevel) {
                            resultLevelCount.setBlackLinksHigh(resultLevelCount.getBlackLinksHigh() + 1);
                        } else if (riskLevel >= middleLevel) {
                            resultLevelCount.setBlackLinksMiddle(resultLevelCount.getBlackLinksMiddle() + 1);
                        } else if (riskLevel >= lowLevel) {
                            resultLevelCount.setBlackLinksLow(resultLevelCount.getBlackLinksLow() + 1);
                        }
                        break;
                    case "malscan":
                        if (riskLevel >= highLevel) {
                            resultLevelCount.setMalscanHigh(resultLevelCount.getBlackLinksHigh() + 1);
                        } else if (riskLevel >= middleLevel) {
                            resultLevelCount.setMalscanMiddle(resultLevelCount.getBlackLinksMiddle() + 1);
                        } else if (riskLevel >= lowLevel) {
                            resultLevelCount.setMalscanLow(resultLevelCount.getBlackLinksLow() + 1);
                        }
                        break;
                    case "keyword":

                        if (riskLevel >= highLevel) {
                            resultLevelCount.setBlackLinksHigh(resultLevelCount.getBlackLinksHigh() + 1);
                        } else if (riskLevel >= middleLevel) {
                            resultLevelCount.setBlackLinksHigh(resultLevelCount.getBlackLinksMiddle() + 1);
                        } else if (riskLevel >= lowLevel) {
                            resultLevelCount.setBlackLinksHigh(resultLevelCount.getBlackLinksLow() + 1);
                        }
                        break;
                    case "sql":
                        if (riskLevel >= highLevel) {
                            resultLevelCount.setSqlInjectionHigh(resultLevelCount.getSqlInjectionHigh() + 1);
                        } else if (riskLevel >= middleLevel) {
                            resultLevelCount.setSqlInjectionMiddle(resultLevelCount.getSqlInjectionMiddle() + 1);
                        } else if (riskLevel >= lowLevel) {
                            resultLevelCount.setSqlInjectionLow(resultLevelCount.getSqlInjectionLow() + 1);
                        }
                        break;
                    case "xss":

                        if (riskLevel >= highLevel) {
                            resultLevelCount.setXssHigh(resultLevelCount.getXssHigh() + 1);
                        } else if (riskLevel >= middleLevel) {
                            resultLevelCount.setXssMiddle(resultLevelCount.getXssMiddle() + 1);
                        } else if (riskLevel >= lowLevel) {
                            resultLevelCount.setXssLow(resultLevelCount.getXssLow() + 1);
                        }
                        break;
                    case "webvul":

                        if (riskLevel >= highLevel) {
                            resultLevelCount.setWebvulHigh(resultLevelCount.getWebvulHigh() + 1);
                        } else if (riskLevel >= middleLevel) {
                            resultLevelCount.setWebvulMiddle(resultLevelCount.getWebvulMiddle() + 1);
                        } else if (riskLevel >= lowLevel) {
                            resultLevelCount.setWebvulLow(resultLevelCount.getWebvulLow() + 1);
                        }
                        break;
                    case "infoLeak":
                        if (riskLevel >= highLevel) {
                            resultLevelCount.setInfoLeakHigh(resultLevelCount.getInfoLeakHigh() + 1);
                        } else if (riskLevel >= middleLevel) {
                            resultLevelCount.setInfoLeakMiddle(resultLevelCount.getInfoLeakMiddle() + 1);
                        } else if (riskLevel >= lowLevel) {
                            resultLevelCount.setInfoLeakLow(resultLevelCount.getInfoLeakLow() + 1);
                        }
                        break;
                    case "cgi":

                        if (riskLevel >= highLevel) {
                            resultLevelCount.setCgiHigh(resultLevelCount.getCgiHigh() + 1);
                        } else if (riskLevel >= middleLevel) {
                            resultLevelCount.setCgiMiddle(resultLevelCount.getCgiMiddle() + 1);
                        } else if (riskLevel >= lowLevel) {
                            resultLevelCount.setCgiLow(resultLevelCount.getCgiLow() + 1);
                        }
                        break;
                    case "csrf":
                        if (riskLevel >= highLevel) {
                            resultLevelCount.setCsrfHigh(resultLevelCount.getCsrfHigh() + 1);
                        } else if (riskLevel >= middleLevel) {
                            resultLevelCount.setCsrfMiddle(resultLevelCount.getCsrfMiddle() + 1);
                        } else if (riskLevel >= lowLevel) {
                            resultLevelCount.setCsrfLow(resultLevelCount.getCsrfLow() + 1);
                        }
                        break;
                    case "formCrack":
                        if (riskLevel >= highLevel) {
                            resultLevelCount.setFormCrackHigh(resultLevelCount.getFormCrackHigh() + 1);
                        } else if (riskLevel >= middleLevel) {
                            resultLevelCount.setFormCrackMiddle(resultLevelCount.getFormCrackMiddle() + 1);
                        } else if (riskLevel >= lowLevel) {
                            resultLevelCount.setFormCrackLow(resultLevelCount.getFormCrackLow() + 1);
                        }
                        break;
                }
            }
        }
        map.put("count", resultLevelCount);
        return map;
    }

}
