package com.shtel.secure.platform.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.shtel.secure.platform.quartz.demo.MyJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DemoService {

    @Autowired
    private Scheduler scheduler;


    public JSONObject start() throws Exception {
        JSONObject jobKeyJson = new JSONObject();
        //设置开始时间为1分钟后
        long startAtTime = System.currentTimeMillis();
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = MyJob.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(name, group).build();
        //cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("* * * * * ? *");
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTime)).withSchedule(cronScheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);

        jobKeyJson.put("jobName", jobDetail.getKey().getName());
        jobKeyJson.put("jobGroup", jobDetail.getKey().getGroup());
        return jobKeyJson;
    }

    public boolean stopJobs() {
        try {
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();
                    //get job's trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    for (Trigger trigger : triggers) {
                        scheduler.unscheduleJob(trigger.getKey());
                    }
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean stopJob(String name,String group) {
        JobKey jobKey = new JobKey(name, group);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void getJobs() {
        try {
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    TriggerKey triggerKey = triggers.get(0).getKey();
                    System.out.println(triggerKey.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
