package org.starlightfinancial.wechatweb.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author senlin.deng
 */
public class EncryptHelper {
    public static EncryptHelper Instance = new EncryptHelper();
    private Key key;

    private EncryptHelper() {
        setKey("creditdeskey");
    }

    public void setKey(String strKey) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            //防止linux下 随机生成key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());

            keyGenerator.init(56, secureRandom);
            this.key = keyGenerator.generateKey();
            keyGenerator = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEncString(String strMing) {
        @SuppressWarnings("unused")
        byte[] byteMi = null;
        @SuppressWarnings("unused")
        byte[] byteMing = null;
        try {
            return byte2hex(getEncCode(strMing.getBytes()));
        } catch (Exception e) {
            return "";
        } finally {
            byteMing = null;
            byteMi = null;
        }
    }

    public String getDesString(String strMi) {
        @SuppressWarnings("unused")
        byte[] byteMing = null;
        @SuppressWarnings("unused")
        byte[] byteMi = null;
        try {
            return new String(getDesCode(hex2byte(strMi.getBytes())));
        } catch (Exception e) {
            return "";
        } finally {
            byteMing = null;
            byteMi = null;
        }
    }

    private byte[] getEncCode(byte[] byteS) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    private byte[] getDesCode(byte[] byteD) {

        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    public static String byte2hex(byte[] b) { // 一个字节的数，
        // 转成16进制字符串
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            // 整数转成十六进制表示
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        // 转成大写
        return hs.toUpperCase();

    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    public static void main(String[] args) {
        EncryptHelper encryptHelper = new EncryptHelper();
        String encString = encryptHelper.getEncString("123456a");
        System.out.println(encString);
        System.out.println(encryptHelper.getDesString("8EE1509302691176"));

    }
}
