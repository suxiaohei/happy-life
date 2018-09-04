package com.sl.happylife.greeterschedule.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 定时任务接口
 *
 * @author suxin
 */
public interface QuartzFacade {

    /**
     * 制定日期的定时任务
     *
     * @param startTime      startTime
     * @param quartzIdentity quartzIdentity
     * @param beanClass      beanClass
     * @param methodName     methodName
     * @param paramJson      paramJson
     * @return 结果
     */
    boolean scheduleOnTime(
            long startTime, String quartzIdentity, Class beanClass, String methodName, JSONObject paramJson);

    /**
     * 根据语法添加定时任务
     *
     * @param quartzIdentity quartzIdentity
     * @param cronExpress    cronExpress
     * @param beanClass      beanClass
     * @param methodName     methodName
     * @param paramJson      paramJson
     * @param isReplace      isReplace
     * @return 结果
     */
    boolean scheduleCron(
            String quartzIdentity, String cronExpress, Class beanClass, String methodName,
            JSONObject paramJson, boolean isReplace);
}
