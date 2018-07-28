package application;

import application.CreditCardPayment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentController {


    @PostMapping("payment")
    public String helloGetter(@RequestBody CreditCardPayment payment) {

        System.out.println(payment);
        return "whatever";
    }

}