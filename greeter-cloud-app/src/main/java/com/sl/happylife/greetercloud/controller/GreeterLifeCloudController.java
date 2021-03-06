package com.sl.happylife.greetercloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.sl.happylife.greetercloud.configures.GreeterCloudConfigures;
import com.sl.happylife.greetercloud.service.SayHiScheduleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对外暴露的服务
 *
 * @author suxin
 */
@RestController
@EnableDiscoveryClient
public class GreeterLifeCloudController {

    private GreeterCloudConfigures greeterCloudConfigures;
    private SayHiScheduleFacade sayHiScheduleFacade;

    @GetMapping(path = "/greeter_life_test")
    public String testLife() {

        return "greeter/test";
    }

    @GetMapping(path = "/hi")
    public String sayHi(@RequestParam String name) {

        JSONObject params = new JSONObject()
                .fluentPut("name", greeterCloudConfigures.getName());

        return sayHiScheduleFacade.sayHi(params);
    }

    @Autowired
    public void setSayHiScheduleFacade(SayHiScheduleFacade sayHiScheduleFacade) {
        this.sayHiScheduleFacade = sayHiScheduleFacade;
    }

    @Autowired
    public void setGreeterCloudConfigures(GreeterCloudConfigures greeterCloudConfigures) {
        this.greeterCloudConfigures = greeterCloudConfigures;
    }
}
