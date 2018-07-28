package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class OrderController {

    Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    ProductInventoryRepo productInventoryRepo;


    @PostMapping("/order")
    public String createOrder(@RequestBody OrderDto orderDto) {

        String productId = orderDto.getProductId();


        Optional<ProductInventory> maybe = productInventoryRepo.findByProductId(productId);

        log.info("found item {}", maybe);


        if (!maybe.isPresent()) {
            return "No product found with product ID = " + productId;
        }

        ProductInventory productInventory = maybe.get();

        if (productInventory.getQuantity() <= 0) {
            return "No product in stock";
        }

        CreditCardPayment payment = new CreditCardPayment(orderDto.getCreditCardNumber(), productInventory.getPrice());

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://localhost:8080/payment", payment, String.class);

        log.info("Credit card charge returned with result: {}", result);

        return "Order successful";
    }





}
