package com.shtel.secure.platform.issue.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.model.mapper.TaskMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/7 15:53
 * @Description: 下发任务服务层
 */
@Service
public class issueService {
    private static Logger logger = LogManager.getLogger(issueService.class.getName());
    @Autowired
    private TaskMapper taskMapper;

    /**
     * <p>下发临时任务</p>
     *
     * @param task
     * @return
     */
    public JSONObject issueTemporaryTask(Task task){
        return null;
    }

    /**
     * <p>下发周期任务</p>
     *
     * @param task
     * @return
     */
    public JSONObject issuePeriodTask(Task task){
        return  null;
    }

    /**
     *<p>下发任务</p>
     *
     * @param url 请求路径
     * @param task
     * @return
     */
    public JSONObject issueTask(String url,Task task){
        return null;
    }

    /**
     * <p>任务参数封装</p>
     *
     * @param task
     * @return
     */
    public JSONObject getParameter(Task task){
        return null;
    }

}
