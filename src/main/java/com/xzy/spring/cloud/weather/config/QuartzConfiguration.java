package com.xzy.spring.cloud.weather.config;

import com.xzy.spring.cloud.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName QuartzConfiguration
 * @Description TODO
 * @Author Administrator
 * @Date 2018/10/22 21:26
 * @Version 1.0
 **/
@Configuration
public class QuartzConfiguration {

    private static final int TIME = 1800;

    //JobDetail
    @Bean
    public JobDetail weatherDataSyncJobJobDetail(){
      return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob")
                .storeDurably().build();
    }

    //trigger
    @Bean
    public Trigger weatherDataSyncJobTrigger(){

       SimpleScheduleBuilder scheBuilder = SimpleScheduleBuilder.simpleSchedule()
               .withIntervalInSeconds(TIME).repeatForever();
        return TriggerBuilder.newTrigger().forJob("weatherDataSyncJob")
                .withIdentity("weatherDataSyncJobTrigger")
                .withSchedule(scheBuilder).build();
    }
}
