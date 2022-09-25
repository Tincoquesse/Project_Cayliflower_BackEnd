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
        controller.addSubcategory(new SubcategoryDTO("C Sharp"),"IT");
        controller.addSubcategory(new SubcategoryDTO("JS"),"IT");
        controller.addSubcategory(new SubcategoryDTO("DevOps"),"IT");

        //Java

        controller.addTraining(new TrainingDTO("Java Podstawy","Kurs skierowany do osób, " +
                "chcących rozpocząć swoją przygodę z branżą IT, nie wymaga wcześniejszego " +
                "doświadczenia w programowaniu",
                new BigDecimal(3000),20,new HashSet<>(),new HashSet<>()),"Java");
        controller.addTraining(new TrainingDTO("Java Zaawansowana","Szkolenie dla osób które posiadają doświadczenie w branży IT, " +
                "chcących zgłębiać tajniki języka Java ",
                new BigDecimal(3000),25,new HashSet<>(),new HashSet<>()),"Java");
        controller.addTraining(new TrainingDTO("Spring","Spring to najpopularniejszy framework dla Java, dlatego jego znajomość jest " +
                "praktycznie niezbędna, jeśli myślisz o karierze Java Developera.",
                new BigDecimal(2500),15,new HashSet<>(),new HashSet<>()),"Java");
        controller.addTraining(new TrainingDTO("Hibernate","Hibernate framework stanowiący jedną z najpopularniejszych implementacji Java Persistence API (JPA)." +
                "Odpowiada za wygodna komunikację aplikacji z bazą danych.",
                new BigDecimal(2500),15,new HashSet<>(),new HashSet<>()),"Java");

        controller.addTrainer(new TrainerDTO("Paweł", "Gajec", "Lubię placki"));
        controller.addTrainer(new TrainerDTO("Kamil", "Kotlarz", "Lubię placki"));
        controller.addTrainer(new TrainerDTO("Piotr", "Laskowski", "Nie lubię placków"));

        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Java Podstawy", "Paweł", "Gajec", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Java Zaawansowana", "Paweł", "Gajec", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Java Zaawansowana", "Kamil", "Kotlarz", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Hibernate", "Kamil", "Kotlarz", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Hibernate", "Piotr", "Laskowski", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Spring", "Piotr", "Laskowski", new HashSet<>()));

        //C Sharp

        controller.addTraining(new TrainingDTO("C Sharp Podstawy","Kurs skierowany do osób, " +
                "chcących rozpocząć swoją przygodę z branżą IT, nie wymaga wcześniejszego " +
                "doświadczenia w programowaniu",
                new BigDecimal(3000),20,new HashSet<>(),new HashSet<>()),"C Sharp");
        controller.addTraining(new TrainingDTO("C Sharp Zaawansowany","Szkolenie dla osób które posiadają doświadczenie w branży IT, " +
                "chcących zgłębiać tajniki języka C # ",
                new BigDecimal(3000),25,new HashSet<>(),new HashSet<>()),"C Sharp");
        controller.addTraining(new TrainingDTO("C Sharp UI","Spring to najpopularniejszy framework dla Java, dlatego jego znajomość jest " +
                "praktycznie niezbędna, jeśli myślisz o karierze Java Developera.",
                new BigDecimal(2500),15,new HashSet<>(),new HashSet<>()),"C Sharp");
        controller.addTraining(new TrainingDTO("C Sharp Application Security","Hibernate framework stanowiący jedną z najpopularniejszych implementacji Java Persistence API (JPA)." +
                "Odpowiada za wygodna komunikację aplikacji z bazą danych.",
                new BigDecimal(2500),15,new HashSet<>(),new HashSet<>()),"C Sharp");

        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("C Sharp Podstawy", "Paweł", "Gajec", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("C Sharp Zaawansowany", "Piotr", "Laskowski", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("C Sharp Zaawansowany", "Kamil", "Kotlarz", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("C Sharp UI", "Kamil", "Kotlarz", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("C Sharp Application Security", "Piotr", "Laskowski", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("C Sharp Application Security", "Paweł", "Gajec", new HashSet<>()));

        //JS

        controller.addTraining(new TrainingDTO("JS Podstawy","Kurs skierowany do osób, " +
                "chcących rozpocząć swoją przygodę z branżą IT, nie wymaga wcześniejszego " +
                "doświadczenia w programowaniu",
                new BigDecimal(3000),20,new HashSet<>(),new HashSet<>()),"JS");
        controller.addTraining(new TrainingDTO("JS Zaawansowany","Szkolenie dla osób które posiadają doświadczenie w branży IT, " +
                "chcących zgłębiać tajniki języka JS ",
                new BigDecimal(3000),25,new HashSet<>(),new HashSet<>()),"JS");
        controller.addTraining(new TrainingDTO("Angular","Angular to kompleksowy framework do projektowania oraz tworzenia wydajnych aplikacji typu SPA (Single Page Application) . " +
                "Napisany w języku TypeScript i wspierany oraz rozwijany przez Google.",
                new BigDecimal(2500),15,new HashSet<>(),new HashSet<>()),"JS");
        controller.addTraining(new TrainingDTO("React","React jest nazywany JavaScriptową biblioteką, służącą do budowania interfejsów dla użytkownika. " +
                "Umożliwia tworzenie złożonych interfejsów za pomocą małych, odizolowanych od siebie komponentów",
                new BigDecimal(2500),15,new HashSet<>(),new HashSet<>()),"JS");

        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("JS Podstawy", "Paweł", "Gajec", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("JS Zaawansowany", "Piotr", "Laskowski", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("JS Zaawansowany", "Kamil", "Kotlarz", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Angular", "Kamil", "Kotlarz", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Angular", "Piotr", "Laskowski", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("React", "Paweł", "Gajec", new HashSet<>()));

        //Dav Ops

        controller.addTraining(new TrainingDTO("Dev Ops Podstawy","Kurs skierowany do osób, " +
                "chcących rozpocząć swoją przygodę z branżą IT, nie wymaga wcześniejszego " +
                "doświadczenia w programowaniu",
                new BigDecimal(3000),20,new HashSet<>(),new HashSet<>()),"DevOps");
        controller.addTraining(new TrainingDTO("Dev Ops Zaawansowany","Szkolenie dla osób które posiadają doświadczenie w branży IT, " +
                "chcących zgłębiać tajniki języka C # ",
                new BigDecimal(3000),25,new HashSet<>(),new HashSet<>()),"DevOps");
        controller.addTraining(new TrainingDTO("Azure Dev Ops","Spring to najpopularniejszy framework dla Java, dlatego jego znajomość jest " +
                "praktycznie niezbędna, jeśli myślisz o karierze Java Developera.",
                new BigDecimal(2500),15,new HashSet<>(),new HashSet<>()),"DevOps");
        controller.addTraining(new TrainingDTO("Jenkins Dev Ops","Hibernate framework stanowiący jedną z najpopularniejszych implementacji Java Persistence API (JPA)." +
                "Odpowiada za wygodna komunikację aplikacji z bazą danych.",
                new BigDecimal(2500),15,new HashSet<>(),new HashSet<>()),"DevOps");

        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Dev Ops Podstawy", "Paweł", "Gajec", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Dev Ops Zaawansowany", "Piotr", "Laskowski", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Dev Ops Zaawansowany", "Kamil", "Kotlarz", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Azure Dev Ops", "Kamil", "Kotlarz", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Jenkins Dev Ops", "Piotr", "Laskowski", new HashSet<>()));
        controller.assignTrainerToTraining(new TrainerToTrainingAssigmentDTO("Jenkins Dev Ops", "Paweł", "Gajec", new HashSet<>()));
    }
}
