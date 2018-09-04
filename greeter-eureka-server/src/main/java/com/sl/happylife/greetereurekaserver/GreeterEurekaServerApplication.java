package com.sl.happylife.greetereurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心
 *
 * @author suxin
 */
@SpringBootApplication
@EnableEurekaServer
public class GreeterEurekaServerApplication {

    public static void main(String[] args) {
        System.out.println("start GreeterEurekaApplication .............");
        SpringApplication.run(GreeterEurekaServerApplication.class);
    }
}