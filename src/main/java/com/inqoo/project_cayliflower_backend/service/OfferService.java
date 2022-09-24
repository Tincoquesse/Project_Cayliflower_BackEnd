package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.OfferPreparationRequestDTO;
import com.inqoo.project_cayliflower_backend.repository.TrainingRepo;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final TrainingRepo trainingRepo;

    public OfferService(TrainingRepo trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    public void processOffer(OfferPreparationRequestDTO requestDTO) {
// TODO: email sender
    }
}
