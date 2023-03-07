package com.tungns.spingsecurityaa.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app.config")
@Data
public class AppConfigProperty {
    private List<String> publicUrls;
}
