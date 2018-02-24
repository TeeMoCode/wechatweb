package org.starlightfinancial.wechatweb.utils.emay;

public enum EmayResultCodeEnum {
    CODE_0("0", "成功"),
    CODE_1("-1", "系统错误"),
    CODE_2("-2", "客户端异常"),
    CODE_3("-101", "命令不被支持"),
    CODE_4("-102", "RegistryTransInfo删除信息失败（转接）"),
    CODE_5("-103", "RegistryInfo更新信息失败"),
    CODE_6("-104", "请求超过限制"),
    CODE_7("-110", "号码注册激活失败"),
    CODE_8("-111", "企业注册失败"),
    CODE_9("-113", "充值失败"),
    CODE_10("-117", "发送短信失败"),
    CODE_11("-118", "接收MO失败"),
    CODE_12("-119", "接收Report失败"),
    CODE_13("-120", "修改密码失败"),
    CODE_14("-122", "号码注销失败"),
    CODE_15("-123", "查询单价失败"),
    CODE_16("-124", "查询余额失败"),
    CODE_17("-125", "设置MO转发失败"),
    CODE_18("-126", "路由信息失败"),
    CODE_19("-127", "计费失败0余额"),
    CODE_20("-128", "计费失败余额不足"),
    CODE_21("-1100", "序列号错误,序列号不存在内存中,或尝试攻击的用户"),
    CODE_22("-1102", "序列号密码错误"),
    CODE_23("-1103", "序列号Key错误"),
    CODE_24("-1104", "路由失败，请联系系统管理员"),
    CODE_25("-1105", "注册号状态异常, 未用 1"),
    CODE_26("-1107", "注册号状态异常, 停用 3"),
    CODE_27("-1108", "注册号状态异常, 停止 5"),
    CODE_28("-1131", "充值卡无效"),
    CODE_29("-1131", "充值卡无效"),
    CODE_30("-1132", "充值密码无效"),
    CODE_31("-1133", "充值卡绑定异常"),
    CODE_32("-1134", "充值状态无效"),
    CODE_33("-1135", "充值金额无效"),
    CODE_34("-190", "数据操作失败"),
    CODE_35("-1901", "数据库插入操作失败"),
    CODE_36("-1902", "数据库更新操作失败"),
    CODE_37("-1903", "数据库删除操作失败"),
    CODE_38("-9000", "数据格式错误,数据超出数据库允许范围"),
    CODE_39("-9001", "序列号格式错误"),
    CODE_40("-9002", "密码格式错误"),
    CODE_41("-9003", "客户端Key格式错误"),
    CODE_42("-9004", "设置转发格式错误"),
    CODE_43("-9005", "公司地址格式错误"),
    CODE_44("-9006", "企业中文名格式错误"),
    CODE_45("-9007", "企业中文名简称格式错误"),
    CODE_46("-9008", "邮件地址格式错误"),
    CODE_47("-9009", "企业英文名格式错误"),
    CODE_48("-9010", "企业英文名简称格式错误"),
    CODE_49("-9011", "传真格式错误"),
    CODE_50("-9012", "联系人格式错误"),
    CODE_51("-9013", "联系电话"),
    CODE_52("-9014", "邮编格式错误"),
    CODE_53("-9015", "新密码格式错误"),
    CODE_54("-9016", "发送短信包大小超出范围"),
    CODE_55("-9017", "发送短信内容格式错误"),
    CODE_56("-9018", "发送短信内容格式错误"),
    CODE_57("-9019", "发送短信优先级格式错误"),
    CODE_58("-9020", "发送短信手机号格式错误"),
    CODE_59("-9021", "发送短信定时时间格式错误"),
    CODE_60("-9022", "发送短信唯一序列值错误"),
    CODE_61("-9023", "充值卡号格式错误"),
    CODE_62("-9024", "充值密码格式错误"),
    CODE_63("-9025", "客户端请求sdk5超时（需确认）");


    private String code;
    private String value;

    EmayResultCodeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

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

    public static String getValueByCode(String code) {
        for (EmayResultCodeEnum _enum : EmayResultCodeEnum.values()) {
            if (code.equals(_enum.getCode())) {
                return _enum.getValue();
            }
        }
        return null;
    }

    }
