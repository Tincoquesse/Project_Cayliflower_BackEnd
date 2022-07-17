package com.inqoo.project_cayliflower_backend.model;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    @ManyToOne
    private Subcategory subcategory;

    public Training() {
    }

    public Training(String name, String description, BigDecimal price, Subcategory subcategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.subcategory = subcategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }
}
