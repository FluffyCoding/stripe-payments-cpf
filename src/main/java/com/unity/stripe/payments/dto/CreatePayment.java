package com.unity.stripe.payments.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class CreatePayment {

    @NotNull(message = "Name Should Not Be Empty**")
    @Size(min = 5, max = 50)
    private String fullName;

    @NotNull
    @Size(min = 2, max = 100, message = "Invalid Remarks**")
    private String remarks;

    @NotEmpty(message = "Must be a valid Email Address")
    @Email(message = "Must be a valid Email Address")
    private String email;

    @NotNull
    @Digits(integer = 3, fraction = 2)
    @Min(value = 1, message = "Invalid Amount")
    private BigDecimal amount;

    private String cartId;

}
