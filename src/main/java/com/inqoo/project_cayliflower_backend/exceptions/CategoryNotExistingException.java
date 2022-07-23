package com.inqoo.project_cayliflower_backend.exceptions;

public class CategoryNotExistingException extends RuntimeException{
    public CategoryNotExistingException() {
        super("Category Not Found");
    }
}
