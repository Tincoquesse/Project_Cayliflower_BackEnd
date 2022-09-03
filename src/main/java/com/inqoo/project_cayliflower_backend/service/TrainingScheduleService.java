package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.TrainingSchedule;
import com.inqoo.project_cayliflower_backend.repository.TrainingScheduleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingScheduleService {

    private final TrainingScheduleRepo trainingScheduleRepo;

    public TrainingScheduleService(TrainingScheduleRepo trainingScheduleRepo) {
        this.trainingScheduleRepo = trainingScheduleRepo;
    }

    public void addTrainingSchedule(TrainingSchedule trainingSchedule) {
        trainingScheduleRepo.save(trainingSchedule);
    }

    public List<TrainingSchedule> getTrainingScheduleByTrainingName(String trainingName) {
        return trainingScheduleRepo.findByTrainingName(trainingName);
    }

    public List<TrainingSchedule> getTrainingScheduleByTrainer(String firstName, String lastName) {
        return trainingScheduleRepo.findByTrainerFirstNameAndTrainerLastName(firstName, lastName);
    }
}
