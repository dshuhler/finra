package application.web;

import java.math.BigDecimal;

public class CreditCardPaymentDto {

    private String cardNumber;
    private BigDecimal amount;

    public CreditCardPaymentDto(String cardNumber, BigDecimal amount) {
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

    @Override
    public String toString() {
        return "CreditCardPaymentDto{" +
                "cardNumber='" + cardNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
