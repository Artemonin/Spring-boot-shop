package com.pitonov.myshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "product")
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotEmpty
    @NotNull
    private String name;

    @Column(name = "decription", length = 400)
    @NotEmpty
    @NotNull
    private String description;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;
}
