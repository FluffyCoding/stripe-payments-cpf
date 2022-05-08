package com.unity.stripe.payments.service;

import com.unity.stripe.payments.entity.StripeTransaction;

public interface StripeTransactionService {

    void saveTransaction(StripeTransaction transaction);
    void showTransaction(String id);
}
