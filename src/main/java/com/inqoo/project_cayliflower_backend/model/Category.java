package com.inqoo.project_cayliflower_backend.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;
    @OneToMany
    private List<Subcategory> subcategories;

    public Category(String name, String description, List<Subcategory> subcategories) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSubcategory(Subcategory save) {
        subcategories.add(save);
    }
}
