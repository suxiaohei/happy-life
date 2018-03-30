package com.sl.happylife.greeterschedule.quartztest;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 任务实现类
 *
 * @author weishuai
 */
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println(context.getMergedJobDataMap().get("task")
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
    }
}