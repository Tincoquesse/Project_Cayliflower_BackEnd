package com.inqoo.project_cayliflower_backend.configuration;


import com.inqoo.project_cayliflower_backend.controller.CauliflowerRestController;
import com.inqoo.project_cayliflower_backend.model.CategoryDTO;
import com.inqoo.project_cayliflower_backend.model.OfferPreparationRequestDTO;
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
        controller.addCategory(new CategoryDTO("Sales", "Sales Category", Collections.emptyList()));
        controller.addCategory(new CategoryDTO("Marketing", "Marketing Category", Collections.emptyList()));
        controller.addCategory(new CategoryDTO("Other", "Other Category", Collections.emptyList()));



        offerService.sendVerificationEmail(new OfferPreparationRequestDTO(new HashSet<>(), "kamil.sound@gmail.com"));
        offerService.sendVerificationEmail(new OfferPreparationRequestDTO(new HashSet<>(), "gajec.pawel@gmail.com"));
    }


}
