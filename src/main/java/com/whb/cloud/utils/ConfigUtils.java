package com.whb.cloud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * 配置工具类
 */
public class ConfigUtils {

    private static HashMap<String,String> map=new HashMap<String,String>();
    //日志记录器
    private static Logger logger = LoggerFactory.getLogger(ConfigUtils.class);


    static {
        Properties prop=new Properties();
        //加载配置
        InputStream is= ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            //加载流中的数据
            prop.load(is);
            Enumeration enu=prop.propertyNames();
            while (enu.hasMoreElements()){
                String name= (String) enu.nextElement();
                String value=prop.getProperty(name);
                map.put(name,value);
            }
        } catch (IOException e) {
            logger.error("配置文件加载失败="+e.getMessage());
        }
    }


    /**
     * 取得配置字符串内容
     * @param name 参数名
     * @return 参数值
     */
    public static String getValue(String name){
        return map.get(name);
    }

    /**
     * 取得配置int类型
     * @param name 参数名
     * @return 返回参数值
     */
    public static int getValueInt(String name){
        String value=map.get(name);
        return Integer.parseInt(value);
    }

}
