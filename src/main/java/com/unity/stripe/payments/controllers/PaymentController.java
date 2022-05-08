package com.unity.stripe.payments.controllers;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import com.unity.stripe.payments.dto.CreatePayment;
import com.unity.stripe.payments.dto.CreatePaymentResponse;
import com.unity.stripe.payments.service.implementation.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    private final CustomerService customerService;

    Logger log = LoggerFactory.getLogger(getClass());

    public PaymentController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {

        Map<String, String> transactionMetadata = new HashMap<>();

        var customerData = customerService.findCustomerByEmail(createPayment.getEmail());


        transactionMetadata.put("date", getCurrentDate());
        transactionMetadata.put("email", customerData.getEmail());
        transactionMetadata.put("fullName", customerData.getFullName());
        transactionMetadata.put("mobile", customerData.getPhone());
        transactionMetadata.put("cartId", createPayment.getCartId());
        transactionMetadata.put("amount", createPayment.getAmount().toString());
        transactionMetadata.put("remarks", createPayment.getRemarks());


        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()

                .setAmount(calculateAmountToCents(createPayment.getAmount()))
                .setCurrency("usd")
                .setReceiptEmail(createPayment.getEmail())
                .putAllMetadata(transactionMetadata)
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods
                                .builder()
                                .setEnabled(true)
                                .build()
                )
                .build();
        // Create a PaymentIntent with the order amount and currency
        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());
    }

    private Long calculateAmountToCents(BigDecimal amount) {
        var dResult = amount.multiply(BigDecimal.valueOf(100));
        return (long) Math.round(dResult.longValue());
    }


    String getCurrentDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }


}