package org.starlightfinancial.wechatweb.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author senlin.deng
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByOpenId(String openId);

    User findByMobileAndPasswordAndIsDelete(String mobile, String encryptPassword, String isDelete);

    User findByMobileAndIsDelete(String mobile, String isDelete);
}
