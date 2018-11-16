package com.xzy.spring.cloud.weather.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

/**
 * @ClassName XmlBuilder
 * @Description TODO
 * @Author Administrator
 * @Date 2018/10/22 22:08
 * @Version 1.0
 **/
public class XmlBuilder {

    /**
     * create by: xzy
     * description:将xml转为指定pojo对象
     * create time: 22:10 2018/10/22
     *
     * @Param 
     * @return 
     */
    public static Object xmlStrToObj(Class<?> clazz,String xmlStr) throws Exception{
        Object xmlObject = null;
        Reader reader = null;
        JAXBContext context = JAXBContext.newInstance(clazz);

        //xml转为对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();
        reader = new StringReader(xmlStr);
        xmlObject =  unmarshaller.unmarshal(reader);
        if(null!=reader){
            reader.close();
        }
        return xmlObject;
    };
}
