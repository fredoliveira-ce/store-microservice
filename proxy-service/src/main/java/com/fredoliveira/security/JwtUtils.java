package com.fredoliveira.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

public final class JwtUtils {

    // Cannot be instantiated.
    private JwtUtils() { }

    private static final String SECRET = "asdfghqwerty";
    private static final String HEADER_AUTHORIZATION = "Authorization";

    public static void addToken(HttpServletResponse response, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION);
        Authentication authentication = null;

        if (token != null) {
            String username = getUsername(token);

            if (!isNull(username)) {
                authentication = new UsernamePasswordAuthenticationToken(username, null, emptyList());
            }
        }

        return authentication;
    }

    private static String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
