package com.sl.happylife.greetereureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author suxin
 */
@SpringBootApplication
@EnableEurekaServer
public class GreeterEurekaApplication {

    public static void main(String[] args) {
        System.out.println("start GreeterEurekaApplication .............");
        SpringApplication.run(GreeterEurekaApplication.class);
    }
}