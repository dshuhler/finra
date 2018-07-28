package application.service;

import application.model.ProductInventory;
import application.model.ProductInventoryRepo;
import application.web.CreditCardPaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    ProductInventoryRepo productInventoryRepo;

    private Logger log = LoggerFactory.getLogger(OrderService.class);

    public String executeOrder(String productId, String creditCardNumber) {

        Optional<ProductInventory> maybe = productInventoryRepo.findByProductId(productId);

        log.info("found item {}", maybe);


        if (!maybe.isPresent()) {
            return "No product found with product ID = " + productId;
        }

        ProductInventory productInventory = maybe.get();

        if (productInventory.getQuantity() <= 0) {
            return "No product in stock";
        }

        CreditCardPaymentDto payment = new CreditCardPaymentDto(creditCardNumber, productInventory.getPrice());

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://localhost:8080/payment", payment, String.class);

        log.info("Credit card charge returned with result: {}", result);

        return "Order successful";
    }

}
