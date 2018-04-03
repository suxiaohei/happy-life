package com.sl.happylife.greeterconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableConfigServer
public class GreeterConfigApplication {

    public static void main(String[] args) {

        System.out.println("start GreeterConfigApplication..............");
        SpringApplication.run(GreeterConfigApplication.class);
    }
}
