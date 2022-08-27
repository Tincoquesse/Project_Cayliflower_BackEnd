package com.inqoo.project_cayliflower_backend.exceptions;

public class SubcategoryNotExistingException extends RuntimeException {

    public SubcategoryNotExistingException() {
        super("Subcategory Not Found");
    }
}
