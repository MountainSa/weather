package com.xzy.spring.cloud.weather.service;

import com.xzy.spring.cloud.weather.vo.WeatherResponse;

/**
 * Weather Data Service.
 *
 */
public interface WeatherDataService {
	/**
	 * 根据城市ID查询天气数据
	 * 
	 * @param cityId
	 * @return
	 */
	WeatherResponse getDataByCityId(String cityId);

	/**
	 * 根据城市名称查询天气数据
	 * 
	 * @param
	 * @return
	 */
	WeatherResponse getDataByCityName(String cityName);

	/**
	 * create by: xzy
	 * description:根据城市id同步天气
	 * create time: 22:28 2018/10/22
	 *
	 * @Param
	 * @return
	 */
	void SyncDataByCityId(String cityId);
}
