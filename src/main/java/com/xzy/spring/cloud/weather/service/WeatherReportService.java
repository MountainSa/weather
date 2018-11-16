package com.xzy.spring.cloud.weather.service;

import com.xzy.spring.cloud.weather.vo.Weather;

/**
 * create by: xzy
 * description:weather report service 天气预报服务
 * create time: 0:40 2018/11/17
 *
 * @Param 
 * @return 
 */
public interface WeatherReportService {

    /**
     * create by: xzy
     * description:根据城市id查询天气信息
     * create time: 0:42 2018/11/17
     *
     * @Param 
     * @return 
     */
     Weather getDataByCityId(String cityId);
}
