package com.smip.error;


import org.springframework.security.core.AuthenticationException;

/**
 * Created by kepler@gmail.com on 2017/11/14.
 */
public class UsernameIsExitedException extends AuthenticationException {

    public UsernameIsExitedException(String msg) {
        super(msg);
    }

    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }

}
