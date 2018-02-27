package com.sl.happylife.greeterschedule.quartztest;

import org.quartz.*;
import org.quartz.core.jmx.JobDetailSupport;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuartzTest {

    public static void main(String[] args) throws SchedulerException, ParseException {
        System.out.println(addJob("张科3", MyJob.class, "我是你的人", 10, "aaa"));
        resumeJob();
    }

    private static SchedulerFactory sf = new StdSchedulerFactory();
    private static String TRIGGER_GROUP_NAME = "TRIGGER_GROUP_NAME";

    /**
     * 添加任务
     *
     * @param jobName 任务名称
     * @param job     任务处理类 需要继承Job
     * @param task    处理任务可以获取的上下文
     *                通过context.getMergedJobDataMap().getString("context"); 获取
     * @param seconds 间隔秒
     * @return 0 添加成功 1：任务已经存在 2：添加异常
     */
    public static int addJob(String jobName, Class<? extends Job> job, Object task, int seconds, String jobGorupName) {
        try {
            // 判断任务是否存在
            Scheduler sche = sf.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGorupName);
            if (sche.checkExists(jobKey)) {
                return 1;// 任务已经存在
            }
            // 创建一个JobDetail实例，指定SimpleJob
            Map<String, Object> JobDetailmap = new HashMap<String, Object>();
            JobDetailmap.put("name", jobName);// 设置任务名字
            JobDetailmap.put("group", jobGorupName);// 设置任务组
            JobDetailmap.put("jobClass", job.getCanonicalName());// 指定执行类
            // Task.class.getCanonicalName()
            JobDetail jobDetail = JobDetailSupport.newJobDetail(JobDetailmap);
            // 添加数据内容
            jobDetail.getJobDataMap().put("task", task);// 传输的上下文
            // 通过SimpleTrigger定义调度规则：马上启动，每2秒运行一次，共运行100次 等。。。。
            SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl();
            simpleTrigger.setName(jobName);
            simpleTrigger.setGroup(TRIGGER_GROUP_NAME);
            // 什么时候开始执行
            simpleTrigger.setStartTime(new Date(new Date().getTime() + 1000 * seconds));
            // 间隔时间
            simpleTrigger.setRepeatInterval(1000 * seconds);
            // 最多执行次数 默认执行一次
            simpleTrigger.setRepeatCount(0);
            // 通过SchedulerFactory获取一个调度器实例
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail, simpleTrigger);// ④ 注册并进行调度
            scheduler.start();// ⑤调度启动
            return 0;// 添加成功
        } catch (Exception e) {
            // e.printStackTrace();
            return 2;// 操作异常
        }
    }

    /**
     * 关闭任务调度
     *
     * @param jobName 任务名称
     * @return 0 关闭成功 1： 关闭失败 2：操作异常
     */
    public static int closeJob(String jobName, String jobGorupName) {
        //关闭任务调度
        try {
            Scheduler sche = sf.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGorupName);
            return sche.deleteJob(jobKey) == true ? 0 : 1;
        } catch (SchedulerException e) {
            //e.printStackTrace();
            return 2;
        }
    }

    /**
     * 从数据库中找到已经存在的job，并重新开始调度
     */
    public static void resumeJob() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
