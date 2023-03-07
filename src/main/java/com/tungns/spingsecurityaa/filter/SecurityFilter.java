package com.tungns.spingsecurityaa.filter;

import com.tungns.spingsecurityaa.security.authManager.CustomAuthenticationManager;
import com.tungns.spingsecurityaa.security.authentication.CustomAuthentication;
import com.tungns.spingsecurityaa.security.entrypoint.CustomAuthEntrypoint;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final CustomAuthenticationManager authenticationManager;
    private final CustomAuthEntrypoint customAuthEntrypoint;
    private final List<MvcRequestMatcher> mvcRequestMatchers;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (mvcRequestMatchers.stream().anyMatch(matcher -> matcher.matches(request))) {
            filterChain.doFilter(request, response);
            return;
        }
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        var authentication = new CustomAuthentication().setName(username).setCredential(password);
        try {
            CustomAuthentication authenticationResult = (CustomAuthentication) authenticationManager.authenticate(authentication);
            if (ObjectUtils.isEmpty(authenticationResult)) {
                return;
            }
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            customAuthEntrypoint.commence(request, response, e);
        }
    }
}
