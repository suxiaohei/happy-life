package com.sl.happylife.greeterschedule.configures;

import com.sl.happylife.greeterschedule.spring.AutowiringSpringBeanJobFactory;
import org.quartz.Scheduler;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:quartz.properties")
@Profile({"dev"})
public class SchedulerConfigurationDev {

    @Value("${quartz.org.quartz.threadPool.threadCount}")
    private int threadCount = 2;

    private static Logger logger = LoggerFactory.getLogger(SchedulerConfigurationDev.class);

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Scheduler scheduler(JobFactory jobFactory) throws Exception {
        logger.info("start schedule by DirectSchedulerFactory in dev mode");

        DirectSchedulerFactory directSchedulerFactory = DirectSchedulerFactory.getInstance();
        directSchedulerFactory.createVolatileScheduler(threadCount);

        return directSchedulerFactory.getScheduler();
    }

}
