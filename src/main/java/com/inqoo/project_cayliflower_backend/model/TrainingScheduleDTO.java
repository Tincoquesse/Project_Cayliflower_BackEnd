package com.inqoo.project_cayliflower_backend.model;

import java.time.Instant;

public class TrainingScheduleDTO {
    private String trainingName;
    private String trainerFirstName;
    private String trainerLastName;
    private Instant date;

    public TrainingScheduleDTO(String trainingName, String trainerFirstName, String trainerLastName, Instant date) {
        this.trainingName = trainingName;
        this.trainerFirstName = trainerFirstName;
        this.trainerLastName = trainerLastName;
        this.date = date;
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
