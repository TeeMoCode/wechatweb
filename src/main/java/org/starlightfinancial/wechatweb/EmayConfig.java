package org.starlightfinancial.wechatweb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 亿美短信平台配置
 */
@Component
public class EmayConfig {

    @Value("${emay.appid}")
    private String appId;
    @Value("${emay.secretkey}")
    private String secretKey;
    @Value("${emay.algorithm}")
    private String algorithm;
    @Value("${emay.host}")
    private String host;
    @Value("${emay.encode}")
    private String encode;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }
}
