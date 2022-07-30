package com.inqoo.project_cayliflower_backend.controller;

import com.inqoo.project_cayliflower_backend.model.Category;
import com.inqoo.project_cayliflower_backend.model.CategoryDTO;
import com.inqoo.project_cayliflower_backend.model.SubcategoryDTO;
import com.inqoo.project_cayliflower_backend.model.TrainingDTO;
import com.inqoo.project_cayliflower_backend.service.CauliflowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok().body(cauliflowerService.addCategory(categoryDTO));
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        return ResponseEntity.ok().body(cauliflowerService.getAllCategories());
    }
    @PostMapping("/subcategory/add/{category}")
    public ResponseEntity<SubcategoryDTO> addSubcategory(@RequestBody SubcategoryDTO subcategoryDTO, @PathVariable String category){
        return ResponseEntity.ok().body(cauliflowerService.addSubcategory(subcategoryDTO,category));
    }

    @PostMapping("/training/add/{subcategory}")
    public ResponseEntity<TrainingDTO> addTraining(@RequestBody TrainingDTO trainingDTO, @PathVariable String subcategory){
        return ResponseEntity.ok().body(cauliflowerService.addTraining(trainingDTO, subcategory));
    }

    @GetMapping("/subcategory/get/{category}")
    public  ResponseEntity<List<SubcategoryDTO>> getSubcategoriesFromCategory(@PathVariable String category){
        return ResponseEntity.ok().body(cauliflowerService.getSubcategoriesFromCategory(category));
    }

    @GetMapping("/training/get/{subcategory}")
    public  ResponseEntity<List<TrainingDTO>> getTrainingsFromSubcategories(@PathVariable String subcategory){
        return ResponseEntity.ok().body(cauliflowerService.getTrainingsFromSubcategory(subcategory));
    }

}
