package com.sl.happylife.greeterschedule.job;

import java.lang.reflect.Method;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

public class QuartzOnTimeJob implements Job {

    private ApplicationContext context;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        Class beanClass = null;

        Object beanClassOrName = jobDataMap.get("beanClass");
        if (beanClassOrName instanceof Class) {
            beanClass = (Class) beanClassOrName;
        } else {
            try {
                beanClass = Class.forName((String) beanClassOrName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        String methodName = jobDataMap.getString("methodName");
        Object params = jobDataMap.get("params");

        try {
            Method method;
            Object facadeImplObject = context.getBean(Class.forName(beanClass.getName()));
            Class<?> classOfObject = this.getClassOfObject(params);
            if (!ObjectUtils.isEmpty(params)) {
                method = facadeImplObject.getClass().getMethod(methodName, classOfObject);
                method.invoke(facadeImplObject, params);
            } else {
                method = facadeImplObject.getClass().getMethod(methodName);
                method.invoke(facadeImplObject);
            }
        } catch (Exception ex) {
            Scheduler scheduler = jobExecutionContext.getScheduler();
            try {
                scheduler.deleteJob(jobDetail.getKey());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }

            ex.printStackTrace();
        }
    }

    private Class<?> getClassOfObject(Object params) {
        return params.getClass();
    }

    @Autowired
    public void setContent(ApplicationContext context) {
        this.context = context;
    }
}
