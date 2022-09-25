package com.inqoo.project_cayliflower_backend.service;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.ServerSetup;
import com.inqoo.project_cayliflower_backend.model.MailSenderForTests;
import com.inqoo.project_cayliflower_backend.model.OfferPreparationRequestDTO;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class OfferServiceTest {

    @Rule
    public GreenMailRule server = new GreenMailRule(new ServerSetup(25, "localhost", "smtp"));


    MailSenderForTests mailSenderForTests = new MailSenderForTests();

    @Test
    public void shouldSendEmail() throws Exception {
        // Given
        String to = "test@kalafiory.pl";
        String content = "test";
        String subject = "Cauliflower Courses";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        // When
        mailSenderForTests.send(simpleMailMessage);

        // Then
        MimeMessage[] receivedMessages = server.getReceivedMessages();
        assertThat(receivedMessages.length).isEqualTo(1);

        MimeMessage receivedMessage = receivedMessages[0];
        assertThat(receivedMessage.getAllRecipients()[0].toString()).isEqualTo(to);
        assertThat(receivedMessage.getContent().toString()).contains(content);

//        assertThat(receivedMessage.getFrom()[0].toString()).isEqualTo(from); //from
//        assertThat(receivedMessage.getSubject()).isEqualTo(title); //title
//        //Or
//        assertThat(content).isEqualTo(GreenMailUtil.getBody(server.getReceivedMessages()[0]));
    }

}