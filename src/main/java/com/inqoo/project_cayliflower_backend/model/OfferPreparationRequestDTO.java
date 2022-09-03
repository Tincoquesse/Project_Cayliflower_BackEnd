package com.inqoo.project_cayliflower_backend.model;

public class OfferPreparationRequestDTO {

    private final String trainingName;
    private final String subcategoryName;
    private final String trainerFirstName;
    private final String trainerLastName;

    public OfferPreparationRequestDTO(String trainingName, String subcategoryName, String trainerFirstName, String trainerLastName) {
        this.trainingName = trainingName;
        this.subcategoryName = subcategoryName;
        this.trainerFirstName = trainerFirstName;
        this.trainerLastName = trainerLastName;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public String getTrainerFirstName() {
        return trainerFirstName;
    }

    public String getTrainerLastName() {
        return trainerLastName;
    }
}
