package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.Category;
import com.inqoo.project_cayliflower_backend.model.CategoryDTO;
import com.inqoo.project_cayliflower_backend.repository.CategoryRepo;
import com.inqoo.project_cayliflower_backend.repository.SubcategoryRepo;
import com.inqoo.project_cayliflower_backend.repository.TrainingRepo;
import org.springframework.stereotype.Service;

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
        Category save = categoryRepo.save(CategoryMapper.fromDTO(categoryDTO));
        return CategoryMapper.fromEntity(save);
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(CategoryMapper::fromEntity)
                .collect(Collectors.toList());
    }

}
