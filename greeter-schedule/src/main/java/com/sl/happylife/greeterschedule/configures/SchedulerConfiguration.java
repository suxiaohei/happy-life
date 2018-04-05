package com.sl.happylife.greeterschedule.configures;

import com.sl.happylife.greeterschedule.spring.AutowiringSpringBeanJobFactory;
import com.zaxxer.hikari.HikariDataSource;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:quartz.properties")
@Profile({"prod"})
public class SchedulerConfiguration {

    @Value("${bzn_quartz.org.quartz.scheduler.instanceName}")
    private String instanceName;
    @Value("${bzn_quartz.org.quartz.scheduler.instanceId}")
    private String instanceId;
    @Value("${bzn_quartz.org.quartz.scheduler.skipUpdateCheck}")
    private String skipUpdateCheck;
    @Value("${bzn_quartz.org.quartz.scheduler.jobFactory.class}")

    private String jobFactoryClass;
    @Value("${bzn_quartz.org.quartz.jobStore.class}")
    private String jobStoreClass;
    @Value("${bzn_quartz.org.quartz.jobStore.driverDelegateClass}")
    private String jobStoreDriverDelegateClass;
    @Value("${bzn_quartz.org.quartz.jobStore.tablePrefix}")
    private String jobStoreTablePrefix;
    @Value("${bzn_quartz.org.quartz.jobStore.isClustered}")
    private String jobStoreIsClustered;
    @Value("${bzn_quartz.org.quartz.jobStore.clusterCheckinInterval}")
    private String clusterCheckinInterval;

    @Value("${bzn_quartz.org.quartz.threadPool.threadCount}")
    private String threadCount;
    @Value("${bzn_quartz.org.quartz.threadPool.class}")
    private String threadPoolClass;

    private static Logger logger = LoggerFactory.getLogger(SchedulerConfiguration.class);

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean("quartzDataSource")
    @ConfigurationProperties("dataSource.bzn_quartz")
    public HikariDataSource quartzDataSource() {
        return (HikariDataSource) DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("quartzDataSource") DataSource quartzDataSource, JobFactory jobFactory)
            throws IOException {

        logger.info("start schedule by SchedulerFactoryBean");

        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        // this allows to update triggers in DB when updating settings in config file:
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(quartzDataSource);
        factory.setJobFactory(jobFactory);

        Properties properties = quartzProperties();
        properties.put("org.quartz.scheduler.instanceName", instanceName);
        properties.put("org.quartz.scheduler.instanceId", instanceId);
        properties.put("org.quartz.scheduler.skipUpdateCheck", skipUpdateCheck);

        properties.put("org.quartz.scheduler.jobFactory.class", jobFactoryClass);
        properties.put("org.quartz.jobStore.class", jobStoreClass);
        properties.put("org.quartz.jobStore.driverDelegateClass", jobStoreDriverDelegateClass);
        properties.put("quartz.jobStore.tablePrefix", jobStoreTablePrefix);
        properties.put("org.quartz.jobStore.isClustered", jobStoreIsClustered);
        properties.put("bzn_quartz.org.quartz.jobStore.clusterCheckinInterval", clusterCheckinInterval);

        properties.put("org.quartz.threadPool.threadCount", threadCount);
        properties.put("org.quartz.threadPool.class", threadPoolClass);

        factory.setQuartzProperties(properties);

        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}
