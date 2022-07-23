package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.Subcategory;
import com.inqoo.project_cayliflower_backend.model.SubcategoryDTO;

import java.util.Collections;

public class SubcategoryMapper {

    public static Subcategory fromDTO(SubcategoryDTO subcategoryDTO){
        return new Subcategory(subcategoryDTO.getName(), Collections.emptyList());
    }
    public static SubcategoryDTO fromEntity(Subcategory subcategory){
        return new SubcategoryDTO(subcategory.getName());
    }
}
