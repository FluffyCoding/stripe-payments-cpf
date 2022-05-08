package com.unity.stripe.payments.controllers;

import com.unity.stripe.payments.dto.CreatePayment;
import com.unity.stripe.payments.service.ProductService;
import com.unity.stripe.payments.service.implementation.CustomerService;
import com.unity.stripe.payments.service.implementation.GiftService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping(path = "/product")
public class WebController {


    private final ProductService productService;
    private final CustomerService customerService;
    private final GiftService giftService;

    public WebController(ProductService productService, CustomerService customerService, GiftService giftService) {
        this.productService = productService;
        this.customerService = customerService;
        this.giftService = giftService;
    }


    @Value("${stripe-public-key}")
    private String publicKey;

    @GetMapping(path = "/")
    public String indexPage(Model model) {

        model.addAttribute("coffee", productService.getProductById(254));
        model.addAttribute("cola", productService.getProductById(255));
        return "gifts";
    }

    @GetMapping(path = "/{pid}")
    public String payment(@PathVariable("pid") int pid, Model model, Principal userPrincipal) {

        var cid = customerService.findCustomerByEmail(userPrincipal.getName());
        var createPayment = new CreatePayment();
        var customer = customerService.findCustomerById(cid.getId());
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
    public String redirectToConformed(@RequestParam("payment_intent") String paymentIntent,
                                      @RequestParam("payment_intent_client_secret") String secret,
                                      Model model) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);

        var gift = giftService.findGiftByPaymentId(paymentIntent);
        model.addAttribute("giftDetail", gift);
        return "checkout-success";
    }

}
