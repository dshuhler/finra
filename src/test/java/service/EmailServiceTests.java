package service;

import application.service.EmailService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.MessagingException;

import java.text.MessageFormat;

import static org.mockito.Mockito.mock;


public class EmailServiceTests {

    private JavaMailSender mockJavaMailSender = mock(JavaMailSender.class);

    @Test
    public void sendsTemplateEmail() throws MessagingException {

        EmailService emailService = new EmailService(mockJavaMailSender);

        emailService.sendShippingEmail("X-5", "d_mcnabb");

        SimpleMailMessage testSsm = new SimpleMailMessage();

        testSsm.setTo("shipping@finra.com");
        testSsm.setFrom("orders@finra.com");
        testSsm.setSubject("Order to ship");
        testSsm.setText("Ship one unit of product: X-5 to user: d_mcnabb");

        Mockito.verify(mockJavaMailSender).send(testSsm);
    }



}
