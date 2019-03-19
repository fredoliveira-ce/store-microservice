package com.fredoliveira.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fredoliveira.security.filter.JwtAuthenticationFilter;
import com.fredoliveira.security.filter.JwtLoginFilter;
import com.fredoliveira.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.inject.Inject;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private ObjectMapper mapper;
    private AuthService authService;

    @Inject
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Inject
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String loginUrl = "/login";
        http.csrf().disable().authorizeRequests()
                .antMatchers("/orders/**").permitAll()
                .antMatchers("/payments/**").permitAll()
                .antMatchers("/stores/**").permitAll()
                .and()
                .cors()
                .and()
                .addFilterBefore(new JwtLoginFilter(
                                loginUrl,
                                authenticationManager(),
                                mapper
                        ),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authService);
    }

}
