package com.inqoo.project_cayliflower_backend.model;

public class TrainerToTrainingAssigmentDTO {

    private final String trainingName;
    private final String trainerFirstName;
    private final String trainerLastName;

    public TrainerToTrainingAssigmentDTO(String trainingName,
                                         String trainerFirstName,
                                         String trainerLastName) {
        this.trainingName = trainingName;
        this.trainerFirstName = trainerFirstName;
        this.trainerLastName = trainerLastName;
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

}
