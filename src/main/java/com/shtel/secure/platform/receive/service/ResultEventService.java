package com.shtel.secure.platform.receive.service;

import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.model.Temp;
import com.shtel.secure.platform.receive.model.mapper.ResultEventMapper;
import com.shtel.secure.platform.receive.model.mapper.TempMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

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
     *
     * @param resultEvent
     * @return
     */
    public int resultEventInsert(ResultEvent resultEvent) {
        return resultEventMapper.insert(resultEvent);
    }

    /**
     * 通过ID获取resultEvent结果
     *
     * @param id
     * @return
     */
    public ResultEvent getResultEventById(String id) {
        return resultEventMapper.selectByPrimaryKey(id);
    }

    public List<ResultEvent> getResultEventsByGroupAndUrl(String virtualGroupId, String url) {
        List<ResultEvent> resultEvents = resultEventMapper.getResultEventsByGroupAndUrl(virtualGroupId, url);
        return resultEvents;
    }


    public Integer calculationScore(FinishType finishType) {
        Integer score;
        if (finishType.getRiskHighCount() > 0) {
            score = 66 + finishType.getRiskHighCount();
        } else {
            if (finishType.getRiskMiddleCount() > 0) {
                score = 33 + finishType.getRiskMiddleCount();
            } else {
                score = finishType.getRiskLowCount();
            }
        }
        if (score > 100) {
            score = 100;
        }
        return score;
    }
}
