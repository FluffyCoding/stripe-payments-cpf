package com.unity.stripe.payments.service.implementation;

import com.unity.stripe.payments.dao.StripeTransactionRepository;
import com.unity.stripe.payments.entity.StripeTransaction;
import com.unity.stripe.payments.service.StripeTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StripeTransactionServiceImpl implements StripeTransactionService {

    @Autowired
    StripeTransactionRepository transactionRepository;

    @Override
    public void saveTransaction(StripeTransaction transaction) {
        transactionRepository.save(transaction);
    }
}
