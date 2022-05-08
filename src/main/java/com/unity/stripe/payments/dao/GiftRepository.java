package com.unity.stripe.payments.dao;

import com.unity.stripe.payments.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GiftRepository extends JpaRepository<Gift,Integer> {

    List<Gift> findAllByCustomerEmail(String email);

    Optional<Gift> findGiftByTransactionId (String id);
}
