package com.sl.happylife.greetercloud.configures;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件（从Spring Cloud Config中读取）
 *
 * @author suxin
 */
@Configuration
public class GreeterCloudConfigures {

    @Value("${name}")
    private String name;

    public String getName() {
        return name;
    }

    @Value("${server.port}")
    private Integer port;

    public Integer getPort() {
        return port;
    }
}
