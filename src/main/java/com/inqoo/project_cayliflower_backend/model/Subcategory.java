package com.inqoo.project_cayliflower_backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Training> trainings;

    @ManyToOne
    private Category category;

    public Subcategory() {
    }

    public Subcategory(List<Training> trainings, Category category) {
        this.trainings = trainings;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
