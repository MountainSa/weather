package com.xzy.spring.cloud.weather.service.impl;

import com.xzy.spring.cloud.weather.service.WeatherDataService;
import com.xzy.spring.cloud.weather.service.WeatherReportService;
import com.xzy.spring.cloud.weather.vo.Weather;
import com.xzy.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName WeatherReportServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2018/11/17 0:41
 * @Version 1.0
 **/
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse response = weatherDataService.getDataByCityId(cityId);
        return response.getData();
    }
}
