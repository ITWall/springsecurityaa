package com.tungns.spingsecurityaa.security.filter;

import com.tungns.spingsecurityaa.security.authentication.CustomAuthentication;
import com.tungns.spingsecurityaa.security.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class CustomAuthFilter extends OncePerRequestFilter {
    private CustomAuthenticationManager customAuthenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!ObjectUtils.isEmpty(SecurityContextHolder.getContext().getAuthentication()) && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            doFilter(request, response, filterChain);
            return;
        }
        String key = request.getHeader("key");
        CustomAuthentication customAuthentication = new CustomAuthentication(false, key);
        customAuthenticationManager.authenticate(customAuthentication);
        doFilter(request, response, filterChain);
    }
}
