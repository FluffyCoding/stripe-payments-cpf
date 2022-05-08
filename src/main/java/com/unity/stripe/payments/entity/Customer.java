package com.unity.stripe.payments.entity;

import com.unity.stripe.payments.validators.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull
    @UniqueEmail
    @Column(name = "email")
    private String email;

    @Transient
    @NotEmpty
    @NotNull
    private String password;

    @NotNull
    @NotEmpty
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;


//    @OneToMany(mappedBy = "customer", orphanRemoval = true, fetch = FetchType.LAZY)
//    private Set<ShippingAddress> address;

}

