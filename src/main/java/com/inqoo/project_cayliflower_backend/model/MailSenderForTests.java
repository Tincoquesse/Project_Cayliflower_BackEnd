package com.inqoo.project_cayliflower_backend.model;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


public class MailSenderForTests implements MailSender {

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {

    }
}