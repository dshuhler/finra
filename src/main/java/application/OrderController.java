package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class OrderController {

    private Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    EmailService emailService;

    @PostMapping("/order")
    public String doOrder(@RequestBody OrderDto orderDto) {

        String result = orderService.executeOrder(orderDto.getProductId(), orderDto.getCreditCardNumber());

        if (result.equals("Order successful")) {
            try {
                emailService.sendShippingEmail(orderDto.getProductId(), orderDto.getUserId());
            } catch (MessagingException e) {
                e.printStackTrace();
                log.error("Failed sending email to shipping");
            }
        }

        return result;
    }

}