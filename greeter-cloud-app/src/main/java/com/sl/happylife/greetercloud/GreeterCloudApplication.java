package com.sl.happylife.greetercloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author suxin
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GreeterCloudApplication {

    public static void main(String[] args) {

        System.out.println("start GreeterCloudApplication .............");
        SpringApplication.run(GreeterCloudApplication.class);
    }
}