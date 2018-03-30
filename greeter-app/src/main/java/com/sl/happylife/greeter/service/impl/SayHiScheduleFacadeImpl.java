package com.sl.happylife.greeter.service.impl;

import com.sl.happylife.greeter.service.SayHiScheduleFacade;
import com.sl.happylife.greeterschedule.service.QuartzFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

/**
 *
 */
@Service
public class SayHiScheduleFacadeImpl implements SayHiScheduleFacade {

    private QuartzFacade quartzFacade;

    @Override
    public String sayHi(String name) {

        Long currentTime = System.currentTimeMillis();
        String quartzIdentity = UUID.randomUUID().toString();

        quartzFacade.scheduleOnTime(
                currentTime + 3000, quartzIdentity,
                SayHiScheduleFacade.class, "sayHi", null);

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
