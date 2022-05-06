package com.unity.stripe.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "stripe_transactions")
public class StripeTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String webHookTransactionId;

    private Date date;

    private String cartId;

    private String customerEmail;

    private String customerContact;

    private BigDecimal transactionAmount;

}
