package com.tungns.spingsecurityaa.security.manager;

import com.tungns.spingsecurityaa.security.authentication.CustomAuthentication;
import com.tungns.spingsecurityaa.security.provider.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomAuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        if (!authenticationProvider.supports(customAuthentication.getClass())) {
            throw new BadCredentialsException("Not supported");
        }
        Authentication authenticated = authenticationProvider.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        return authenticated;
    }
}
