package management.business;

public class PaymentInfo {
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
