package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.*;
import com.inqoo.project_cayliflower_backend.repository.TrainerRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainingRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainingScheduleRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
class CauliflowerServiceTest {

    @Autowired
    private TrainerRepo trainerRepo;
    @Autowired
    private TrainingRepo trainingRepo;
    @Autowired
    private CauliflowerService cauliflowerService;
    @Autowired
    private TrainingScheduleRepo trainingScheduleRepo;

    private void aTrainer(String firstName, String lastName) {
        trainerRepo.save(new Trainer(firstName, lastName, "Bio", new HashSet<>()));
    }

    private void aTraining(String name) {
        String description = "Testowo";
        BigDecimal price = BigDecimal.valueOf(34);
        int duration = 5;

        trainingRepo.save(new Training(name, description, price, duration, new HashSet<>()));
    }

    @Test
    public void shouldAssignTrainer() {
        //given
        aTrainer("Jan", "Dzban");
        aTraining("Spring");

        Set<Instant> dates = new HashSet<>();
        dates.add(Instant.parse("2023-02-03T10:30:00.00Z"));
        dates.add(Instant.parse("2023-02-10T10:30:00.00Z"));

        TrainerToTrainingAssigmentDTO trainerToTrainingAssigmentDTO
                = new TrainerToTrainingAssigmentDTO(
                "Spring", "Jan", "Dzban", dates);
        //when
        cauliflowerService.assignToTraining(trainerToTrainingAssigmentDTO);

        var anything = trainingScheduleRepo.findByTrainingName("Spring").stream()
                .map(TrainingSchedule::getTrainerScheduleEntries)
                .filter(t -> t.stream()
                        .map(TrainerScheduleEntry::getDate).equals(LocalDate.of(2023, 2,3)))
                .collect(Collectors.toList());

        //then

    }
}