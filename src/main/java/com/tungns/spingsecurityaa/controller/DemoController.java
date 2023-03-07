package com.tungns.spingsecurityaa.controller;

import com.tungns.spingsecurityaa.security.AuthorityService;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class DemoController {

    @GetMapping("/read-sms")
    @PreAuthorize("hasAnyAuthority(@authorityService.getAuthoritiesIncludeSuper('READ_SMS'))")
    public String readSMS() {
        var enums = Arrays.stream(AuthorityService.AuthorityEnum.class.getEnumConstants()).map(Enum::name).toList();
        System.out.println(enums);
        return "read-sms";
    }

    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public String login() {
        return "login";
    }

    @GetMapping({"/logout", "/logout/{id}"})
    @PreAuthorize("permitAll()")
    public String logout(@PathVariable(required = false) String id) {
        return "logout";
    }
}
