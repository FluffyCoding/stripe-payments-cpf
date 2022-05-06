package com.unity.stripe.payments.controllers;

import com.unity.stripe.payments.dto.CreatePayment;
import com.unity.stripe.payments.service.ProductService;
import com.unity.stripe.payments.service.implementation.CustomerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/product")
public class WebController {


    private final ProductService productService;
    private final CustomerService customerService;


    public WebController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }


    @Value("${stripe-public-key}")
    private String publicKey;

    @GetMapping(path = "/")
    public String indexPage(Model model) {
        model.addAttribute("customer", customerService.findCustomerById(1));
        model.addAttribute("coffee", productService.getProductById(258));
        model.addAttribute("cola", productService.getProductById(259));
        return "gifts";
    }

    @GetMapping(path = "/{pid}")
    public String payment(@PathVariable("pid") int pid, Model model) {

        var createPayment = new CreatePayment();
        var customer = customerService.findCustomerById(1);
        var product = productService.getProductById(pid);

        createPayment.setEmail(customer.getEmail());
        createPayment.setFullName(customer.getFullName());
        createPayment.setAmount(product.getPrice());
        createPayment.setRemarks(product.getDescription());
        createPayment.setCartId(product.getProductCode());

        model.addAttribute("stripePublicKey", publicKey);
        model.addAttribute("creatPayment", createPayment);
        return "checkout";
    }

    @GetMapping(path = "/conformed")
    public String redirectToConformed() {
        return "checkout-success";
    }

}
