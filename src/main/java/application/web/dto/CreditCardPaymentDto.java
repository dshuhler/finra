package application.web.dto;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardPaymentDto that = (CreditCardPaymentDto) o;
        return Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardNumber, amount);
    }
}
