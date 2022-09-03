package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.exceptions.NameAlreadyTakenException;
import com.inqoo.project_cayliflower_backend.model.Trainer;
import com.inqoo.project_cayliflower_backend.model.TrainerDTO;
import com.inqoo.project_cayliflower_backend.repository.TrainerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    private final TrainerRepo trainerRepo;

    public TrainerService(TrainerRepo trainerRepo) {

        this.trainerRepo = trainerRepo;
    }

    public TrainerDTO addTrainer(TrainerDTO trainerDTO) {
        if (trainerIsPresent(trainerDTO)) {
            throw new NameAlreadyTakenException();
        } else {
            Trainer savedTrainer = trainerRepo.save(TrainerMapper.fromDTO(trainerDTO));
            return TrainerMapper.fromEntity(savedTrainer);
        }
    }

    private boolean trainerIsPresent(TrainerDTO trainerDTO) {
        return trainerRepo.findByFirstNameAndLastName(trainerDTO.getFirstName(), trainerDTO.getLastName()).isPresent();
    }

    public List<TrainerDTO> getTrainers() {
        return trainerRepo.findAll().stream()
                .map(TrainerMapper::fromEntity)
                .collect(Collectors.toList());
    }

    Optional<Trainer> getTrainerFromRepo(String firstName, String lastName) {
        return trainerRepo.findByFirstNameAndLastName(firstName, lastName);
    }
}
