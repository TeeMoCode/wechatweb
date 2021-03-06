package org.starlightfinancial.wechatweb.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starlightfinancial.wechatweb.domain.User;
import org.starlightfinancial.wechatweb.domain.UserRepository;
import org.starlightfinancial.wechatweb.service.UserService;
import org.starlightfinancial.wechatweb.utils.EncryptHelper;

/**
 * @author senlin.deng
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void saveOrUpdateUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findByOpenId(String openId) {
        return userRepository.findByOpenId(openId);
    }

    @Override
    public User findUser(String mobile, String password) {
        String encryptPassword = EncryptHelper.Instance.getEncString(password);
        if (StringUtils.isEmpty(encryptPassword)) {
            return null;
        }
        encryptPassword = encryptPassword.trim();
        return userRepository.findByMobileAndPasswordAndIsDelete(mobile, encryptPassword, "0");

    }

    @Override
    public User findByMobile(String mobile) {
        User user = userRepository.findByMobileAndIsDelete(mobile, "0");
        return user;
    }
}
