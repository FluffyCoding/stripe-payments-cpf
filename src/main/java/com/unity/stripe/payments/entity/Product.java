package com.unity.stripe.payments.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    private Integer id;

    private String productCode;

    private String heading;

    private String description;

    private BigDecimal price;
}
