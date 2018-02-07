package com.sl.happylife.greetercloud.configures;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class GreeterCloudConfigures {

    @Value("${server.port}")
    private Integer port;

    public Integer getPort() {
        return port;
    }
}
