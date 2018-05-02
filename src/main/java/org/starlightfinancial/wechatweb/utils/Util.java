package org.starlightfinancial.wechatweb.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.starlightfinancial.wechatweb.domain.User;

/**
 * @author senlin.deng
 */
public class Util {

    /**
     * 获取当前登录用户
      * @return 返回用户
     */
    public  static User getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return  user;
    }

}

