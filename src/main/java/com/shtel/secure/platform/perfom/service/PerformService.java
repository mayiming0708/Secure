package com.shtel.secure.platform.perfom.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.shtel.secure.platform.issue.model.Task;
import com.shtel.secure.platform.issue.model.mapper.TaskMapper;
import com.shtel.secure.platform.issue.service.IssueService;
import com.shtel.secure.platform.perfom.model.PageBean;
import com.shtel.secure.platform.perfom.model.Perform;
import com.shtel.secure.platform.perfom.model.PerformLevelCount;
import com.shtel.secure.platform.perfom.model.PerformReq;
import com.shtel.secure.platform.perfom.model.mapper.BasicMapper;
import com.shtel.secure.platform.perfom.model.mapper.PerformLevelCountMapper;
import com.shtel.secure.platform.perfom.model.mapper.PerformMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

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
    @Autowired
    private BasicMapper basicMapper;
    @Autowired
    private PerformLevelCountMapper performLevelCountMapper;

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
     * @return
     */
    public JSONObject selectWebDetail(String userId) {
        List<Perform> performs = performMapper.selectWebDetail(userId);
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
     * @param performReq
     * @return
     */
    public JSONObject selectWebPage(PerformReq performReq) {
        logger.info("根据用户id是否周期查询站点列表（分页）");
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(performReq.getCurrentPage(), performReq.getPageSize());
        List<Perform> performs = basicMapper.selectWebDetail(performReq);
        int counts = basicMapper.countWebDetail(performReq);
        PageBean<Perform> pageData = new PageBean<>(performReq.getCurrentPage(), performReq.getPageSize(), counts);
        pageData.setItems(performs);
        JSONObject response = new JSONObject();
        response.put("total", counts);
        response.put("rows", pageData.getItems());
        return response;
    }

    /**
     * <p>根据用户Id查询所有任务(分页)</p>
     *
     * @param performReq
     * @return
     */
    public JSONObject selectTaskPage(PerformReq performReq) {
        logger.info("根据用户Id查询所有任务(分页)");
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(performReq.getCurrentPage(), performReq.getPageSize());
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", performReq.getUserId());
        criteria.andEqualTo("status", 0);
        criteria.andEqualTo("isSuccess", 1);
        if (performReq.getCreateTime() != null)
            criteria.andLike("createTime", performReq.getCreateTime() + "%");
        if (performReq.getIsPeriod() != null)
            criteria.andEqualTo("isPeriod", performReq.getIsPeriod());
        example.setOrderByClause("create_time DESC");
        List<Task> tasks = taskMapper.selectByExample(example);
        int counts = taskMapper.selectCountByExample(example);
        PageBean<Task> pageData = new PageBean<>(performReq.getCurrentPage(), performReq.getPageSize(), counts);
        pageData.setItems(tasks);
        JSONObject response = new JSONObject();
        response.put("total", counts);
        response.put("rows", pageData.getItems());
        return response;
    }

    /**
     * 获取展示页所需数据
     *
     * @return
     */
    public JSONObject getPerformData(Integer userId) {
        logger.info("获取展示页所需数据");
        int taskCounts = performMapper.countTask(userId);
        PerformReq perform = performMapper.countWebAndBugCounts(userId);
        PerformLevelCount performLevelCount=new PerformLevelCount();
        performLevelCount.setUserId(userId);
        performLevelCount=performLevelCountMapper.selectOne(performLevelCount);
        if (performLevelCount==null){
            performLevelCount=new PerformLevelCount();
            performLevelCount.setBlackLinksHigh(0);
            performLevelCount.setBlackLinksMiddle(0);
            performLevelCount.setBlackLinksLow(0);
            performLevelCount.setMalscanHigh(0);
            performLevelCount.setMalscanMiddle(0);
            performLevelCount.setMalscanLow(0);
            performLevelCount.setCgiHigh(0);
            performLevelCount.setCgiMiddle(0);
            performLevelCount.setCgiLow(0);
            performLevelCount.setFormCrackHigh(0);
            performLevelCount.setFormCrackMiddle(0);
            performLevelCount.setFormCrackLow(0);
            performLevelCount.setInfoLeakHigh(0);
            performLevelCount.setInfoLeakMiddle(0);
            performLevelCount.setInfoLeakLow(0);
            performLevelCount.setWebvulHigh(0);
            performLevelCount.setWebvulMiddle(0);
            performLevelCount.setWebvulLow(0);
            performLevelCount.setXssHigh(0);
            performLevelCount.setXssMiddle(0);
            performLevelCount.setXssLow(0);
            performLevelCount.setCsrfHigh(0);
            performLevelCount.setCsrfMiddle(0);
            performLevelCount.setCsrfLow(0);
            performLevelCount.setKeywordHigh(0);
            performLevelCount.setKeywordMiddle(0);
            performLevelCount.setKeywordLow(0);
            performLevelCount.setSqlInjectionHigh(0);
            performLevelCount.setSqlInjectionMiddle(0);
            performLevelCount.setSqlInjectionLow(0);
            }
        JSONObject levelCount=new JSONObject();
        JSONObject blackLinks=new JSONObject();
        blackLinks.put("high",performLevelCount.getBlackLinksHigh());
        blackLinks.put("middle",performLevelCount.getBlackLinksMiddle());
        blackLinks.put("low",performLevelCount.getBlackLinksLow());
        levelCount.put("blackLinks",blackLinks);
        JSONObject malscan=new JSONObject();
        malscan.put("high",performLevelCount.getMalscanHigh());
        malscan.put("middle",performLevelCount.getMalscanMiddle());
        malscan.put("low",performLevelCount.getBlackLinksLow());
        levelCount.put("malscan",malscan);
        JSONObject keyword=new JSONObject();
        keyword.put("high",performLevelCount.getKeywordHigh());
        keyword.put("middle",performLevelCount.getKeywordMiddle());
        keyword.put("low",performLevelCount.getKeywordLow());
        levelCount.put("keyword",keyword);
        JSONObject sql=new JSONObject();
        sql.put("high",performLevelCount.getSqlInjectionHigh());
        sql.put("middle",performLevelCount.getSqlInjectionMiddle());
        sql.put("low",performLevelCount.getSqlInjectionMiddle());
        levelCount.put("sql",sql);
        JSONObject xss=new JSONObject();
        xss.put("high",performLevelCount.getXssHigh());
        xss.put("middle",performLevelCount.getXssMiddle());
        xss.put("low",performLevelCount.getXssLow());
        levelCount.put("xss",xss);
        JSONObject webvul=new JSONObject();
        webvul.put("high",performLevelCount.getWebvulHigh());
        webvul.put("middle",performLevelCount.getWebvulMiddle());
        webvul.put("low",performLevelCount.getWebvulLow());
        levelCount.put("webvul",webvul);
        JSONObject infoLeak=new JSONObject();
        infoLeak.put("high",performLevelCount.getInfoLeakHigh());
        infoLeak.put("middle",performLevelCount.getInfoLeakMiddle());
        infoLeak.put("low",performLevelCount.getInfoLeakLow());
        levelCount.put("infoLeak",infoLeak);
        JSONObject cgi=new JSONObject();
        cgi.put("high",performLevelCount.getCgiHigh());
        cgi.put("middle",performLevelCount.getCgiMiddle());
        cgi.put("low",performLevelCount.getCgiLow());
        levelCount.put("cgi",cgi);
        JSONObject csrf=new JSONObject();
        csrf.put("high",performLevelCount.getCsrfHigh());
        csrf.put("middle",performLevelCount.getCsrfMiddle());
        csrf.put("low",performLevelCount.getCsrfLow());
        levelCount.put("csrf",csrf);
        JSONObject formCrack=new JSONObject();
        formCrack.put("high",performLevelCount.getFormCrackHigh());
        formCrack.put("middle",performLevelCount.getFormCrackMiddle());
        formCrack.put("low",performLevelCount.getFormCrackLow());
        levelCount.put("formCrack",formCrack);
        int webCounts = perform.getUrlCount();
        int higBugs = perform.getRiskHighCount();
        int middleBugs = perform.getRiskMiddleCount();
        int lowBugs = perform.getRiskLowCount();
        int availabilityCount = perform.getAvailabilityCount();
        int siteinfoCount = perform.getSiteinfoCount();
        int bugCounts = higBugs + middleBugs + lowBugs;
        int useTime = performMapper.getAvgWebTime(userId);
        PerformReq performReq = performMapper.getBugCount(userId);
        Map<String, Integer> map = new HashMap<>();
        map.put("暗链", performReq.getBlackLinks());
        map.put("挂马", performReq.getMalscan());
        map.put("sql注入", performReq.getSqlInjection());
        map.put("XSS跨站脚本漏洞", performReq.getXss());
        map.put("应用漏洞", performReq.getWebvul());
        map.put("信息泄露", performReq.getInfoLeak());
        map.put("关键词", performReq.getKeyword());
        map.put("CSRF跨站请求伪造漏洞", performReq.getCgi());
        map.put("CGI漏洞", performReq.getCgi());
        map.put("表单破解漏洞", performReq.getFormCrack());
        JSONArray bugTop = new JSONArray();
        List<Map.Entry<String, Integer>> mapList = new ArrayList<>(map.entrySet());
        Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Collections.reverse(mapList);
        for (Map.Entry<String, Integer> mapping : mapList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(mapping.getKey(), mapping.getValue());
            if ("CSRF跨站请求伪造漏洞".equals(mapping.getKey()) || "关键词".equals(mapping.getKey())) {
                jsonObject.put("level", "middle");
            } else {
                jsonObject.put("level", "high");
            }
            bugTop.add(jsonObject);
        }
        JSONObject response = new JSONObject();
        response.put("taskCounts", taskCounts);
        response.put("webCounts", webCounts);
        response.put("higBugs", higBugs);
        response.put("middleBugs", middleBugs);
        response.put("lowBugs", lowBugs);
        response.put("availabilityCount", availabilityCount);
        response.put("siteinfoCount", siteinfoCount);
        response.put("bugCounts", bugCounts);
        response.put("topBug", bugTop);
        response.put("avgUseTime", useTime);
        response.put("levelRecord",levelCount);
        List<PerformReq> urlTop = performMapper.getTopUrl(userId);
        JSONArray topURL = new JSONArray();
        for (PerformReq performReq1 : urlTop) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", performReq1.getUrl());
            jsonObject.put("score", performReq1.getScore());
            topURL.add(jsonObject);
        }
        response.put("topURL", topURL);
        return response;
    }

    /**
     * <p>根据virtual_group_id，逻辑删除记录</p>
     *
     * @param virtual_group_id
     * @return
     */
    public JSONObject deleteRecord(String virtual_group_id) {
        logger.info("删除任务");
        int rows = performMapper.deleteTask(virtual_group_id);
        int rows2 = performMapper.deleteURL(virtual_group_id);
        if (rows == 0)
            return IssueService.Response("删除Task任务失败", 100, new JSONObject());
        if (rows2 == 0)
            return IssueService.Response("删除URL任务失败", 100, new JSONObject());
        return IssueService.Response("删除任务成功", 0, new JSONObject());
    }

    /**
     * <p>站点信息时间排序列表</p>
     *
     * @param url
     * @return
     */
    public JSONObject getWebDetailListDESC(String url){
        logger.info("站点信息时间排序列表");
        List<PerformReq> list= basicMapper.getWebDetailListDESC(url);
        JSONObject response=new JSONObject();
        response.put("riskHighCount",0);
        response.put("riskMiddleCount",0);
        response.put("riskLowCount",0);
        response.put("blackLinks",0);
        response.put("malscan",0);
        response.put("sqlInjection",0);
        response.put("webvul",0);
        response.put("infoLeak",0);
        response.put("cgi",0);
        response.put("formCrack",0);
        response.put("keyword",0);
        response.put("csrf",0);
        response.put("xss",0);
        for (PerformReq performReq:list){
            response.put("riskHighCount",response.getInteger("riskHighCount")+performReq.getRiskHighCount());
            response.put("riskMiddleCount",response.getInteger("riskMiddleCount")+performReq.getRiskMiddleCount());
            response.put("riskLowCount",response.getInteger("riskLowCount")+performReq.getRiskLowCount());
            response.put("blackLinks",response.getInteger("blackLinks")+performReq.getBlackLinks());
            response.put("malscan",response.getInteger("malscan")+performReq.getMalscan());
            response.put("sqlInjection",response.getInteger("sqlInjection")+performReq.getSqlInjection());
            response.put("webvul",response.getInteger("webvul")+performReq.getRiskHighCount());
            response.put("infoLeak",response.getInteger("infoLeak")+performReq.getRiskHighCount());
            response.put("cgi",response.getInteger("cgi")+performReq.getCgi());
            response.put("formCrack",response.getInteger("formCrack")+performReq.getFormCrack());
            response.put("keyword",response.getInteger("keyword")+performReq.getKeyword());
            response.put("csrf",response.getInteger("csrf")+performReq.getCsrf());
            response.put("xss",response.getInteger("xss")+performReq.getXss());
            response.put("finishRate",performReq.getFinishRate());
        }
        response.put("data",list);
        return response;
    }


}
