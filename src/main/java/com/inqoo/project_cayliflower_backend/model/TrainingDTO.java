package com.inqoo.project_cayliflower_backend.model;

import java.math.BigDecimal;
import java.util.Set;

public class TrainingDTO {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int duration;
    private final Set<Trainer> trainers;
    private final Set<TrainingScheduleDTO> scheduleDTOSet;


    public TrainingDTO(String name, String description, BigDecimal price, int duration, Set<Trainer> trainers, Set<TrainingScheduleDTO> scheduleDTOSet) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.trainers = trainers;
        this.scheduleDTOSet = scheduleDTOSet;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

}
