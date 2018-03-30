package com.sl.happylife.greeterfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({
        "com.sl.happylife.**.controller",
        "com.sl.happylife.**.service"})
public class GreeterFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreeterFeignApplication.class);
    }
}
