package application.web;

import application.service.EmailService;
import application.service.OrderService;
import application.web.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private OrderService orderService;
    private EmailService emailService;

    @Autowired
    public OrderController(OrderService orderService, EmailService emailService) {
        this.orderService = orderService;
        this.emailService = emailService;
    }

    @PostMapping("/order")
    public String exectureOrder(@RequestBody OrderDto orderDto) {

        String result = orderService.executeOrder(orderDto.getProductId(), orderDto.getCreditCardNumber());

        if (result.equals("Order successful")) {
            emailService.sendShippingEmail(orderDto.getProductId(), orderDto.getUserId());
        }

        return result;
    }

}