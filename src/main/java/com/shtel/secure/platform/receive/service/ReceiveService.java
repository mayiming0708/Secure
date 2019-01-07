package com.shtel.secure.platform.receive.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.model.Temp;
import com.shtel.secure.platform.receive.model.mapper.ResultEventMapper;
import com.shtel.secure.platform.receive.model.mapper.TempMapper;
import com.shtel.secure.utils.MapObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReceiveService {
    private static final Logger logger = LoggerFactory.getLogger(ReceiveService.class);

    @Autowired
    private TempMapper tempMapper;

    @Autowired
    private ResultEventMapper resultEventMapper;

    public int insert(String value) {
        Temp temp = new Temp();
        temp.setValue(value);

        return tempMapper.insert(temp);
    }

    public int resultEventInsert(ResultEvent resultEvent) {
        return resultEventMapper.insert(resultEvent);
    }
}
