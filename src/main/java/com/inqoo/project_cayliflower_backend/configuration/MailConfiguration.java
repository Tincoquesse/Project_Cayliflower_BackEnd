package com.inqoo.project_cayliflower_backend.configuration;


import com.inqoo.project_cayliflower_backend.model.MailSenderForTests;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Bean
    @Profile("prod")
    public MailSender springMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("kursy.kalafior@gmail.com");
        mailSender.setPassword("mpsxgwvshmytlnfe");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    @Profile("!prod")
    public MailSender dummyMailSender() {

        return new MailSenderForTests();
    }
}
