package com.inqoo.project_cayliflower_backend.model;

public class TrainerDTO {

    private final String firstName;
    private final String lastName;
    private final String bio;

    public TrainerDTO(String firstName, String lastName, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

}
