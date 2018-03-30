package com.sl.happylife.greetercloud;

import com.sl.happylife.greeterschedule.configures.SchedulerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @author suxin
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
@ComponentScan({
        "com.sl.happylife.**.controller",
        "com.sl.happylife.**.service",
        "com.sl.happylife.**.configures"})
public class GreeterCloudApplication {

    public static void main(String[] args) {

        System.out.println("start GreeterCloudApplication .............");
        SpringApplication.run(GreeterCloudApplication.class);
    }
}