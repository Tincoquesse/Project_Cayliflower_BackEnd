package com.inqoo.project_cayliflower_backend.repository;

import com.inqoo.project_cayliflower_backend.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepo  extends JpaRepository<Trainer, Long> {

    Optional<Trainer> findByFirstNameAndLastName(String firstName, String lastName);
}
