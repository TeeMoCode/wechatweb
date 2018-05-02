package org.starlightfinancial.wechatweb.enums;

/**
 * @author senlin.deng
 * @date 2018/3/14 15:33
 */
public enum ConstantsEnum {
    /**
     * 表示肯定,正向意义
     */
    SUCCESS("1", "成功"),

    /**
     * 表示否定,负面意义
     */
    FAIL("0", "失败"),
    /**
     * 请求成功
     */
    REQUESTSUCCESS("0000", "请求成功"),
    /**
     * 请求失败
     */
    REQUESTFAIL("0001", "请求失败");


    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    ConstantsEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
