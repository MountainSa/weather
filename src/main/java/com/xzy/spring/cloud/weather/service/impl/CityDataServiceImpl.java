package com.xzy.spring.cloud.weather.service.impl;

import com.xzy.spring.cloud.weather.service.CityDataService;
import com.xzy.spring.cloud.weather.utils.XmlBuilder;
import com.xzy.spring.cloud.weather.vo.City;
import com.xzy.spring.cloud.weather.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @ClassName CityDataService
 * @Description TODO
 * @Author Administrator
 * @Date 2018/10/22 22:17
 * @Version 1.0
 **/
@Service
public class CityDataServiceImpl implements CityDataService {
    @Override
    public List<City> listCity() throws Exception {
        //读取xml文件
        Resource resource = new ClassPathResource("citylist.xml");

        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(),"UTF-8"));

        StringBuffer buffer = new StringBuffer();

        String line = "";

        while ((line = br.readLine())!=null){
            buffer.append(line);
        }
        br.close();
        //xml数据转成java对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToObj(CityList.class,buffer.toString());

        return cityList.getCityList();
    }
}
