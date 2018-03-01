package com.sl.happylife.greeterschedule.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by suxin on 17-5-10.
 */
public interface QuartzFacade {

    boolean scheduleOnTime(long startTime, String quartzIdentity, Class beanClass, String methodName, JSONObject paramJson);

    boolean scheduleCron(String quartzIdentity, String cronExpress, Class beanClass, String methodName, JSONObject paramJson, boolean isReplace);
}
