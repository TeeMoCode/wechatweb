package org.starlightfinancial.wechatweb.service;

import org.starlightfinancial.wechatweb.domain.User;

public interface UserService {
    void saveUser(User user);

    User findByOpenId(String openId);

    User findUser(String mobile, String password);

    User findByMobile(String mobile);
}
