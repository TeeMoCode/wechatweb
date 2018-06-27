package org.starlightfinancial.wechatweb.security.auth.lang;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starlightfinancial.wechatweb.utils.HttpClientUtil;

import java.io.IOException;
import java.util.Properties;

/**
 * @author senlin.deng
 */
public class GetOpenIdUtil {

    private static final Logger logger = LoggerFactory.getLogger(GetOpenIdUtil.class);

    private static String appid;
    private static String secret;
    private static String grant_type;
    private static String url;

    static {
        Properties properties = new Properties();
        try {
            properties.load(GetOpenIdUtil.class.getClassLoader().getResourceAsStream("weixin.properties"));
            appid = properties.getProperty("appid");
            secret = properties.getProperty("secret");
            grant_type = properties.getProperty("grant_type");
            url = properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("加载微信公众号配置失败", e.getMessage());
        }
    }


    public static String getOpenId(String code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appid", appid);
        jsonObject.put("secret", secret);
        jsonObject.put("code", code);
        jsonObject.put("grant_type", grant_type);
        String requestData = jsonObject.toString();
        String resultStr = HttpClientUtil.post(url, requestData);
        JSONObject resultData = JSONObject.parseObject(resultStr);
        System.out.println("微信授权服务器返回的信息"+resultData);
        Integer errcode = resultData.getIntValue("errcode");
        if (errcode != null && errcode > 0){
            logger.error("获取openId异常,异常码:"+errcode+",异常信息:"+resultData.getString("errmsg"));
            return  null;
        }else {
            String openid = resultData.getString("openid");
            return openid;
        }

    }


}
