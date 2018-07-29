package application.service;

import application.model.ProductInventory;
import application.model.ProductInventoryRepo;
import application.web.dto.CreditCardPaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private static Logger log = LoggerFactory.getLogger(OrderService.class);

    private ProductInventoryRepo productInventoryRepo;
    private CreditCardService creditCardService;

    @Autowired
    public OrderService(ProductInventoryRepo productInventoryRepo, CreditCardService creditCardService) {
        this.productInventoryRepo = productInventoryRepo;
        this.creditCardService = creditCardService;
    }

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

        boolean chargeSuccessful = creditCardService.chargeCreditCard(payment);

        log.info("Credit card charge returned with result: {}", chargeSuccessful);

        if (chargeSuccessful) {
            // I would expect to transactionally decrement the inventory table here but that seems out of scope :)
            return "Order successful";
        } else {
            return "Credit card charge failed";
        }
    }

}
