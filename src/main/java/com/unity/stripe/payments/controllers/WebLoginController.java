package com.unity.stripe.payments.controllers;


import com.unity.stripe.payments.entity.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.PublicKey;

@Controller
public class WebLoginController {

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("CustomerIdentity", new Customer());
        return "login";
    }

}
