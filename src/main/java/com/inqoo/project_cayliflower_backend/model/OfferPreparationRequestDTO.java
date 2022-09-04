package com.inqoo.project_cayliflower_backend.model;

public class OfferPreparationRequestDTO {

    private final String trainingName;
    private final String subcategoryName;
    private final String trainerFirstName;
    private final String trainerLastName;
    private final String email;

    public OfferPreparationRequestDTO(String trainingName, String subcategoryName, String trainerFirstName, String trainerLastName, String email) {
        this.trainingName = trainingName;
        this.subcategoryName = subcategoryName;
        this.trainerFirstName = trainerFirstName;
        this.trainerLastName = trainerLastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
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
