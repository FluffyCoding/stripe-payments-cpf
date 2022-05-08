package com.unity.stripe.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserAuthGroup {

    @Id
    @Column(name = "user_auth_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "user_role")
    private String userRole;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
