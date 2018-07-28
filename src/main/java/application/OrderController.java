package application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
public class OrderController {


    @GetMapping("/hello")
    public String getGreeting() {


        CreditCardPayment payment = new CreditCardPayment("1234", BigDecimal.valueOf(123));

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://localhost:8080/payment", payment, String.class);


        System.out.println(result);


        return "hello world";
    }





}
