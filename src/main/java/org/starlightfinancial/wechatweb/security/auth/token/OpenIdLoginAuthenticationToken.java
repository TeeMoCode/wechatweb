package org.starlightfinancial.wechatweb.security.auth.token;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 微信OpenId登录token
 *
 * @author senlin.deng
 */
public class OpenIdLoginAuthenticationToken extends AbstractAuthenticationToken {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OpenIdLoginAuthenticationToken.class);
    private final Object principal;

    public OpenIdLoginAuthenticationToken(String openId) {
        super(null);
        this.principal = openId;
        this.setAuthenticated(false);
        LOGGER.info("OpenIdLoginAuthenticationToken setAuthenticated ->false loading ...");
    }

    public OpenIdLoginAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        // must use super, as we override
        super.setAuthenticated(true);
        LOGGER.info("OpenIdLoginAuthenticationToken setAuthenticated ->true loading ...");
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
