package com.shtel.secure.platform.receive.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.enumType.model.EnumType;
import com.shtel.secure.platform.finishType.model.FinishType;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.service.IssueService;
import com.shtel.secure.platform.receive.model.ResultEvent;
import com.shtel.secure.platform.receive.model.ResultLevelCount;
import com.shtel.secure.platform.receive.model.Temp;
import com.shtel.secure.platform.receive.model.mapper.ResultEventMapper;
import com.shtel.secure.platform.receive.model.mapper.ResultLevelCountMapper;
import com.shtel.secure.platform.receive.model.mapper.TempMapper;
import com.shtel.secure.utils.EmailUtil;
import com.shtel.secure.utils.ResultUtil;
import com.shtel.secure.utils.SMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultEventService {
    private static final Logger logger = LoggerFactory.getLogger(ResultEventService.class);

    @Autowired
    private TempMapper tempMapper;

    @Autowired
    private ResultEventMapper resultEventMapper;

    @Autowired
    private ResultLevelCountMapper resultLevelCountMapper;

    @Value("${email.account}")
    private String accout;
    @Value("${email.password}")
    private String password;
    @Value("${phone.uid}")
    private String uid;
    @Value("${phone.key}")
    private String key;

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
        Example example = new Example(ResultEvent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("virtualGroupId", virtualGroupId);
        if (url != null && !"".equals(url)) {
            criteria.andEqualTo("site", url);
        }
        example.setOrderByClause("ws_result_event.receive_time DESC");
        return resultEventMapper.selectByExample(example);
    }

    public List<ResultEvent> getResultEventsByUserIdAndUrl(Integer userId, String url) {
        return resultEventMapper.getResultEventsByUserIdAndUrl(userId, url);
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


    public String sendFinishMail(String receiveAddress,String startAt,String endAt) {
        try {
            EmailUtil.sendMail(accout, password, accout, receiveAddress, "您于"+startAt+"下发的任务已于"+endAt+"完成，请登录网站查看", "webSock网站监测任务已完成:http://39.96.45.191/websock-server/ws/index");
            return ResultUtil.Result(EnumType.SUCCESS);
        } catch (Exception e) {
            return ResultUtil.Result(EnumType.EMAIL_ERROR);
        }
    }

    public String sendSMS(String smsMob, String startAt,String endAt){
       try{
        SMSUtil.sendMessage(uid,key,smsMob,"您于"+startAt+"下发的任务已于"+endAt+"完成，请登录网站查看:http://39.96.45.191/websock-server/ws/index");
           return ResultUtil.Result(EnumType.SUCCESS);
       }catch (Exception e){
           return ResultUtil.Result(EnumType.EMAIL_ERROR);
       }
    }

    public int insertResultLevelCount(ResultLevelCount resultLevelCount) {
        return resultLevelCountMapper.insert(resultLevelCount);
    }

    public ResultLevelCount getResultLevelCountByUserId(Integer userId) {
        return resultLevelCountMapper.selectByPrimaryKey(userId);
    }

    public int updateResultLevelCount(ResultLevelCount resultLevelCount) {
        return resultLevelCountMapper.updateByPrimaryKeySelective(resultLevelCount);
    }
}
