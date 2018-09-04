package com.sl.happylife.greeterfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Cloud Feign 消费模块
 *
 * @author suxin
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {
        "com.sl.happylife.greetercloud.facade.**.service"})
@ComponentScan({
        "com.sl.happylife.**.controller",
        "com.sl.happylife.**.biz",
        "com.sl.happylife.**.configures",
        "com.sl.happylife.**.service.impl"})
public class GreeterFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreeterFeignApplication.class);
    }
}
