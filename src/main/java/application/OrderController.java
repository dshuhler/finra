package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public String doOrder(@RequestBody OrderDto orderDto) {

        String result = orderService.executeOrder(orderDto.getProductId(), orderDto.getCreditCardNumber());

        if (result.equals("Order successful")) {
            //send email here
        }

        return result;
    }

}