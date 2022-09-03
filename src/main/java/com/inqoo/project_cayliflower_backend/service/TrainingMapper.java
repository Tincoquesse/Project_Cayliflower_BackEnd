package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.Training;
import com.inqoo.project_cayliflower_backend.model.TrainingDTO;

import java.util.HashSet;

public class TrainingMapper {

    public static Training fromDTO(TrainingDTO trainingDTO) {
        return new Training(trainingDTO.getName(), trainingDTO.getDescription(), trainingDTO.getPrice(), trainingDTO.getDuration(), trainingDTO.getTrainers());
    }

    public static TrainingDTO fromEntity(Training training) {
        return new TrainingDTO(training.getName(), training.getDescription(), training.getPrice(), training.getDuration(), training.getTrainers(), new HashSet<>());
    }
}
