package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.OfferPreparationRequestDTO;
import com.inqoo.project_cayliflower_backend.repository.TrainingRepo;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;

@Service
@Transactional
public class OfferService {

    private final MailSender javaMailSender;

    private final TrainingRepo trainingRepo;

    public OfferService(MailSender javaMailSender, TrainingRepo trainingRepo) {
        this.javaMailSender = javaMailSender;
        this.trainingRepo = trainingRepo;
    }

    public void processOffer(OfferPreparationRequestDTO requestDTO) throws MessagingException, UnsupportedEncodingException {
        sendEmail(requestDTO);

    }
    public void sendEmail(OfferPreparationRequestDTO requestDTO) {
        String toAddress = requestDTO.getEmail();
        String fromAddress = "kursy.kalafior@gmail.com";
        String subject = "Oferta Szkoleń";

        String content = "Dear Customer,\n"
                + "Thank you for Your order!\n"
                + "Kursy kalafior";

        SimpleMailMessage message  = new SimpleMailMessage();
        message.setTo(toAddress);
        message.setFrom(fromAddress);
        message.setSubject(subject);
        message.setText(content);

        javaMailSender.send(message);

    }
}
