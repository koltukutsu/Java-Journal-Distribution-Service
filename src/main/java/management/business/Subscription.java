package management.business;

public class Subscription {
    private final DateInfo dates;
    private PaymentInfo payment;
    private int copies;
    private final Journal journal;
    private final Subscriber subscriber;

    public Subscription(DateInfo dates, int copies, Journal journal, Subscriber subscriber) {
        this.dates = dates;
        this.copies = copies;
        this.journal = journal;
        this.subscriber = subscriber;
    }

    public void acceptPayment(double amount) {
        if(payment == null) {
            payment = new PaymentInfo();
        } else {
            payment.increasePayment(amount);
        }

    }

    public boolean canSend(int issueMonth){
        if(payment == null) {
            return false;
        }
        double receivedPayment = payment.getReceivedPayment();
        double issuePrice = journal.getIssuePrice();
        // whether the subscriber is a corporation or an individual
        if(subscriber instanceof Coorporation) {
            double discountRatio = payment.getDiscountRatio();
            double amount = (issuePrice * discountRatio) * copies;
            if(receivedPayment < amount) {
                // TODO: do i need to decrease the amount of the payment
                return false;
            }
        } else if(subscriber instanceof Individual) {
            double amount = issuePrice * copies;
            if(receivedPayment < amount) {
                return false;
            }
        }
        return true;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void increaseCoppies() {
        this.copies++;
    }
}
