package com.inqoo.project_cayliflower_backend.repository;

import com.inqoo.project_cayliflower_backend.model.TrainerScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingScheduleEntryRepo extends JpaRepository<TrainerScheduleEntry, Long> {

}
