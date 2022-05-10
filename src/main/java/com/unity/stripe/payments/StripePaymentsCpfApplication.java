package com.unity.stripe.payments;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StripePaymentsCpfApplication {

    @Value("${stripe-api-key}")
    private String stripe_key;

    @PostConstruct
    public void setup() {
        //System.out.println(stripe_key);
        Stripe.apiKey = stripe_key;
    }

    public static void main(String[] args) {
        SpringApplication.run(StripePaymentsCpfApplication.class, args);
    }


}
