package com.inqoo.project_cayliflower_backend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TrainingSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String trainingName;
    @OneToMany
    private Set<TrainerScheduleEntry> trainerScheduleEntries;

    public TrainingSchedule(String trainingName, Set<TrainerScheduleEntry> trainerScheduleEntries) {
        this.trainingName = trainingName;

        this.trainerScheduleEntries = trainerScheduleEntries;
    }

    public TrainingSchedule() {
    }

    public String getTrainingName() {
        return trainingName;
    }

    public Set<TrainerScheduleEntry> getTrainerScheduleEntries() {
        return trainerScheduleEntries;
    }
}
