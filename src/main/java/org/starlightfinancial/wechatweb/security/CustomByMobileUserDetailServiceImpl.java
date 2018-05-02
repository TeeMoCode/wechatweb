package org.starlightfinancial.wechatweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.starlightfinancial.wechatweb.domain.User;
import org.starlightfinancial.wechatweb.domain.UserRepository;

/**
 * @author senlin.deng
 */
public class CustomByMobileUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        User user = userRepository.findByMobileAndIsDelete(mobile, "0");
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在,手机号:"+mobile);
        }
        return user;
    }
}
