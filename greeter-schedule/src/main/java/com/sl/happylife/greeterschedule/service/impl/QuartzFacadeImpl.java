package com.sl.happylife.greeterschedule.service.impl;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import com.alibaba.fastjson.JSONObject;
import com.sl.happylife.greeterschedule.job.QuartzCronJob;
import com.sl.happylife.greeterschedule.job.QuartzOnTimeJob;
import com.sl.happylife.greeterschedule.service.QuartzFacade;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Primary
@Service
public class QuartzFacadeImpl implements QuartzFacade {

    private static final Logger logger = LoggerFactory.getLogger(QuartzFacadeImpl.class);

    private Scheduler scheduler;

    @Override
    public boolean scheduleOnTime(long startTime, String quartzIdentity, Class beanClass, String methodName, JSONObject paramJson) {
        if (paramJson == null) {
            return false;
        }

        if (startTime <= 0) {
            logger.warn(String.format("Quartz job is not set success, because of startTime is error"));
            return false;
        }

        JobDetail jobDetail = JobBuilder.newJob(QuartzOnTimeJob.class)
                .withIdentity(quartzIdentity)
                .storeDurably()
                .build();

        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("beanClass", beanClass.getName());
        jobDataMap.put("methodName", methodName);
        jobDataMap.put("params", paramJson);

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                /*.withSchedule(simpleSchedule()
                    .withMisfireHandlingInstructionFireNow()
                )*/
                .startAt(new Date(startTime))
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean scheduleCron(String quartzIdentity, String cronExpress, Class beanClass, String methodName, JSONObject paramJson,
                                boolean isReplace) {
        if (paramJson == null) {
            return false;
        }

        JobDetail jobDetail = JobBuilder.newJob(QuartzCronJob.class)
                .withIdentity(quartzIdentity)
                .build();

        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("beanClass", beanClass);
        jobDataMap.put("methodName", methodName);
        jobDataMap.put("params", paramJson);

        Trigger trigger;
        try {
            trigger = scheduler.getTrigger(TriggerKey.triggerKey(quartzIdentity));
            boolean isNotExist = ObjectUtils.isEmpty(trigger);
            if (isNotExist || isReplace) {
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(quartzIdentity)
                        .forJob(jobDetail)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpress))
                        .build();
            }
            Set<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(trigger);
            scheduler.scheduleJob(jobDetail, triggerSet, true);
        } catch (SchedulerException e) {
            logger.error("scheduleCron is error", e);
        }

        return true;
    }

    @Autowired
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
