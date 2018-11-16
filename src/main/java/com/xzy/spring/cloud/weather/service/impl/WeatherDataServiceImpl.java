package com.xzy.spring.cloud.weather.service.impl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.xzy.spring.cloud.weather.service.WeatherDataService;
import com.xzy.spring.cloud.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * WeatherDataService 实现.
 *
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final Logger logger  = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final long TIME_OUT = 1800L;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    @Override
    public void SyncDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * create by: xzy
     * description:将天气数据放在缓存中
     * create time: 22:29 2018/10/22
     *
     * @Param
     * @return
     */
    private void saveWeatherData(String uri){
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }
            //数据写入缓存
            ops.set(key,strBody,TIME_OUT, TimeUnit.SECONDS);
    }

    private WeatherResponse doGetWeather(String uri) {
        String key = uri;
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //先查缓存，缓存有取缓存的数据
        //缓存没有，在调用服务接口来获取
        if(stringRedisTemplate.hasKey(key)){
            logger.info("redis has data");
            strBody = ops.get(key);
        }else{
            logger.info("redis don't has data");
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }
            //数据写入缓存
            ops.set(key,strBody,TIME_OUT, TimeUnit.SECONDS);
        }


        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("error",e);
        }

        return resp;
    }

}
