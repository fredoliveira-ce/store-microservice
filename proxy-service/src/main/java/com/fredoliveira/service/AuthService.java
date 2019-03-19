package com.fredoliveira.service;

import com.fredoliveira.domain.User;
import com.fredoliveira.domain.UserUnauthorizedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service public class AuthService extends AbstractUserDetailsAuthenticationProvider {

    @Override protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
    ) throws AuthenticationException { }

    @Override protected UserDetails retrieveUser(
            String username,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
    ) throws AuthenticationException {
        if(username.equals("fred")) {
            return new User(username, "123");
        }

        throw new UserUnauthorizedException(username);
    }

}
