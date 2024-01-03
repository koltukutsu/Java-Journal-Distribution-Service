package management.business;

import junit.framework.TestCase;

public class SubscriptionTest extends TestCase {
    private Subscription subscription1, subscription2;
    private Journal chosenJournal;
    private Subscriber individual, corporation;

    public void setUp() throws Exception {
//        super.setUp();
        DateInfo dates = new DateInfo(2, 1, 2023);
        chosenJournal = new Journal("Journal of Testing", "1234-5678", 12, 10.00);
        individual = new Individual("John Doe", "123 Main St. Anytown, NY 12345");
        corporation = new Corporation("IBM", "123 Main St. Anytown, NY 12345");
        subscription1 = new Subscription(dates, 1, chosenJournal, individual);
        subscription2 = new Subscription(dates, 2, chosenJournal, corporation);

        chosenJournal.addSubscription(subscription1);
        chosenJournal.addSubscription(subscription2);

        subscription1.acceptPayment(250.5);
        subscription2.acceptPayment(150);
    }

    public void testAcceptPayment() {
    }

    public void testCanSend() {
        boolean subscription1CanSendPositive = subscription1.canSend(4);
        boolean subscription2CanSendPositive = subscription2.canSend(2);
        boolean subscription1CanSendNegative = subscription1.canSend(1);
        boolean subscription2CanSendNegative = subscription2.canSend(1);

        System.out.println(subscription1CanSendPositive);
        System.out.println(subscription2CanSendPositive);
        System.out.println(subscription1CanSendNegative);
        System.out.println(subscription2CanSendNegative);

        assertTrue(subscription1CanSendPositive);
        assertTrue(subscription2CanSendPositive);
        assertFalse(subscription1CanSendNegative);
        assertFalse(subscription2CanSendNegative);
    }

    public void testGetSubscriber() {
        assertEquals(individual, subscription1.getSubscriber());
        assertEquals(corporation, subscription2.getSubscriber());
    }

    public void testIncreaseCoppies() {
        subscription1.increaseCoppies();
        subscription2.increaseCoppies();
        assertEquals(2, subscription1.getCopies());
        assertEquals(3, subscription2.getCopies());
    }
}