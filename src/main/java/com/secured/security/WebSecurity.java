package com.secured.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@AllArgsConstructor
public class WebSecurity {
    private final UserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilterImp;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager auth) throws Exception{
        JWTAuth jwtAuthFilter = new JWTAuth();
        jwtAuthFilter.setAuthenticationManager(auth);
        jwtAuthFilter.setFilterProcessesUrl("/login");
        return http.csrf().disable()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthFilter)
                .addFilterBefore(jwtAuthFilterImp, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
//    @Bean
//    UserDetailsService userDetailService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles().build());
//        return manager;
//    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }


}
