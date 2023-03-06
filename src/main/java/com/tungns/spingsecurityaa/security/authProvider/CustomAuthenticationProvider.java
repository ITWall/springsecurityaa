package com.tungns.spingsecurityaa.security.authProvider;

import com.tungns.spingsecurityaa.repository.UserRepository;
import com.tungns.spingsecurityaa.security.authentication.CustomAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var user = userRepository.findByUsername(authentication.getName()).orElse(null);
        if (ObjectUtils.isEmpty(user)) {
            throw new BadCredentialsException("username not found");
        }
        if (!user.getPassword().equals(authentication.getCredentials())) {
            throw new BadCredentialsException("incorrect password");
        }
        var authorities = user.getRole().getAuthorities();
        var grantedAuthorities = authorities.stream().map(authorityEntity -> (GrantedAuthority) new SimpleGrantedAuthority(authorityEntity.getCode()))
                .collect(Collectors.toList());
        var customAuthentication = new CustomAuthentication()
                .setName(authentication.getName())
                .setCredential("")
                .setGrantedAuthorities(grantedAuthorities);
        customAuthentication.setAuthenticated(true);
        return customAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthentication.class);
    }

    public boolean notSupports(Class<?> authentication) {
        return !supports(authentication);
    }
}
