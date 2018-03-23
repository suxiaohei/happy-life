package com.sl.happylife.greetercloud.configures;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class GreeterCloudConfigures {

    @Value("${server.port}")
    private Integer port;

//    @Value("${name}")
//    private String name;
//
//    public String getName() {
//        return name;
//    }

    public Integer getPort() {
        return port;
    }
}
