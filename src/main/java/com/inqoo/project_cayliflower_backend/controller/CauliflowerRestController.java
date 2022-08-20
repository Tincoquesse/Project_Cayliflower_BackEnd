package com.inqoo.project_cayliflower_backend.controller;

import com.inqoo.project_cayliflower_backend.model.*;
import com.inqoo.project_cayliflower_backend.service.CauliflowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CauliflowerRestController {

    private final CauliflowerService cauliflowerService;

    public CauliflowerRestController(CauliflowerService cauliflowerService) {
        this.cauliflowerService = cauliflowerService;
    }

    @PostMapping("/category/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok().body(cauliflowerService.addCategory(categoryDTO));
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        return ResponseEntity.ok().body(cauliflowerService.getAllCategories());
    }

    @PostMapping("/subcategory/add/{category}")
    public ResponseEntity<SubcategoryDTO> addSubcategory(@RequestBody SubcategoryDTO subcategoryDTO, @PathVariable String category) {
        return ResponseEntity.ok().body(cauliflowerService.addSubcategory(subcategoryDTO, category));
    }

    @PostMapping("/training/add/{subcategory}")
    public ResponseEntity<TrainingDTO> addTraining(@RequestBody TrainingDTO trainingDTO, @PathVariable String subcategory) {
        return ResponseEntity.ok().body(cauliflowerService.addTraining(trainingDTO, subcategory));
    }

    @GetMapping("/subcategory/get/{category}")
    public ResponseEntity<List<SubcategoryDTO>> getSubcategoriesFromCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(cauliflowerService.getSubcategoriesFromCategory(category));
    }

    @GetMapping("/training/get/{subcategory}")
    public ResponseEntity<List<TrainingDTO>> getTrainingsFromSubcategories(@PathVariable String subcategory) {
        return ResponseEntity.ok().body(cauliflowerService.getTrainingsFromSubcategory(subcategory));
    }

    @GetMapping("/trainer")
    public ResponseEntity<List<TrainerDTO>> getTrainer(){
        return ResponseEntity.ok().body(cauliflowerService.getTrainer());
    }

    @PostMapping("/trainer")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TrainerDTO> addTrainer(@RequestBody TrainerDTO trainerDTO) {
        return ResponseEntity.created(URI.create("/api/trainer")).body(cauliflowerService.addTrainer(trainerDTO));
    }

}
