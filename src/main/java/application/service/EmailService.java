package application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.MessageFormat;

@Service
public class EmailService {

    private static Logger log = LoggerFactory.getLogger(OrderService.class);

    private JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendShippingEmail(String productId, String userId) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo("shipping@finra.com");
        simpleMailMessage.setFrom("orders@finra.com");
        simpleMailMessage.setSubject("Order to ship");
        String text = MessageFormat.format("Ship one unit of product: {0} to user: {1}", productId, userId);
        simpleMailMessage.setText(text);

        // comment the line below out if you want to run for demo purposes without an SMTP server (I used FakeSMTP)
        emailSender.send(simpleMailMessage);

        // for demo purposes, log what we would have sent
        log.info("sent email with text: {}", text);
    }

}
