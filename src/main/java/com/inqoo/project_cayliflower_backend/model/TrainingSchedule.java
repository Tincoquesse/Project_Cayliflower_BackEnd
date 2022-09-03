package com.inqoo.project_cayliflower_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class TrainingSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String trainingName;
    private String trainerFirstName;
    private String trainerLastName;
    private Instant date;

    public TrainingSchedule(String trainingName, String trainerFirstName, String trainerLastName, Instant date) {
        this.trainingName = trainingName;
        this.trainerFirstName = trainerFirstName;
        this.trainerLastName = trainerLastName;
        this.date = date;
    }

    public TrainingSchedule() {
    }

    public String getTrainingName() {
        return trainingName;
    }

    public String getTrainerFirstName() {
        return trainerFirstName;
    }

    public String getTrainerLastName() {
        return trainerLastName;
    }

    public Instant getDate() {
        return date;
    }
}
