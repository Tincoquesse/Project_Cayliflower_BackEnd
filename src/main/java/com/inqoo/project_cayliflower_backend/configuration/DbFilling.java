package com.inqoo.project_cayliflower_backend.configuration;


import com.inqoo.project_cayliflower_backend.controller.CauliflowerRestController;
import com.inqoo.project_cayliflower_backend.model.*;
import com.inqoo.project_cayliflower_backend.service.OfferService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;

@Component
@Profile("prod")
public class DbFilling {

    private CauliflowerRestController controller;
    private OfferService offerService;


    public DbFilling(CauliflowerRestController controller, OfferService offerService) {
        this.controller = controller;
        this.offerService = offerService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws MessagingException, UnsupportedEncodingException {
        controller.addCategory(new CategoryDTO("IT", "IT Category", Collections.emptyList()));
        controller.addCategory(new CategoryDTO("Sprzedaż", "Sales Category", Collections.emptyList()));
        controller.addCategory(new CategoryDTO("Marketing", "Marketing Category", Collections.emptyList()));
        controller.addCategory(new CategoryDTO("Inne", "Other Category", Collections.emptyList()));

        controller.addSubcategory(new SubcategoryDTO("Java"),"IT");
        controller.addSubcategory(new SubcategoryDTO("cSharp"),"IT");
        controller.addSubcategory(new SubcategoryDTO("JS"),"IT");
        controller.addSubcategory(new SubcategoryDTO("DevOps"),"IT");

        controller.addTraining(new TrainingDTO("Java Podstawy","Kurs skierowany do osób, " +
                "chcących rozpocząć swoją przygodę z branżą IT, nie wymaga wcześniejszego " +
                "doświadczenia w programowaniu",
                new BigDecimal(100),10,new HashSet<>(),new HashSet<>()),"Java");
        controller.addTraining(new TrainingDTO("Java Zaawansowana","Szkolenie dla osób które posiadają doświadczenie w branży IT ",
                new BigDecimal(100),10,new HashSet<>(),new HashSet<>()),"Java");
        controller.addTraining(new TrainingDTO("Spring","Test",
                new BigDecimal(100),10,new HashSet<>(),new HashSet<>()),"Java");
        controller.addTraining(new TrainingDTO("Hibernate","Test",
                new BigDecimal(100),10,new HashSet<>(),new HashSet<>()),"Java");

        controller.addTrainer(new TrainerDTO("Paweł", "Gajec", "Lubię placki"));
        controller.addTrainer(new TrainerDTO("Kamil", "Kotlarz", "Lubię placki"));

        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Java Zaawansowana", "Paweł", "Gajec", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Java Zaawansowana", "Kamil", "Kotlarz", new HashSet<>()));
    }
}
