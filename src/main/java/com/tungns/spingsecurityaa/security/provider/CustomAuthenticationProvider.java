package com.tungns.spingsecurityaa.security.provider;

import com.tungns.spingsecurityaa.security.authentication.CustomAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final String secretKey;

    public CustomAuthenticationProvider(@Value("${app.secret-key}") String secretKey) {
        this.secretKey = secretKey;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        if (!secretKey.equals(customAuthentication.getKey())) {
            throw new BadCredentialsException("Invalid key");
        }
        return new CustomAuthentication(true, "");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthentication.class);
    }
}
