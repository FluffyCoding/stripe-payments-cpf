package com.unity.stripe.payments.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty(message = "Email cannot be empty.")
    @Email
    private String email;

    @Column(name = "full_name", nullable = false)
    @Size(min = 5, max = 30)
    private String fullName;

    @Column(name = "user_password", nullable = false)
    @Size(min = 5, max = 35, message = "Password Should be Greater Than 5 Character")
    private String Password;

}
