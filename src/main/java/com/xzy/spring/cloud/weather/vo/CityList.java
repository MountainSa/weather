package com.xzy.spring.cloud.weather.vo;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @ClassName CityList
 * @Description TODO
 * @Author Administrator
 * @Date 2018/10/22 22:05
 * @Version 1.0
 **/
@Data
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {

    @XmlElement(name = "d")
    private List<City> cityList;

}
