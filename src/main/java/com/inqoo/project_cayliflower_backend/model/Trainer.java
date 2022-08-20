package com.inqoo.project_cayliflower_backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String bio;

    @ManyToMany
    private List<Training> trainings;

    public Trainer() {
    }

    public Trainer(String firstName, String lastName, String bio, List<Training> trainings) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.trainings = trainings;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public Long getId() {
        return id;
    }
}


