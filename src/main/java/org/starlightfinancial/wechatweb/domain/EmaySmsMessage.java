package org.starlightfinancial.wechatweb.domain;

public class EmaySmsMessage {

    private String mobile;
    private String header = "【润通小贷】您的验证码是:";
    private String smsCode;
    private String tail = "(此验证码3分钟内有效)。请不要把验证码泄露给其他人。若非本人发送,请忽略此短信。本条短信免费。";
    private String appId;
    private String secretKey;
    private String host;
    private String algorithm;
    private String encode;
    private boolean isGizp = false;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public boolean isGizp() {
        return isGizp;
    }

    public void setGizp(boolean gizp) {
        isGizp = gizp;
    }

    public String getContent(){
        return   this.header+this.smsCode+this.tail;
    }


}
