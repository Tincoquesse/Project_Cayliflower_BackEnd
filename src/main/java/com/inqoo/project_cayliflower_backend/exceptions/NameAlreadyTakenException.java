package com.inqoo.project_cayliflower_backend.exceptions;

public class NameAlreadyTakenException extends RuntimeException {

    public NameAlreadyTakenException() {
        super("Name Already Taken");
    }
}

