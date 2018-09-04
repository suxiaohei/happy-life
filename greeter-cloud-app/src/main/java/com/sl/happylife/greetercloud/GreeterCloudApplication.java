package com.sl.happylife.greetercloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Cloud Eureka 提供服务模块
 *
 * @author suxin
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
@ComponentScan({
        "com.sl.happylife.**.controller",
        "com.sl.happylife.**.configures",
        "com.sl.happylife.**.service.impl"})
public class GreeterCloudApplication {

    public static void main(String[] args) {

        System.out.println("start GreeterCloudApplication .............");
        SpringApplication.run(GreeterCloudApplication.class);
    }
}