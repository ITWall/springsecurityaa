package com.tungns.spingsecurityaa.security;

import com.tungns.spingsecurityaa.security.filter.CustomAuthFilter;
import com.tungns.spingsecurityaa.security.manager.CustomAuthenticationManager;
import com.tungns.spingsecurityaa.security.manager.CustomAuthenticationManager2;
import com.tungns.spingsecurityaa.security.provider.CustomAuthenticationProvider2;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private CustomAuthenticationManager customAuthenticationManager;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and().authenticationManager(new CustomAuthenticationManager2(new CustomAuthenticationProvider2()))
                .addFilterAfter(new CustomAuthFilter(customAuthenticationManager), BasicAuthenticationFilter.class)
                .build();
    }

}
