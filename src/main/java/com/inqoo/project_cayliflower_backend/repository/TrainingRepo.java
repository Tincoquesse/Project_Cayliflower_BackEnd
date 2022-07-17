package com.inqoo.project_cayliflower_backend.repository;

import com.inqoo.project_cayliflower_backend.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingRepo extends JpaRepository<Training, Long> {

    Optional<Training> findAllByName(String name);
}
