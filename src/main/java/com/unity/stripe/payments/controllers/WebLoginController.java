package com.unity.stripe.payments.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebLoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }


}
