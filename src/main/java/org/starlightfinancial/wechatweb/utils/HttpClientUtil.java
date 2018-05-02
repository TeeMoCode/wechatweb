package org.starlightfinancial.wechatweb.utils;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

/**
 * @author senlin.deng
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    /**
     * post 请求
     *
     * @param url
     * @return
     */
    public static String post(String url) {
        return post(url, "");
    }

    /**
     * post请求
     *
     * @param url
     * @param data
     * @return
     */
    public static String post(String url, String data) {
        return httpPost(url, data);
    }

    /**
     * 发送http post请求
     *
     * @param url      url
     * @param instream post数据的字节流
     * @return
     */
    public static String post(String url, InputStream instream) {
        try {
            HttpEntity entity = Request.Post(url)
                    .bodyStream(instream, ContentType.create("text/html", Consts.UTF_8))
                    .execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (Exception e) {
            logger.error("post请求异常，" + e.getMessage() + "\n post url:" + url);
            e.printStackTrace();
        }
        return null;
    }
    /**
     * post 请求提交表单
     *
     * @param url
     * @param data
     * @return
     */
    public  static String post(String url, List<BasicNameValuePair> data) {
        try {
            HttpEntity entity = Request.Post(url)
                    .bodyForm(data,Consts.UTF_8)
                    .execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (Exception e) {
            logger.error("post请求异常，" + e.getMessage() + "\n post url:" + url);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        return httpGet(url);
    }

    /**
     * post 请求
     *
     * @param url
     * @param data
     * @return
     */
    private static String httpPost(String url, String data) {
        try {
            HttpEntity entity = Request.Post(url)
                    .bodyString(data, ContentType.create("text/html", Consts.UTF_8))
                    .execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (Exception e) {
            logger.error("post请求异常，" + e.getMessage() + "\n post url:" + url);
            e.printStackTrace();
        }
        return null;
    }





    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    private static String httpGet(String url) {
        try {
            HttpEntity entity = Request.Get(url).
                    execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (Exception e) {
            logger.error("get请求异常，" + e.getMessage() + "\n get url:" + url);
            e.printStackTrace();
        }
        return null;
    }


}
