package com.sl.happylife.greetercloud.controller;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
public class GreeterLifeCloudController {

    @GetMapping(path = "/greeter_life_test")
    public String testLife() {

        return "greeter/test";
    }

    @GetMapping(path = "/hi")
    public String sayHi(@RequestParam String name) {

        return "hi " + name + " from port" ;
    }
}
