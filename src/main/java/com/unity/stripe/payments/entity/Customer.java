package com.unity.stripe.payments.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "full_name", nullable = true)
    private String fullName;

    @NotEmpty(message = "Must be a valid Email Address")
    @Email
    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Transient
    @NotEmpty
    @NotNull
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @NotNull
    @NotEmpty
    //@Pattern(regexp = "([0-9\\-]+)", message = "Must Be A Valid Phone Number")
    private String phone;


//    @OneToMany(mappedBy = "customer", orphanRemoval = true, fetch = FetchType.LAZY)
//    private Set<ShippingAddress> address;

}

