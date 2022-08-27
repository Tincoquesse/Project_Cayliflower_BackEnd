package com.inqoo.project_cayliflower_backend.exceptions;

public class TrainingNotExistingException extends RuntimeException{
    public TrainingNotExistingException(){
        super("Training Not Found");
    }

}
