package com.shtel.secure.platform.perfom.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.model.mapper.TaskMapper;
import com.shtel.secure.platform.perfom.model.PageBean;
import com.shtel.secure.platform.perfom.model.Perform;
import com.shtel.secure.platform.perfom.model.mapper.PerformMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/15 13:52
 * @Description: 页面展示服务层
 */
@Service
public class PerformService {
    private static Logger logger = LogManager.getLogger(PerformService.class.getName());

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private PerformMapper performMapper;

    /**
     * <p>根据用户Id查询所有任务</p>
     *
     * @param userId
     * @return
     */
    public JSONObject selectTaskByUserId(String userId) {
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        example.setOrderByClause("create_time DESC");
        List<Task> tasks = taskMapper.selectByExample(example);
        JSONObject response = new JSONObject();
        response.put("total", tasks.size());
        JSONArray detailArray = new JSONArray();
        for (Task task : tasks) {
            JSONObject detail = new JSONObject();
            detail.put("virtualGroupId", task.getVirtualGroupId());
            detail.put("urls", task.getUrls());
            detail.put("siteinfo", task.getSiteinfo());
            detail.put("availability", task.getAvailability());
            detail.put("blackLinks", task.getBlackLinks());
            detail.put("malscan", task.getMalscan());
            detail.put("keyword", task.getKeyword());
            detail.put("sqlInjection", task.getKeyword());
            detail.put("xss", task.getKeyword());
            detail.put("webvul", task.getKeyword());
            detail.put("cgi", task.getKeyword());
            detail.put("form_crack", task.getKeyword());
            detail.put("csrf", task.getKeyword());
            detail.put("isPeriod", task.getKeyword());
            detail.put("isSuccess", task.getKeyword());
            detail.put("finishRate", task.getKeyword());
            detail.put("createTime", task.getCreateTime());
            if (task.getFinishRate() == 1) {
                detail.put("endTime", task.getUpdateTime());
            }
            detailArray.add(detail);
        }
        response.put("rows", detailArray);
        return response;
    }

    /**
     * <p>根据用户id是否周期查询站点列表</p>
     *
     * @param userId
     * @param isPeriod
     * @return
     */
    public JSONObject selectWebDetail(String userId, int isPeriod) {
        List<Perform> performs = performMapper.selectWebDetail(userId, isPeriod);
        JSONObject response = new JSONObject();
        response.put("total", performs.size());
        JSONArray webArray = new JSONArray();
        for (Perform perform : performs) {
            webArray.add(perform);
        }
        response.put("rows", webArray);
        return response;
    }

    /**
     * <p>根据用户id是否周期查询站点列表（分页）</p>
     *
     * @param currentPage
     * @param pageSize
     * @param userId
     * @param isPeriod
     * @return
     */
    public JSONObject selectWebPage(int currentPage, int pageSize, String userId, int isPeriod) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<Perform> performs = performMapper.selectWebDetail(userId, isPeriod);
        int counts=performMapper.countWebDetail(userId,isPeriod);
        PageBean<Perform> pageData = new PageBean<>(currentPage, pageSize, counts);
        pageData.setItems(performs);
        JSONObject response=new JSONObject();
        response.put("total",counts);
        response.put("rows",pageData.getItems());
        return response;
    }

    /**
     * <p>根据用户Id查询所有任务(分页)</p>
     *
     * @param currentPage
     * @param pageSize
     * @param userId
     * @return
     */
    public JSONObject selectTaskPage(int currentPage, int pageSize, String userId) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        example.setOrderByClause("create_time DESC");
        List<Task> tasks = taskMapper.selectByExample(example);
        int counts=taskMapper.selectCountByExample(example);
        PageBean<Task> pageData = new PageBean<>(currentPage, pageSize, counts);
        pageData.setItems(tasks);
        JSONObject response=new JSONObject();
        response.put("total",counts);
        response.put("rows",pageData.getItems());
        return response;
    }


}
