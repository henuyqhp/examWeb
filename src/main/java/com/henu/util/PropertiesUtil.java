package com.henu.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置文件的读取类 封装成单例
 */
public class PropertiesUtil {

    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(PropertiesUtil.getProperty("ftp.server.ip"));
    }

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties properties;

    static {
        String fileName = "examWeb.properties";
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName)));
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }
    }

    /**
     * 获取值 如果没有相应键的值就返回null
     * @param key 键
     * @return 值
     */
    public static String getProperty(String key){
        String value = properties.getProperty(key);
        if (StringUtils.isBlank(value))
            return null;
        return value.trim();
    }

    /**
     * 获取值 如果没有相应键的值就返回默认的defaultvalue
     * @param key 键
     * @param defaultValue 默认值
     * @return 值或默认值
     */
    public static String getProperty(String key,String defaultValue){
        String value = properties.getProperty(key);
        if (StringUtils.isBlank(value)){
            return null;
        }
        return defaultValue;
    }
}
