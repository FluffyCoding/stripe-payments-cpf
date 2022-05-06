package com.unity.stripe.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "gifts")
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private Date date;

    private String giftCode;
    
    private String transactionId;

    private String customerEmail;

    private BigDecimal transactionAmount;

    private String remarks;
}
