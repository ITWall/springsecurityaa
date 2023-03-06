package com.tungns.spingsecurityaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/read-sms")
//    @PreAuthorize("hasAuthority('READ_SMS')")
    public String demo() {
        return "read-sms";
    }
}
