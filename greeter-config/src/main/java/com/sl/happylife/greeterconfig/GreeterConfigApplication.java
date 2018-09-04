package com.sl.happylife.greeterconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 统一配置模块
 *
 * @author suxin
 */
@SpringBootApplication
@EnableConfigServer
public class GreeterConfigApplication {

    public static void main(String[] args) {

        System.out.println("start GreeterConfigApplication..............");
        SpringApplication.run(GreeterConfigApplication.class);
    }
}
