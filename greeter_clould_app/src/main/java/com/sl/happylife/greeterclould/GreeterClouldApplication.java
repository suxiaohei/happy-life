package com.sl.happylife.greeterclould;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author suxin
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GreeterClouldApplication {

    public static void main(String[] args) {
        System.out.println("start GreeterClouldApplication .............");
        SpringApplication.run(GreeterClouldApplication.class);
    }
}