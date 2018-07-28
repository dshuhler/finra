package service;

import application.service.EmailService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.mock;

public class EmailServiceTests {

    private JavaMailSender mockJavaMailSender = mock(JavaMailSender.class);

    @Test
    public void sendsCorrectlyPopulatedTemplateEmail() {

        EmailService emailService = new EmailService(mockJavaMailSender);

        emailService.sendShippingEmail("X-5", "d_mcnabb");
        Mockito.verify(mockJavaMailSender).send(buildTestSmm("X-5", "d_mcnabb"));

        emailService.sendShippingEmail("A-53", "k_kolb");
        Mockito.verify(mockJavaMailSender).send(buildTestSmm("A-53", "k_kolb"));

    }


    private SimpleMailMessage buildTestSmm(String productId, String userId) {
        SimpleMailMessage testSmm = new SimpleMailMessage();

        testSmm.setTo("shipping@finra.com");
        testSmm.setFrom("orders@finra.com");
        testSmm.setSubject("Order to ship");
        testSmm.setText("Ship one unit of product: " + productId + " to user: " + userId);
        return testSmm;
    }

}
