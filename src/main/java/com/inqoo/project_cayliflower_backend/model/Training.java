package com.inqoo.project_cayliflower_backend.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    @ManyToMany
    private Set<Trainer> trainers;

    private int duration;

    public Training() {
    }

    public Training(String name, String description, BigDecimal price, int duration, Set<Trainer> trainers) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.trainers = trainers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void addTrainer(Trainer trainer) {
        this.trainers.add(trainer);
    }

    @Override
    public String toString() {

        String trainersText = "";
        for (Trainer trainer: trainers){
            trainersText += trainer.toString() + "\n\n";
        }

        return "Kurs: \n" +
                "nazwa: " + name + "\n" +
                "opis: " + description + "\n" +
                "cena: " + price + " PLN \n" +
                "prowadzący: \n" + trainersText +
                "czas trwania: " + duration + " dni\n"
                ;
    }
}
