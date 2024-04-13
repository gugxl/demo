package com.gugu.demo.util.json;


import net.sf.json.JSONObject;

/**
 * @author gugu
 * @Classname JsonLibUtil
 * @Description TODO
 * @Date 2022/9/18 10:28
 */
public class JsonLibUtil {
    public static String bean2Json(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
    }
}
