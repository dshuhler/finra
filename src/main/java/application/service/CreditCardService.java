package application.service;

import application.web.dto.CreditCardPaymentDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreditCardService {

    public boolean chargeCreditCard(CreditCardPaymentDto payment) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8080/payment", payment, Boolean.class);
    }

}