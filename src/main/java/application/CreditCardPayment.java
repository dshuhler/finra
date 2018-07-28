package application;

import java.math.BigDecimal;
import java.util.Currency;

public class CreditCardPayment {

    private String cardNumber;
    private BigDecimal amount;

    public CreditCardPayment(String cardNumber, BigDecimal amount) {
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
