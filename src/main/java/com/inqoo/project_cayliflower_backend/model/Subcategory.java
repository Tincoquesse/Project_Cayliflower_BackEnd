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

    public Subcategory() {
    }

    public Subcategory(List<Training> trainings) {
        this.trainings = trainings;

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

}
