package com.inqoo.project_cayliflower_backend;

import com.inqoo.project_cayliflower_backend.controller.CauliflowerRestController;
import com.inqoo.project_cayliflower_backend.model.CategoryDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Collections;

@SpringBootApplication
public class ProjectCauliflowerBackEndApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProjectCauliflowerBackEndApplication.class, args);
    }
}
