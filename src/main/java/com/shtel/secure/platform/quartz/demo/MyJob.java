package com.shtel.secure.platform.quartz.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * quartz增删改查方法
 */
public class MyJob implements Job {

    public MyJob(){}
    @Override
    //把要执行的操作，写在execute方法中
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println("测试Quartz"+ df.format(Calendar.getInstance().getTime()));
    }
}