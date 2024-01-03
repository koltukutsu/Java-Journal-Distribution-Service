package management.business;

import java.io.Serializable;

public class Subscription implements Serializable {
    private static final long serialVersionUID = 1L;

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

        payment = new PaymentInfo();
    }

    public void acceptPayment(double amount) {
//        if (payment == null) {
////            payment = new PaymentInfo();
//            payment.increasePayment(amount);
//        } else {
//            payment.increasePayment(amount);
//        }

        payment.increasePayment(amount);

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

    public void increaseCoppies() {
        this.copies++;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public Journal getJournal() {
        return journal;
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

    public String[] getSubscriptionInformationStringArray() {
        String[] stringArray = new String[8];
        stringArray[0] = "Date: " + dates.getStartMonth() + " - " + dates.getEndMonth() + " - " + dates.getStartYear();
        stringArray[1] = "Copies: " + copies;
        stringArray[2] = "Journal: " + journal.getName();
        stringArray[3] = "Subscriber: " + subscriber.getName();
        stringArray[4] = "Payment: " + payment.getReceivedPayment();
        stringArray[5] = "Discount: " + payment.getDiscountRatio();
        stringArray[6] = "Issue Price: " + journal.getIssuePrice();
        stringArray[7] = "Total Price: " + (journal.getIssuePrice() * copies);
//        stringArray[8] = "Can Send: " + this.canSend(1);
        return stringArray;
    }
}
