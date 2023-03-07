package com.tungns.spingsecurityaa.security;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorityService {
    public List<String> getAuthorities(AuthorityEnum... authorities) {
        return Arrays.stream(authorities).map(Enum::name).toList();
    }

    public List<String> getAuthoritiesIncludeSuper(AuthorityEnum... authorities) {
        var auth = new java.util.ArrayList<>(Arrays.stream(authorities).map(Enum::name).toList());
        auth.add(AuthorityEnum.SUPER.name());
        return auth;
    }

    @Getter
    @AllArgsConstructor
    public enum AuthorityEnum {
        SUPER("SUPER"), READ_SMS("Read SMS");
        private final String title;
    }
}
