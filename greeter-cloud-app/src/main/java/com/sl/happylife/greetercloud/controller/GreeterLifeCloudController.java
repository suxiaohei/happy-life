package com.sl.happylife.greetercloud.controller;

import com.sl.happylife.greetercloud.configures.GreeterCloudConfigures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
public class GreeterLifeCloudController {

    private GreeterCloudConfigures greeterCloudConfigures;

    @GetMapping(path = "/greeter_life_test")
    public String testLife() {

        return "greeter/test";
    }

    @GetMapping(path = "/hi")
    public String sayHi(@RequestParam String name) {

        return "hi " + name + " from port " + greeterCloudConfigures.getPort();
    }

    @Autowired
    public void setGreeterCloudConfigures(GreeterCloudConfigures greeterCloudConfigures) {
        this.greeterCloudConfigures = greeterCloudConfigures;
    }
}
