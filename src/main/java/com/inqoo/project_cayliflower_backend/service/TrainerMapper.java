package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.Trainer;
import com.inqoo.project_cayliflower_backend.model.TrainerDTO;

import java.util.Collections;
import java.util.HashSet;

public class TrainerMapper {

    public static Trainer fromDTO(TrainerDTO trainerDTO){
        return new Trainer(trainerDTO.getFirstName(), trainerDTO.getLastName(),trainerDTO.getBio(), new HashSet<>());
    }
    public static TrainerDTO fromEntity(Trainer trainer){
        return new TrainerDTO(trainer.getFirstName(), trainer.getLastName(), trainer.getBio());
    }
}
