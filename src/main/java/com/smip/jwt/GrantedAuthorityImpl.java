package com.smip.jwt;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by kepler@gmail.com on 2017/11/14.
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
