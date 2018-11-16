package com.xzy.spring.cloud.weather.job;

import com.xzy.spring.cloud.weather.service.CityDataService;
import com.xzy.spring.cloud.weather.service.WeatherDataService;
import com.xzy.spring.cloud.weather.vo.City;
import com.xzy.spring.cloud.weather.vo.CityList;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @ClassName WeatherDataSyncJob 定时执行任务
 * @Description TODO
 * @Author Administrator
 * @Date 2018/10/22 19:42
 * @Version 1.0
 **/
public class WeatherDataSyncJob extends QuartzJobBean {

    @Autowired
    private CityDataService cityDataService;
    @Autowired
    private WeatherDataService weatherDataService;

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext context) throws JobExecutionException {
        logger.info("weather Data Sync Job,start!");
        List<City> cityList = null;
        try {
            cityList = cityDataService.listCity();
        } catch (Exception e) {
            logger.error("Exception!",e);
        }
        for (City city : cityList) {
            String cityId = city.getCityId();
            logger.info("weather Data Sync Job,cityId:"+cityId);
            weatherDataService.SyncDataByCityId(cityId);
        }
        logger.info("weather Data Sync Job,end!");
    }
}
