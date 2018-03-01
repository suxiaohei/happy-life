package com.sl.happylife.greeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author suxin
 */
@SpringBootApplication
@ComponentScan({
        "com.sl.happylife.**.controller",
        "com.sl.happylife.**.service",
        "com.sl.happylife.**.configures"})
public class GreeterApplication {

    public static void main(String[] args) {
        System.out.println("start .............");
        SpringApplication.run(GreeterApplication.class);
    }
}