package org.starlightfinancial.wechatweb.utils;

public class WebResultModel {


    private String code;
    private Object data;
    private String message;


    public WebResultModel(){}

    public WebResultModel(String code){}

    public WebResultModel(String code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static WebResultModel ok(){
        return new WebResultModel("0000");
    }
    public static WebResultModel fail(String message){
        return new WebResultModel("0001",null,message);
    }
}
