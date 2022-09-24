package com.inqoo.project_cayliflower_backend.service;

import com.inqoo.project_cayliflower_backend.model.OfferPreparationRequestDTO;
import com.inqoo.project_cayliflower_backend.repository.TrainingRepo;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;

@Service
@Transactional
public class OfferService {

    private final JavaMailSender javaMailSender;

    private final TrainingRepo trainingRepo;

    public OfferService(JavaMailSender javaMailSender, TrainingRepo trainingRepo) {
        this.javaMailSender = javaMailSender;
        this.trainingRepo = trainingRepo;
    }

    public void processOffer(OfferPreparationRequestDTO requestDTO) throws MessagingException, UnsupportedEncodingException {
        sendVerificationEmail(requestDTO);

    }
    public void sendVerificationEmail(OfferPreparationRequestDTO requestDTO)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = requestDTO.getEmail();
        String fromAddress = "kursy.kalafior@gmail.com";
        String senderName = "Kalafiory";
        String subject = "Oferta Szkoleń";
        String content = "Dear Customer,<br>"
                + "test<br>"
                + "Thank you,<br>"
                + "kalafiory.pl.";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        javaMailSender.send(message);

    }
}
