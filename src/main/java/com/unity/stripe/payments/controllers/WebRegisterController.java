package com.unity.stripe.payments.controllers;

import com.unity.stripe.payments.entity.Customer;
import com.unity.stripe.payments.entity.User;
import com.unity.stripe.payments.service.CustomerRegistrationService;
import com.unity.stripe.payments.service.UserRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/customer")
public class WebRegisterController {

    private final CustomerRegistrationService customerService;
    private final UserRegistrationService userService;

    public WebRegisterController(CustomerRegistrationService customerService,
                                 UserRegistrationService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping(path = "/new")
    public String InitiateRegistration(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "register";
    }

    @PostMapping(path = "/new")
    public String submitRegistration(@ModelAttribute @Valid Customer customer,
                                     BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("Error", "There Is Some Error");
            return "register";
        } else {
            var user = new User();
            user.setEmail(customer.getEmail());
            user.setFullName(customer.getFullName());
            user.setPassword(customer.getPassword());

            userService.saveUser(user);
            customerService.saveCustomer(customer);
            return "redirect:/product/";
        }
    }

}
