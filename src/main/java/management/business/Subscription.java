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
        if (payment == null) {
            payment = new PaymentInfo();
            payment.increasePayment(amount);
        } else {
            payment.increasePayment(amount);
        }

    }

    public boolean canSend(int issueMonth) {
        if (payment == null) {
            return false;
        } else {
            double receivedPayment = payment.getReceivedPayment();
            double issuePrice = journal.getIssuePrice();
            // whether the subscriber is a corporation or an individual
            if (subscriber instanceof Corporation) {
                double discountRatio = payment.getDiscountRatio();
                double amount = (issuePrice * discountRatio) * copies;
                System.out.println("amount to pay: " + amount);
                System.out.println("receivedPayment: " + receivedPayment);
                if (receivedPayment < amount) {
                    // TODO: do i need to decrease the amount of the payment
                    return false;
                } else {
                    return issueMonth >= dates.getStartMonth();
                }
            // (subscriber instanceof Individual)
            } else {
                double amount = issuePrice * copies;
                System.out.println("amount to pay: " + amount);
                System.out.println("receivedPayment: " + receivedPayment);
                if (receivedPayment < amount) {
                    return false;
                } else {
                    return issueMonth >= dates.getStartMonth();
                }
            }
        }
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void increaseCoppies() {
        this.copies++;
    }

    public int getCopies() {
        return copies;
    }

    public String getSubscriptionInformation() {
        return "Date: " + dates.getStartMonth() + " - " + dates.getEndMonth() + " - " + dates.getStartYear() + "\n" +
                "Copies: " + copies + "\n" +
                "Journal: " + journal.getName() + "\n" +
                "Subscriber: " + subscriber.getName() + "\n" +
                "Payment: " + payment.getReceivedPayment() + "\n" +
                "Discount: " + payment.getDiscountRatio() + "\n" +
                "Issue Price: " + journal.getIssuePrice() + "\n" +
                "Total Price: " + (journal.getIssuePrice() * copies) + "\n" +
                "Can Send: " + this.canSend(1) + "\n";
    }

}
