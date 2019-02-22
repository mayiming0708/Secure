package com.shtel.secure.platform.issue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.model.mapper.TaskMapper;
import com.shtel.secure.platform.login.model.User;
import com.shtel.secure.utils.HttpUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/7 15:53
 * @Description: 下发任务服务层
 */
@Service
public class IssueService {
    private static Logger logger = LogManager.getLogger(IssueService.class.getName());
    @Autowired
    private TaskMapper taskMapper;
    @Value("${myProps.tempURL}")
    private String tempUrl;
    @Value("${myProps.periodURL}")
    private String periodURL;
    @Value("${myProps.loginAuthURL}")
    private String loginAuthURL;
    @Value("${myProps.progressTempURL}")
    private String progressTempURL;
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
        return issueTask(tempUrl, task);
    }

    /**
     * <p>下发周期任务</p>
     *
     * @param task
     * @return
     */
    public JSONObject issuePeriodTask(Task task) {
        return issueTask(periodURL, task);
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
        logMap.put("parameter", logJson.toJSONString());
        JSONObject taskResponse;
        try {
            taskResponse = HttpUtils.doPostWithCookies(url, loginAuthURL, taskMap, logMap);
        } catch (Exception e) {
            JSONObject content = new JSONObject();
            taskResponse = Response("secure下发任务失败", 100, content);
            logger.info("secure下发任务失败:" + e.getMessage());
        }
        return taskResponse;
    }

    /**
     * <p>获取临时组检测进度</p>
     *
     * @param virtual_group_id
     * @return
     */
    public JSONObject progressTempResponse(String virtual_group_id) {
        JSONObject response;
        Map<String, String> groupMap = new HashMap<>();
        JSONObject virtualGroupId = new JSONObject();
        virtualGroupId.put("virtual_group_id", virtual_group_id);
        groupMap.put("parameter", virtualGroupId.toJSONString());
        Map<String, String> logMap = new HashMap<>();
        JSONObject logJson = new JSONObject();
        logJson.put("username", username);
        logJson.put("password", password);
        logMap.put("parameter", logJson.toJSONString());
        try {
            response = HttpUtils.doPostWithCookies(progressTempURL, loginAuthURL, groupMap, logMap);
        } catch (Exception e) {
            JSONObject content = new JSONObject();
            response = Response("secure获取临时组检测进度", 100, content);
            logger.info("secure获取临时组检测进度:" + e.getMessage());
        }
        return response;
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
        plugin.put("dns", getParameter(true));
        plugin.put("dns_hijack", getParameter(true));
        plugin.put("ping", getParameter(true));
        plugin.put("http_get", getParameter(true));
        plugin.put("http_get_full_time", getParameter(true));
        if (0 == task.getBlackLinks()) {
            plugin.put("black_links", getParameter(false));
        } else {
            plugin.put("black_links", getParameter(true));
        }
        if (0 == task.getMalscan()) {
            plugin.put("malscan", getParameter(false));
        } else {
            plugin.put("malscan", getParameter(true));
        }
        if (0 == task.getKeyword()) {
            plugin.put("keyword", getParameter(false));
        } else {
            plugin.put("keyword", getParameter(true));
        }
        if (0 == task.getSqlInjection()) {
            plugin.put("sql", getParameter(false));
        } else {
            plugin.put("sql", getParameter(true));
        }
        if (0 == task.getXss()) {
            plugin.put("xss", getParameter(false));
        } else {
            plugin.put("xss", getParameter(true));
        }
        if (0 == task.getWebvul()) {
            plugin.put("webvul", getParameter(false));
        } else {
            plugin.put("webvul", getParameter(true));
        }
        if (0 == task.getCgi()) {
            plugin.put("cgi", getParameter(false));
        } else {
            plugin.put("cgi", getParameter(true));
        }
        if (0 == task.getCsrf()) {
            plugin.put("csrf", getParameter(false));
        } else {
            plugin.put("csrf", getParameter(true));
        }
        if (0 == task.getFormCrack()) {
            plugin.put("form_crack", getParameter(false));
        } else {
            plugin.put("form_crack", getParameter(true));
        }
        if (0 == task.getInfoLeak()) {
            JSONObject info_leak = new JSONObject();
            info_leak.put("enabled", false);
            info_leak.put("items", new JSONArray());
            plugin.put("info_leak", info_leak);
        } else {
            JSONObject info_leak = new JSONObject();
            info_leak.put("enabled", true);
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
        String json = jsonObject.toJSONString();
        return jsonObject;
    }

    public JSONObject getParameter(boolean flag) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enabled", flag);
        return jsonObject;
    }

    /**
     * <p>数据插入</p>
     *
     * @param task
     */
    public void innsert(Task task) {
        taskMapper.insertTaskRecord(task);
    }

    /**
     * <p>virtual_group_id字段检测</p>
     *
     * @param virtual_group_id
     * @return
     */
    public JSONObject validateNULL(String virtual_group_id) {
        if (virtual_group_id == null || "".equals(virtual_group_id))
            return Response("输入任务id为空", 100, new JSONObject());
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("virtualGroupId", virtual_group_id);
        int count = taskMapper.selectCountByExample(example);
        if (count < 1)
            return Response("临时任务不存在", 100, new JSONObject());
        return Response("任务存在", 0, new JSONObject());
    }

    /**
     * <p>根据任务id更新任务完成度</p>
     *
     * @param virtual_group_id
     * @return
     */
    public JSONObject updateFinishRate(String virtual_group_id) {
        JSONObject result = validateNULL(virtual_group_id);
        if (result.getInteger("code") != 0) {
            return result;
        } else {
            JSONObject response = progressTempResponse(virtual_group_id).getJSONObject("content");
            if (response.getInteger("code") == 0) {
                float sitesCount = (response.getJSONObject("result")).getFloat("sites_count");
                float sitesDoneCount = (response.getJSONObject("result")).getFloat("sites_done_count");
                DecimalFormat df = new DecimalFormat("#.00");
                float finishRate = Float.valueOf(df.format(sitesDoneCount / sitesCount));
                try {
                    int rows = taskMapper.updateFinishRate(finishRate, virtual_group_id);
                    return Response("更新finshRate成功", 0, new JSONObject());
                } catch (Exception e) {
                    String message = e.getMessage();
                    JSONObject content = new JSONObject();
                    content.put("exception", message);
                    return Response("更新finshRate失败", 100, content);
                }
            }
            return Response(response.getString("message"),response.getInteger("code"),new JSONObject());
        }
    }

    /**
     * <p>响应结果封装</p>
     *
     * @param message
     * @param code
     * @param content
     * @return
     */
    public static JSONObject Response(String message, int code, JSONObject content) {
        JSONObject response = new JSONObject();
        response.put("message", message);
        response.put("code", code);
        response.put("content", content);
        return response;
    }

    public void taskProcessRecord(JSONObject response, Task task, String userId) {
        if (response.getInteger("code") == 0) {
            JSONObject result = response.getJSONObject("result");
            task.setVirtualGroupId(result.getString("virtual_group_id"));
            task.setIsSuccess(1);
            task.setMessage(response.getString("message"));
        } else {
            //若下发不成功，virtual_group_id为下发任务时间戳
            task.setIsSuccess(0);
            JSONObject messageJSON=response.getJSONObject("message");
            if(messageJSON.keySet().size()==0) {
                task.setMessage(response.getJSONObject("message").toJSONString());
            }else{
                for (String key:messageJSON.keySet()){
                    task.setMessage((response.getJSONObject("message")).getJSONArray(key).getString(0));
                }
            }
        }
        task.setUserId(Integer.parseInt(userId));
    }

    public Task getUserByVirtualGroupId(String virtualGroupId) {
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("virtualGroupId", virtualGroupId);
        return taskMapper.selectOneByExample(example);
    }

    public List<Task> getTasksByUserId(Integer userId) {
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        return taskMapper.selectByExample(example);
    }

    public Task getTaskById(Integer taskId){
        return taskMapper.selectByPrimaryKey(taskId);
    }
}