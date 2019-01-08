package com.shtel.secure.platform.receive.service;

import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.model.Temp;
import com.shtel.secure.platform.receive.model.mapper.ResultEventMapper;
import com.shtel.secure.platform.receive.model.mapper.TempMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultEventService {
    private static final Logger logger = LoggerFactory.getLogger(ResultEventService.class);

    @Autowired
    private TempMapper tempMapper;

    @Autowired
    private ResultEventMapper resultEventMapper;

    public int insert(String value) {
        Temp temp = new Temp();
        temp.setValue(value);

        return tempMapper.insert(temp);
    }

    /**
     * resultEvent插入
     * @param resultEvent
     * @return
     */
    public int resultEventInsert(ResultEvent resultEvent) {
        return resultEventMapper.insert(resultEvent);
    }

    /**
     * 通过ID获取resultEvent结果
     * @param id
     * @return
     */
    public ResultEvent getResultEventById(String id) {
        return resultEventMapper.selectByPrimaryKey(id);
    }
}
