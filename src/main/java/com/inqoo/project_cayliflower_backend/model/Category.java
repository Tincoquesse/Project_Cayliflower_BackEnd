package com.inqoo.project_cayliflower_backend.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany
    List<Subcategory> subcategories;

    public Category(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
