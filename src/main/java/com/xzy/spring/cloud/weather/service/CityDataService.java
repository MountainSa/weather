package com.xzy.spring.cloud.weather.service;

import com.xzy.spring.cloud.weather.vo.City;

import java.util.List;

/**
 * create by: xzy
 * description:城市数据服务接口
 * create time: 22:16 2018/10/22
 *
 * @Param
 * @return
 */
public interface CityDataService {
    /**
     * create by: xzy
     * description:获取城市列表
     * create time: 22:16 2018/10/22
     *
     * @Param
     * @return
     */
    List<City> listCity() throws Exception;



}
