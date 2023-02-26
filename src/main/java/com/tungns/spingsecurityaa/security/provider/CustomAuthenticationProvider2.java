package com.tungns.spingsecurityaa.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class CustomAuthenticationProvider2 implements AuthenticationProvider {

    public UsernamePasswordAuthenticationToken authenticate(Authentication authentication) throws AuthenticationException {
        if (!authentication.getName().equals("tungns") || !authentication.getCredentials().equals("1234")) {
            throw new BadCredentialsException("Invalid username and password");
        }
        return new UsernamePasswordAuthenticationToken("", "", List.of(new SimpleGrantedAuthority("admin")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
