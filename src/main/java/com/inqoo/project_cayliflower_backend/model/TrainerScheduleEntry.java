package com.inqoo.project_cayliflower_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;

@Entity
public class TrainerScheduleEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private Instant startDate;

    public TrainerScheduleEntry(String firstName, String lastName, Instant date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = date;
    }

    public TrainerScheduleEntry() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Instant getStartDate() {
        return startDate;
    }

}
