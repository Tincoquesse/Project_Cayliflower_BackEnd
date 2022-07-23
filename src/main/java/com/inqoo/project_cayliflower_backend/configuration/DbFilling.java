package com.inqoo.project_cayliflower_backend.configuration;


import com.inqoo.project_cayliflower_backend.controller.CauliflowerRestController;
import com.inqoo.project_cayliflower_backend.model.CategoryDTO;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Profile("prod")
public class DbFilling {

    CauliflowerRestController controller;

    public DbFilling(CauliflowerRestController controller) {
        this.controller = controller;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        controller.addCategory(new CategoryDTO("IT", "IT Categoty", Collections.emptyList()));
        controller.addCategory(new CategoryDTO("Sales", "Sales Categoty", Collections.emptyList()));
        controller.addCategory(new CategoryDTO("Marketing", "Marketing Categoty", Collections.emptyList()));
        controller.addCategory(new CategoryDTO("Other", "Other Categoty", Collections.emptyList()));
    }

}
