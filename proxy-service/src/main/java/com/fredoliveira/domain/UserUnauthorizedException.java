package com.fredoliveira.domain;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends AuthenticationException {

    public UserUnauthorizedException(String username) {
        super("User with username " + username + " isn't authorized.");
    }

}
