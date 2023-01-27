package com.secured.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuth extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
      AuthCredential authCredential = new AuthCredential();
        try {
            authCredential = new ObjectMapper().readValue(request.getReader(), AuthCredential.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken usernamePath = new UsernamePasswordAuthenticationToken(
                authCredential.getEmail(),
                authCredential.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(usernamePath);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailImp userDetail = (UserDetailImp) authResult.getPrincipal();
        String token = TokenUtil.createToken(userDetail.getNombre(), userDetail.getUsername());
        response.addHeader("Authorization","Bearer " + token);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
