package com.unity.stripe.payments.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class ShippingAddress {

    @Id
    private int id;

    private String city;

    private String country;

    private String line1;

    private String postalCode;

//    @ManyToOne()
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer;

}
