package com.inqoo.project_cayliflower_backend.exceptions;

public class TrainerNotExistingException extends RuntimeException{
    public TrainerNotExistingException(){
        super("Trainer Not Found");
    }
}
