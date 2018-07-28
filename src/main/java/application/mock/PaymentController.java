package application.mock;

import application.CreditCardPayment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private Logger log = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping("payment")
    public boolean helloGetter(@RequestBody CreditCardPayment payment) {

        log.info("Mock payment service received payment: {}", payment);

        return true;
    }

}
