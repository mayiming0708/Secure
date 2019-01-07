package com.shtel.secure.platform.receive.action;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.service.ReceiveService;
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

@RestController
public class ReceiveAction {
    private static final Logger logger = LoggerFactory.getLogger(ReceiveAction.class);

    @Autowired
    private ReceiveService receiveService;
    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/sock/v1/inform", method = RequestMethod.POST)
    public int receiveData(HttpServletRequest request, HttpServletResponse response, @RequestParam("parameter") String parameter) {

        JSONObject oneJsonObject = (JSONObject) JSONObject.parse(parameter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ResultEvent resultEvent = new ResultEvent();
            resultEvent.setReceiveTime(new Date());
            resultEvent.setVirtualGroupId(oneJsonObject.getString("virtual_group_id"));
            resultEvent.setValue(oneJsonObject.getJSONArray("values").toString());
            resultEvent.setTotal(oneJsonObject.getString("total"));
            resultEvent.setStartAt(simpleDateFormat.parse(oneJsonObject.getString("start_at")));
            resultEvent.setTaskId(oneJsonObject.getString("task_id"));
            Type type = typeService.getTypeByNameEn(oneJsonObject.getString("module_type"));
            resultEvent.setModuleType(String.valueOf(type.getId()));
            resultEvent.setGroupId(oneJsonObject.getString("group_id"));
            resultEvent.setSiteId(oneJsonObject.getString("site_id"));
            resultEvent.setSite(oneJsonObject.getString("site"));
            resultEvent.setEndAt(simpleDateFormat.parse(oneJsonObject.getString("end_at")));

            receiveService.resultEventInsert(resultEvent);
        } catch (Exception e) {
            logger.info("|ERROR");
        }


        return receiveService.insert(parameter);
    }

}
