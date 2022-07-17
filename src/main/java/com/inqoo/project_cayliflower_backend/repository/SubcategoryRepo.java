package com.inqoo.project_cayliflower_backend.repository;

import com.inqoo.project_cayliflower_backend.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {

    Optional<Subcategory> findByName(String name);
}
