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

    private static String url;

    static {
        Properties properties = new Properties();
        try {
            properties.load(GetOpenIdUtil.class.getClassLoader().getResourceAsStream("weixin.properties"));
            StringBuilder urlBuilder = new StringBuilder(properties.getProperty("url"));
            urlBuilder.append("?appid=").append(properties.getProperty("appid"))
                    .append("&secret=").append(properties.getProperty("secret"))
                    .append("&code=%s")
                    .append("&grant_type=authorization_code");
            // 组成请求地址
            // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=s%&grant_type=authorization_code
            url = urlBuilder.toString();
            logger.info("加载微信公众号配置成功");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("加载微信公众号配置失败", e.getMessage());
        }
    }


    public static String getOpenId(String code) {
        String resultStr = HttpClientUtil.get(String.format(url,code));
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
