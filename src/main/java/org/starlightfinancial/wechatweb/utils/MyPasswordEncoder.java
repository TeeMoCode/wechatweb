package org.starlightfinancial.wechatweb.utils;

import org.springframework.security.crypto.password.PasswordEncoder;


public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String encodedPassword = EncryptHelper.Instance.getEncString(rawPassword+"");
        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String pass1 = encode(rawPassword);
        return encodedPassword.equals(pass1);
    }
}
