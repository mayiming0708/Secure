package com.shtel.secure.platform.receive.action;

import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.service.FinishTypeService;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.model.mapper.ResultEventMapper;
import com.shtel.secure.platform.receive.service.ResultEventService;
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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "任务结果查询", description = "ResultEvent")
@RestController
@RequestMapping(value = "/service")
public class ResultEventAction {

    private static final Logger logger = LoggerFactory.getLogger(ResultEventAction.class);

    @Autowired
    private ResultEventService resultEventService;
    @Autowired
    private FinishTypeService finishTypeService;

    @ApiOperation(value = "根据resultEvent id查询返回的resultEvent结果", tags = "任务结果查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "resultEventId", required = true, dataType = "String")
    })
    @RequestMapping(value = "/resultEvent", method = RequestMethod.GET)
    public ResultEvent resultEventById(@RequestParam("id") String id) {
        return resultEventService.getResultEventById(id);
    }


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
    public Map<String, ResultEvent> resultEventByGroup(@RequestParam("virtualGroupId") String virtualGroupId, @RequestParam("url") String url) {
        Map<String, ResultEvent> map = new HashMap<>();
        try {
            FinishType finishType = finishTypeService.getFinishTypeByGourpIdAndUrl(virtualGroupId, url);
            for (Field field : finishType.getClass().getDeclaredFields()) {
                if ("id".equals(field.getName()) || "virtualGroupId".equals(field.getName()) || "url".equals(field.getName()) || "score".equals(field.getName()) || "riskHighCount".equals(field.getName()) || "riskMiddleCount".equals(field.getName()) || "riskLowCount".equals(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                map.put(field.getName(), resultEventService.getResultEventById((String) field.get(finishType)));
            }
        } catch (Exception e) {
            logger.info("|获取任务结果失败：" + e);
        }
        return map;
    }
}
