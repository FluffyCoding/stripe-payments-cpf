package com.unity.stripe.payments.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;

@Controller
public class UserDashboardWebController {


    @GetMapping(path = "/")
    public String showLandingPage(Model model, Principal principal) {
        model.addAttribute("userEmail", principal.getName());
        return "landingPage";
    }


}
