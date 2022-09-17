package com.inqoo.project_cayliflower_backend.model;

import java.time.Instant;
import java.util.Set;

public class TrainerToTrainingAssigmentDTO {

    private final String trainingName;
    private final String trainerFirstName;
    private final String trainerLastName;
    private final Set<Instant> dates;


    public TrainerToTrainingAssigmentDTO(String trainingName,
                                         String trainerFirstName,
                                         String trainerLastName, Set<Instant> dates) {
        this.trainingName = trainingName;
        this.trainerFirstName = trainerFirstName;
        this.trainerLastName = trainerLastName;
        this.dates = dates;
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

    public Set<Instant> getDates() {
        return dates;
    }
}
