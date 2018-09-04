package com.sl.happylife.greetercloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sl.happylife.greetercloud.service.SayHiScheduleFacade;
import com.sl.happylife.greeterschedule.service.QuartzFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

/**
 * sayHi接口实现类
 *
 * @author suxin
 */
@Service
public class SayHiScheduleFacadeImpl implements SayHiScheduleFacade {

    private QuartzFacade quartzFacade;

    @Override
    public String sayHi(JSONObject params) {

        Long currentTime = System.currentTimeMillis();
        String quartzIdentity = UUID.randomUUID().toString();
        String name = params.getString("name");

        quartzFacade.scheduleOnTime(
                currentTime + 3000, quartzIdentity,
                SayHiScheduleFacade.class, "sayHi",
                new JSONObject().fluentPut("name", "suxin"));

        System.out.println("say hi " + name +
                " from port " + Calendar.getInstance().getTime().toLocaleString());

        return "say hi " + name +
                " from port ";
    }

    @Autowired
    public void setQuartzFacade(QuartzFacade quartzFacade) {
        this.quartzFacade = quartzFacade;
    }
}
