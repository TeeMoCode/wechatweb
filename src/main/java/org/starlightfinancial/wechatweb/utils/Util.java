package org.starlightfinancial.wechatweb.utils;

public class Util {
    /**
     * 生成6位随机数作短信验证码
     * @return
     */
    public  static String  getSmsCode(){
        return Integer.toString((int) ((Math.random() * 9 + 1) * 100000));
    }

}

