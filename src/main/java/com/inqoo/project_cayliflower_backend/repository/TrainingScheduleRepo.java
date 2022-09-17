package com.inqoo.project_cayliflower_backend.repository;

import com.inqoo.project_cayliflower_backend.model.TrainingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingScheduleRepo extends JpaRepository<TrainingSchedule, Long> {

    List<TrainingSchedule> findByTrainingName(String trainingName);

}
