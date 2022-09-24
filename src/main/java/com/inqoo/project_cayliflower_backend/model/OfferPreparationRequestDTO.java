package com.inqoo.project_cayliflower_backend.model;

import java.util.Set;

public class OfferPreparationRequestDTO {

    private final Set<Training> trainings;
    private final String email;

    public OfferPreparationRequestDTO( Set<Training> trainings, String email) {
        this.trainings = trainings;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Set<Training> getTrainings() {
        return trainings;
    }
}
