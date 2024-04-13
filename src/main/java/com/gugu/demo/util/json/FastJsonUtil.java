package com.gugu.demo.util.json;

import com.alibaba.fastjson2.JSON;

/**
 * @author gugu
 * @Classname FastJsonUtil
 * @Description TODO
 * @Date 2022/9/18 10:26
 */
public class FastJsonUtil {
    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }
}
