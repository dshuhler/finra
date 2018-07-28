package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;

@Service
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendShippingEmail(String productId, String userId) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo("shipping@finra.com");
        helper.setFrom("orders@finra.com");
        helper.setSubject("Order to ship");
        String text = MessageFormat.format("Ship one unit of product: {0} to user: {1}", productId, userId);
        helper.setText(text);

        //this will fail unless FakeSMTP is running (or you change the spring boot SMTP config)
        emailSender.send(message);
    }

}
