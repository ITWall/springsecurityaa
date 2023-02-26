package com.tungns.spingsecurityaa.security.manager;

import com.tungns.spingsecurityaa.security.provider.CustomAuthenticationProvider2;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class CustomAuthenticationManager2 implements AuthenticationManager {

    private final CustomAuthenticationProvider2 authenticationProvider2;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticated =  authenticationProvider2.authenticate(authentication);
        if (authenticated.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        }
        return authenticated;
    }
}
