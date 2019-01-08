package com.shtel.secure.platform.issue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.model.mapper.TaskMapper;
import com.shtel.secure.utils.HttpUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @Value("${myProps.tempURL}")
    private String tempUrl;
    @Value("${myProps.periodURL}")
    private String periodURL;
    @Value("${myProps.loginAuthURL}")
    private String loginAuthURL;
    @Value("${basic.username}")
    private String username;
    @Value("${basic.password}")
    private String password;

    /**
     * <p>下发临时任务</p>
     *
     * @param task
     * @return
     */
    public JSONObject issueTemporaryTask(Task task) {
        return issueTask(tempUrl,task);
    }

    /**
     * <p>下发周期任务</p>
     *
     * @param task
     * @return
     */
    public JSONObject issuePeriodTask(Task task) {
        return issueTask(periodURL,task);
    }

    /**
     * <p>下发任务</p>
     *
     * @param url  请求路径
     * @param task
     * @return
     */
    public JSONObject issueTask(String url, Task task) {
        Map<String, String> taskMap = new HashMap<>();
        taskMap.put("parameter", getParameters(task).toJSONString());
        Map<String, String> logMap = new HashMap<>();
        JSONObject logJson = new JSONObject();
        logJson.put("username", username);
        logJson.put("password", password);
        logMap.put("parameter",logJson.toJSONString());
        JSONObject taskResponse;
        try {
            taskResponse = HttpUtils.doPostWithCookies(url, loginAuthURL, taskMap, logMap);
        } catch (Exception e) {
            taskResponse = new JSONObject();
            JSONObject content=new JSONObject();
            content.put("message", "secure下发任务失败");
            content.put("code",100);
            taskResponse.put("content",content);
            logger.info("secure下发任务失败:" + e.getMessage());
        }
        return taskResponse;
    }

    /**
     * <p>任务参数封装</p>
     *
     * @param task
     * @return
     */
    public JSONObject getParameters(Task task) {
        JSONObject jsonObject = new JSONObject();
        JSONObject plugin = new JSONObject();
        String name = String.valueOf(new Date().getTime());//任务名
        jsonObject.put("name", name);
        task.setVirtualGroupId(name);
        if (0 == task.getBlackLinks()) {
            plugin.put("black_links", getParameter());
        }
        if (0 == task.getBlackLinks()) {
            plugin.put("malscan", getParameter());
        }
        if (0 == task.getBlackLinks()) {
            plugin.put("keyword", getParameter());
        }
        if (0 == task.getBlackLinks()) {
            plugin.put("sql", getParameter());
        }
        if (0 == task.getBlackLinks()) {
            plugin.put("xss", getParameter());
        }
        if (0 == task.getBlackLinks()) {
            plugin.put("webvul", getParameter());
        }
        if (0 == task.getBlackLinks()) {
            plugin.put("cgi", getParameter());
        }
        if (0 == task.getBlackLinks()) {
            plugin.put("csrf", getParameter());
        }
        if (0 == task.getBlackLinks()) {
            plugin.put("form_crack", getParameter());
        }
        if (0 == task.getBlackLinks()) {
            JSONObject info_leak = new JSONObject();
            info_leak.put("enabled", false);
            info_leak.put("items", new JSONArray());
            plugin.put("info_leak", info_leak);
        }
        jsonObject.put("plugin", plugin);
        String[] strings = task.getUrls().split(",");
        JSONArray site_list = new JSONArray();
        for (String url : strings) {
            site_list.add(url);
        }
        jsonObject.put("site_list", site_list);

        return jsonObject;
    }

    public JSONObject getParameter() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enabled", false);
        return jsonObject;
    }

    /**
     * <p>数据插入</p>
     *
     * @param task
     */
    public void innsert(Task task){
        taskMapper.insertTaskRecord(task);
    }

    public void taskProcessRecord(JSONObject response,Task task,String userId){
        if (response.getInteger("code")==0){
            JSONObject result=response.getJSONObject("result");
            task.setVirtualGroupId(result.getString("virtual_group_id"));
            task.setIsSuccess(1);
        }else{
            //若下发不成功，virtual_group_id为下发任务时间戳
            task.setIsSuccess(0);
        }
        task.setMessage(response.getString("message"));
        task.setUserId(Integer.parseInt(userId));
    }


}
