package com.unity.stripe.payments.dao;

import com.unity.stripe.payments.entity.StripeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StripeTransactionRepository extends JpaRepository<StripeTransaction, Long> {


}
