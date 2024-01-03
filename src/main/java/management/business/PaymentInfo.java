package management.business;

import java.io.Serializable;

public class PaymentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private final double discountRatio;
    private double receivedPayment;

    public PaymentInfo() {
        this.discountRatio = 0.15;
        this.receivedPayment = 0;
    }

    void increasePayment(double amount) {
        this.receivedPayment += amount;
    }
    
    public double getReceivedPayment() {
        return receivedPayment;
    }

    public double getDiscountRatio() {
        return discountRatio;
    }
}
