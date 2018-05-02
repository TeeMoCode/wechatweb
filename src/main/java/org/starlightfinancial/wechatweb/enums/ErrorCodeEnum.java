package org.starlightfinancial.wechatweb.enums;

/**
 * @Author: SiliChen
 * @Description:
 * @Date: Created in 16:18 2018/2/7
 * @Modified By:
 */
public enum ErrorCodeEnum {
    ERROR_CODE_01("000000", "请求成功"),
    ERROR_CODE_02("300000", "无数据"),
    ERROR_CODE_03("300001", "请求失败"),
    ERROR_CODE_04("300002", ""),
    ERROR_CODE_05("300003", ""),
    ERROR_CODE_06("300004", "访问频率过快"),
    ERROR_CODE_07("300005", "无权限访问此api"),
    ERROR_CODE_08("300006", ""),
    ERROR_CODE_09("300007", ""),
    ERROR_CODE_10("300008", "缺少必要参数"),
    ERROR_CODE_11("300009", ""),
    ERROR_CODE_12("300010", "URL不存在");

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    ErrorCodeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
