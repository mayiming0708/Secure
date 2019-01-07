package com.shtel.secure.platform.receive.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.service.ReceiveService;
import com.shtel.secure.platform.type.moel.Type;
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

        JSONArray jsonArray = JSONArray.parseArray(parameter);
        for (Object jsonObject : jsonArray) {
            JSONObject oneJsonObject = (JSONObject) jsonObject;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                ResultEvent resultEvent = new ResultEvent();
                resultEvent.setUrl(oneJsonObject.getString("url"));
                resultEvent.setCreatedAt(simpleDateFormat.parse(oneJsonObject.getString("created_at")));
                resultEvent.setReceiveTime(new Date());
                Type type = typeService.getTypeByNameEn(oneJsonObject.getString("type"));
                resultEvent.setType(String.valueOf(type.getId()));
                resultEvent.setValue(oneJsonObject.getString("value"));

                receiveService.resultEventInsert(resultEvent);
            } catch (Exception e) {
                logger.info("|ERROR");
            }
        }

        return receiveService.insert(parameter);
    }

}
