package org.starlightfinancial.wechatweb.security.auth.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.starlightfinancial.wechatweb.domain.User;
import org.starlightfinancial.wechatweb.domain.UserRepository;
import org.starlightfinancial.wechatweb.enums.ConstantsEnum;

/**
 * 根据微信openId查询用户信息
 *
 * @author senlin.deng
 */
@Component
public class CustomByOpenIdUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String openId) throws UsernameNotFoundException {
        User user = userRepository.findByOpenId(openId);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在,openId:"+openId);
        }else{
            //用户主动注销登录时,不进行微信OpenId自动登录
            if(ConstantsEnum.SUCCESS.getCode().equals(user.getIsLogout())){
                user = null;
            }
        }
        return user;
    }
}
