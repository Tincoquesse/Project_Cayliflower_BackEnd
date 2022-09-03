package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.OfferPreparationRequestDTO;
import com.inqoo.project_cayliflower_backend.repository.TrainingRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OfferService {

    private final TrainingRepo trainingRepo;

    public OfferService(TrainingRepo trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    public void processOffer(OfferPreparationRequestDTO requestDTO) {
        String description = trainingRepo.findByName(requestDTO.getTrainingName()).get().getDescription();
        String trainingName = requestDTO.getTrainingName();
        String trainerFirstName = requestDTO.getTrainerFirstName();
        String trainerLastName = requestDTO.getTrainerLastName();
        BigDecimal price = trainingRepo.findByName(requestDTO.getTrainingName()).get().getPrice();

    }
}
