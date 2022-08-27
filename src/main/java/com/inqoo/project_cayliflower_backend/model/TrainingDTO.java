package com.inqoo.project_cayliflower_backend.model;

import java.math.BigDecimal;
import java.util.List;

public class TrainingDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private List<Trainer> trainers;


    public TrainingDTO(String name, String description, BigDecimal price, int duration, List<Trainer> trainers) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }
}
