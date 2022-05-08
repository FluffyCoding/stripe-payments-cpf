package com.unity.stripe.payments.controllers;

import com.unity.stripe.payments.entity.Customer;
import com.unity.stripe.payments.entity.User;
import com.unity.stripe.payments.entity.UserAuthGroup;
import com.unity.stripe.payments.secure.UserManagementService;
import com.unity.stripe.payments.service.CustomerRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(path = "/register")
public class WebRegisterController {

    private final CustomerRegistrationService customerService;
    private final UserManagementService userService;

    public WebRegisterController(CustomerRegistrationService customerService,
                                 UserManagementService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public String InitiateRegistration(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "register";
    }

    @PostMapping(path = "/")
    public String submitRegistration(@ModelAttribute("newCustomer") @Valid Customer customer,
                                     BindingResult result, Model model, HttpServletRequest request) {

        if (result.hasErrors()) {
            model.addAttribute("formError", "There Is Some Error");
            return "register";
        } else {
            customerService.saveCustomer(customer);
            var user = new User();
            var userAdminGroup = new UserAuthGroup();
            userAdminGroup.setEmail(customer.getEmail());
            userAdminGroup.setUserRole("ROLE_ADMIN");
            var userUserGroup = new UserAuthGroup();
            userUserGroup.setEmail(customer.getEmail());
            userUserGroup.setUserRole("ROLE_USER");


            List<UserAuthGroup> userRollsList = new ArrayList<>();
            userRollsList.add(userAdminGroup);
            userRollsList.add(userUserGroup);

            user.setEmail(customer.getEmail());
            user.setFullName(customer.getFullName());
            user.setPassword(customer.getPassword());
            userService.saveUserDetail(user, userRollsList);

            userService.authWithHttpServletRequest(request, user.getEmail(), user.getPassword());

            return "redirect:/product/";
        }
    }


}
