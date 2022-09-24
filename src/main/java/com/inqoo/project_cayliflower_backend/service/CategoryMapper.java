package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.Category;
import com.inqoo.project_cayliflower_backend.model.CategoryDTO;

import java.util.ArrayList;

public class CategoryMapper {

    public static Category fromDTO(CategoryDTO categoryDTO){
        return new Category(categoryDTO.getName(), categoryDTO.getDescription(), new ArrayList<>());
    }
    public static CategoryDTO fromEntity(Category category) {
        return new CategoryDTO(category.getName(), category.getDescription(),category.getSubcategories());
    }
}
