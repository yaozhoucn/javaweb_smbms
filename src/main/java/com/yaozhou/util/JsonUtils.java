package com.yaozhou.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * Created by WXHang on HANG at 2021/6/24 0:29
 * @author HANG
 */
public class JsonUtils {
    public static String getJson(Object object,String dataFormat) throws JsonProcessingException {
        /*JSONObject jsonObject = new JSONObject();*/
        ObjectMapper objectMapper = new ObjectMapper();
        //不使用时间戳的方式,关闭
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
        objectMapper.setDateFormat(sdf);

        String valueAsString = objectMapper.writeValueAsString(object);
        return valueAsString;

    }
}