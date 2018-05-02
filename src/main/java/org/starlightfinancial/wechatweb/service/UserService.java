package org.starlightfinancial.wechatweb.service;

import org.starlightfinancial.wechatweb.domain.User;

/**
 *  @author senlin.deng
 */
public interface UserService {
    void saveOrUpdateUser(User user);

    User findByOpenId(String openId);

    User findUser(String mobile, String password);

    User findByMobile(String mobile);
}
