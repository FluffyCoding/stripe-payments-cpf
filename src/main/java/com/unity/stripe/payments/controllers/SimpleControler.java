package com.unity.stripe.payments.controllers;

import com.unity.stripe.payments.entity.Customer;
import com.unity.stripe.payments.entity.Gift;
import com.unity.stripe.payments.service.implementation.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class SimpleControler {

    @Autowired
    GiftService giftService;

    @GetMapping("/")
    public String show(){

        Gift gift = giftService.findGiftByPaymentId("pi_3KxBXRDxaIGBPlwA2YxGCG2x");
        System.out.println(gift.getCustomerEmail());

        return "public";
    }

}
