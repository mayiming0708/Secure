package com.shtel.secure.platform.receive.action;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.finishType.service.FinishTypeService;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.service.ResultEventService;
import com.shtel.secure.platform.type.model.Type;
import com.shtel.secure.platform.type.service.TypeService;
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


    /**
     * 回调接口
     *
     * @param request
     * @param response
     * @param parameter
     * @return
     */
    @RequestMapping(value = "/inform", method = RequestMethod.POST)
    public int receiveData(HttpServletRequest request, HttpServletResponse response, @RequestParam("parameter") String parameter) {

        JSONObject oneJsonObject = (JSONObject) JSONObject.parse(parameter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ResultEvent resultEvent = new ResultEvent();
            String id = UUID.randomUUID().toString();
            resultEvent.setId(id);
            resultEvent.setReceiveTime(new Date());

            String virtualGroupId = oneJsonObject.getString("virtual_group_id");
            resultEvent.setVirtualGroupId(virtualGroupId);
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
            finishType.setGroupId(groupId);
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
            logger.info("|ERROR");
        }


        return resultEventService.insert(parameter);
    }

}
