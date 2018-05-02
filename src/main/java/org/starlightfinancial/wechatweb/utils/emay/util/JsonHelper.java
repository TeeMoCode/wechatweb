package org.starlightfinancial.wechatweb.utils.emay.util;

import com.alibaba.fastjson.JSONObject;

public class JsonHelper {

    /**
     * 将对象转换为json串
     * @param object
     * @return
     */
    public static String toJsonString(Object object){
        if (object == null){
            return null;
        }
        return JSONObject.toJSONString(object);
    }
    /**
     * 将json串转换为对象
     *
     * @param clazz
     * @param jsonString
     * @return
     */
    public static <T> T fromJson(Class<T> clazz, String jsonString) {
        if (jsonString == null) {
            return null;
        }
        return JSONObject.parseObject(jsonString, clazz);
    }

}
