package com.inqoo.project_cayliflower_backend.controller;

import com.inqoo.project_cayliflower_backend.model.*;
import com.inqoo.project_cayliflower_backend.service.CauliflowerService;
import com.inqoo.project_cayliflower_backend.service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CauliflowerRestController {

    private final CauliflowerService cauliflowerService;
    private final TrainerService trainerService;

    public CauliflowerRestController(CauliflowerService cauliflowerService, TrainerService trainerService) {
        this.cauliflowerService = cauliflowerService;
        this.trainerService = trainerService;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok().body(cauliflowerService.addCategory(categoryDTO));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        return ResponseEntity.ok().body(cauliflowerService.getAllCategories());
    }

    @PostMapping("/category/{category}/subcategory")
    public ResponseEntity<SubcategoryDTO> addSubcategory(@RequestBody SubcategoryDTO subcategoryDTO, @PathVariable String category) {
        return ResponseEntity.ok().body(cauliflowerService.addSubcategory(subcategoryDTO, category));
    }

    @PostMapping("/subcategory/{subcategory}/training")
    public ResponseEntity<TrainingDTO> addTraining(@RequestBody TrainingDTO trainingDTO, @PathVariable String subcategory) {
        return ResponseEntity.ok().body(cauliflowerService.addTraining(trainingDTO, subcategory));
    }

    @GetMapping("/category/{category}/subcategories")
    public ResponseEntity<List<SubcategoryDTO>> getSubcategoriesFromCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(cauliflowerService.getSubcategoriesFromCategory(category));
    }

    @GetMapping("/subcategory/{subcategory}/trainings")
    public ResponseEntity<List<TrainingDTO>> getTrainingsFromSubcategories(@PathVariable String subcategory) {
        return ResponseEntity.ok().body(cauliflowerService.getTrainingsFromSubcategory(subcategory));
    }

    @GetMapping("/trainers")
    public ResponseEntity<List<TrainerDTO>> getTrainer() {
        return ResponseEntity.ok().body(trainerService.getTrainers());
    }

    @PostMapping("/trainer")
    public ResponseEntity<TrainerDTO> addTrainer(@RequestBody TrainerDTO trainerDTO) {
        return ResponseEntity.created(URI.create("/api/trainer")).body(trainerService.addTrainer(trainerDTO));
    }

    @PostMapping("/assigment")
    public ResponseEntity<Void> assignTrainerToTraining(@RequestBody TrainerToTrainingAssigmentDTO assigmentDTO) {
        this.cauliflowerService.assignToTraining(assigmentDTO);
        return ResponseEntity.ok().build();
    }
}

