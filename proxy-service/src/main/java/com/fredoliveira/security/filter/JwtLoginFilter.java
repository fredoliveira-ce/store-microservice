package com.fredoliveira.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fredoliveira.security.JwtUtils;
import com.fredoliveira.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private User user;
    private final ObjectMapper mapper;

    public JwtLoginFilter(
            String url,
            AuthenticationManager authManager,
            ObjectMapper mapper
    ) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.mapper = mapper;
    }

    @Override public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException, IOException {
        User user = mapper.readValue(request.getInputStream(), User.class);

        Authentication authentication = getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        emptyList()
                )
        );

        this.user = (User) authentication.getPrincipal();
        return authentication;
    }

    @Override protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth
    ) throws IOException {
        if(isNull(user)) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            res.getWriter().print(mapper.writeValueAsString(user));
            JwtUtils.addToken(res, auth.getName());
        }
    }
}
