package com.unity.stripe.payments.controllers;

import com.google.gson.JsonSyntaxException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import com.unity.stripe.payments.entity.Gift;
import com.unity.stripe.payments.entity.StripeTransaction;
import com.unity.stripe.payments.service.StripeTransactionService;
import com.unity.stripe.payments.service.implementation.GiftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class StripeWebhookController {


    private final StripeTransactionService transactionService;
    private final GiftService giftService;

    public StripeWebhookController(StripeTransactionService transactionService, GiftService giftService) {

        this.transactionService = transactionService;
        this.giftService = giftService;
    }

    @Value("${stripe-webhook-secret}")
    private String endpointSecret;

    Logger logger = LoggerFactory.getLogger(getClass());


    @PostMapping(path = "/stripe/events")
    public String handleStripeEvent(@RequestBody String payload,
                                    @RequestHeader("Stripe-Signature") String sigHeader) {

        Event event;
        try {
            event = ApiResource.GSON.fromJson(payload, Event.class);
        } catch (JsonSyntaxException e) {
            // Invalid payload
            logger.info("Webhook error while parsing basic request.");
            return "";
        }
        if (sigHeader == null) {
            return "";
        }

        if (endpointSecret != null) {
            // Only verify the event if you have an endpoint secret defined.
            // Otherwise use the basic event deserialized with GSON.
            try {
                event = Webhook.constructEvent(
                        payload, sigHeader, endpointSecret
                );
            } catch (SignatureVerificationException e) {
                // Invalid signature
                logger.info("Webhook error while validating signature.");
                return "";
            }
        }
        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }
        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                logger.info("Payment for id {} , of Amount {} is succeeded ", paymentIntent.getId(), paymentIntent.getAmount());
                // Then define and call a method to handle the successful payment intent.
                handlePaymentIntentSucceeded(paymentIntent);
                break;
            case "payment_method.attached":
                PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
                // Then define and call a method to handle the successful attachment of a PaymentMethod.
                // handlePaymentMethodAttached(paymentMethod);
                break;
            default:
                logger.warn("Default event type: {}", event.getType());
                break;
        }
        return "";
    }

    private void handlePaymentIntentSucceeded(PaymentIntent paymentIntent) {

        try {
            saveTransaction(paymentIntent.getMetadata(), paymentIntent.getId());
        } catch (ParseException parseException) {
            logger.warn(parseException.getMessage(), "Data Parsing Error");
        }
        logger.info("Payment for id {} , of Amount {} is succeeded ", paymentIntent.getId(), paymentIntent.getAmount());

    }


    private void saveTransaction(Map<String, String> data, String transactionId) throws ParseException {

        var transaction = new StripeTransaction();
        transaction.setDate(dateFormatter(data.get("date")));
        transaction.setCustomerEmail(data.get("email"));
        transaction.setWebHookTransactionId(transactionId);
        transaction.setCartId(data.get("cartId"));
        transaction.setTransactionAmount(new BigDecimal(data.get("amount")));
        transaction.setCustomerContact(data.get("mobile"));
        transactionService.saveTransaction(transaction);

        var gift = new Gift();
        gift.setGiftCode(data.get("cartId"));
        gift.setDate(dateFormatter(data.get("date")));
        gift.setTransactionId(transactionId);
        gift.setTransactionAmount(new BigDecimal(data.get("amount")));
        gift.setCustomerEmail(data.get("email"));
        gift.setRemarks(data.get("remarks"));
        giftService.saveGift(gift);

    }

    private Date dateFormatter(String date) throws ParseException {
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(date);
    }

}




