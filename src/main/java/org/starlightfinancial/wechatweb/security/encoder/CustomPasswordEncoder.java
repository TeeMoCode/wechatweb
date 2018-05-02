package org.starlightfinancial.wechatweb.security.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.starlightfinancial.wechatweb.utils.EncryptHelper;

/**
 * 使用手机和密码登录时的密码编码器
 *
 * @author senlin.deng
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String encodedPassword = EncryptHelper.Instance.getEncString(rawPassword + "");
        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String pass1 = encode(rawPassword);
        return encodedPassword.equals(pass1);
    }
}
