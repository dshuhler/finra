package application.web;

import application.service.EmailService;
import application.service.OrderService;
import application.web.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
            emailService.sendShippingEmail(orderDto.getProductId(), orderDto.getUserId());
        }

        return result;
    }

}