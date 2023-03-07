package com.tungns.spingsecurityaa.config;

import com.tungns.spingsecurityaa.property.AppConfigProperty;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;

@Configuration
@AllArgsConstructor
public class AppConfig {

    private final AppConfigProperty appConfigProperty;
    private final HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @Bean
    public List<MvcRequestMatcher> mvcRequestMatchers() {
        return appConfigProperty.getPublicUrls().stream().map(url -> new MvcRequestMatcher(mvcHandlerMappingIntrospector, url)).toList();
    }
}
