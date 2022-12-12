package com.slab.spring_security_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @PostMapping("/token")
    public String getToken() {

        String token="";
        return token;
    }
}
