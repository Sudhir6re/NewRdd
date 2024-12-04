package com.mahait.gov.in.customfilter;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private int appCode;

    public CustomAuthenticationToken(Object principal, Object credentials, int appCode) {
        super(principal, credentials);
        this.appCode = appCode;
        super.setAuthenticated(false);
    }

    public CustomAuthenticationToken(Object principal, Object credentials, int appCode, 
        Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.appCode = appCode;
        super.setAuthenticated(true); // must use super, as we override
    }

    public int getAppCode() {
        return this.appCode;
    }
}
