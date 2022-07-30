package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.exceptions.CategoryNotExistingException;
import com.inqoo.project_cayliflower_backend.exceptions.NameAlreadyTakenException;
import com.inqoo.project_cayliflower_backend.exceptions.SubcategoryNotExistingException;
import com.inqoo.project_cayliflower_backend.model.*;
import com.inqoo.project_cayliflower_backend.repository.CategoryRepo;
import com.inqoo.project_cayliflower_backend.repository.SubcategoryRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainingRepo;
import com.sun.xml.bind.v2.TODO;
import org.springframework.stereotype.Service;

import javax.naming.NameAlreadyBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CauliflowerService {

    private final TrainingRepo trainingRepo;
    private final SubcategoryRepo subcategoryRepo;
    private final CategoryRepo categoryRepo;

    public CauliflowerService(TrainingRepo trainingRepo, SubcategoryRepo subcategoryRepo, CategoryRepo categoryRepo) {
        this.trainingRepo = trainingRepo;
        this.subcategoryRepo = subcategoryRepo;
        this.categoryRepo = categoryRepo;
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO){
        if (categoryRepo.findByName(categoryDTO.getName()).isPresent()){
            throw new NameAlreadyTakenException();
        } else {
        Category save = categoryRepo.save(CategoryMapper.fromDTO(categoryDTO));
        return CategoryMapper.fromEntity(save);
        }
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(CategoryMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public SubcategoryDTO addSubcategory(SubcategoryDTO subcategoryDTO, String categoryName){
        if (categoryRepo.findByName(categoryName).isEmpty()){
            throw new CategoryNotExistingException();
        }
        Category category = categoryRepo.findByName(categoryName).orElseThrow();
        if(category.getSubcategories().stream()
                .anyMatch(subcategory -> subcategory.getName().equalsIgnoreCase(subcategoryDTO.getName()))){
            throw new NameAlreadyTakenException();
        }
        Subcategory save = subcategoryRepo.save(SubcategoryMapper.fromDTO(subcategoryDTO));
        category.getSubcategories().add(save);
        categoryRepo.save(category);
        return SubcategoryMapper.fromEntity(save);
    }

    public List<SubcategoryDTO> getSubcategoriesFromCategory(String categoryName){
        if (categoryRepo.findByName(categoryName).isEmpty()){
            throw new CategoryNotExistingException();
        }
        Category category = categoryRepo.findByName(categoryName).orElseThrow();
        ArrayList<SubcategoryDTO> subcategoryDTOS = new ArrayList<>();
        category.getSubcategories().stream()
                .forEach(subcategory ->subcategoryDTOS.add(SubcategoryMapper.fromEntity(subcategory)) );
        return subcategoryDTOS;
    }

    public TrainingDTO addTraining(TrainingDTO trainingDTO, String subcategoryName) {
        if (subcategoryRepo.findByName(subcategoryName).isEmpty()){
            throw new SubcategoryNotExistingException();
        }
        Subcategory subcategory = subcategoryRepo.findByName(subcategoryName).orElseThrow();
        if (subcategory.getTrainings().stream()
                .anyMatch(training -> training.getName().equalsIgnoreCase(trainingDTO.getName()))){
            throw new NameAlreadyTakenException();
        }
        Training save = trainingRepo.save(TrainingMapper.fromDTO(trainingDTO));
        subcategory.getTrainings().add(save);
        subcategoryRepo.save(subcategory);
        return TrainingMapper.fromEntity(save);
    }
}
