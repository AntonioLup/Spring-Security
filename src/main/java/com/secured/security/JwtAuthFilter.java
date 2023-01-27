package com.secured.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.SecurityContextLoginModule;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String BareToken = request.getHeader("Authorization");
        if(BareToken != null && BareToken.startsWith("Bearer ")){
            String token = BareToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken usernamePath = TokenUtil.getAuth(token);
            SecurityContextHolder.getContext().setAuthentication(usernamePath);
        }
        filterChain.doFilter(request, response);
    }
}
