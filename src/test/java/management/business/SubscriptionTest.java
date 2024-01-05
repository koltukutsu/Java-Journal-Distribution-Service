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
        individual = new Individual("John Doe", "123 Main St. Anytown, NY 12345", "1234-5678-9012-3456", 12, 2023, 123);
        corporation = new Corporation("IBM", "123 Main St. Anytown, NY 12345", 123456, "Bank of America", 1, 1, 2020, 123456789);
        subscription1 = new Subscription(dates, 1, chosenJournal, individual);
        subscription2 = new Subscription(dates, 2, chosenJournal, corporation);

        chosenJournal.addSubscription(subscription1);
        chosenJournal.addSubscription(subscription2);

        subscription1.acceptPayment(250.5);
        subscription2.acceptPayment(150);
    }

    public void testAcceptPayment() {
        subscription1.acceptPayment(250.5);
        subscription2.acceptPayment(150);
        assertEquals(501.0, subscription1.getPayment().getReceivedPayment());
        assertEquals(300.0, subscription2.getPayment().getReceivedPayment());
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

    public void testGetJournal() {
        assertEquals(chosenJournal, subscription1.getJournal());
        assertEquals(chosenJournal, subscription2.getJournal());
    }

    public void testGetCopies() {
        assertEquals(1, subscription1.getCopies());
        assertEquals(2, subscription2.getCopies());
    }

    public void testGetSubscriptionInformation() {
//        public String getSubscriptionInformation() {
//            return "Date: " + dates.getStartMonth() + " - " + dates.getEndMonth() + " - " + dates.getStartYear() + "\n" +
//                    "Copies: " + copies + "\n" +
//                    "Journal: " + journal.getName() + "\n" +
//                    "Subscriber: " + subscriber.getName() + "\n" +
//                    "Payment: " + payment.getReceivedPayment() + "\n" +
//                    "Discount: " + payment.getDiscountRatio() + "\n" +
//                    "Issue Price: " + journal.getIssuePrice() + "\n" +
//                    "Total Price: " + (journal.getIssuePrice() * copies) + "\n" +
//                    "Can Send: " + this.canSend(1) + "\n";
//        }

        assertEquals("Date: 2 - 1 - 2023\n" +
                "Copies: 1\n" +
                "Journal: Journal of Testing\n" +
                "Subscriber: John Doe\n" +
                "Payment: 250.5\n" +
                "Discount: 0.15\n" +
                "Issue Price: 10.0\n" +
                "Total Price: 10.0\n" +
                "Can Send: false\n", subscription1.getSubscriptionInformation());
        assertEquals("Date: 2 - 1 - 2023\n" +
                "Copies: 2\n" +
                "Journal: Journal of Testing\n" +
                "Subscriber: IBM\n" +
                "Payment: 150.0\n" +
                "Discount: 0.15\n" +
                "Issue Price: 10.0\n" +
                "Total Price: 20.0\n" +
                "Can Send: false\n", subscription2.getSubscriptionInformation());
    }

    public void testGetSubscriptionInformationStringArray() {
//
//        public String[] getSubscriptionInformationStringArray() {
//            String[] stringArray = new String[8];
//            stringArray[0] = "Date: " + dates.getStartMonth() + " - " + dates.getEndMonth() + " - " + dates.getStartYear();
//            stringArray[1] = "Copies: " + copies;
//            stringArray[2] = "Journal: " + journal.getName();
//            stringArray[3] = "Subscriber: " + subscriber.getName();
//            stringArray[4] = "Payment: " + payment.getReceivedPayment();
//            stringArray[5] = "Discount: " + payment.getDiscountRatio();
//            stringArray[6] = "Issue Price: " + journal.getIssuePrice();
//            stringArray[7] = "Total Price: " + (journal.getIssuePrice() * copies);
////        stringArray[8] = "Can Send: " + this.canSend(1);
//            return stringArray;
//        }

        String[] stringArray1 = new String[8];
        stringArray1[0] = "Date: 2 - 1 - 2023";
        stringArray1[1] = "Copies: 1";
        stringArray1[2] = "Journal: Journal of Testing";
        stringArray1[3] = "Subscriber: John Doe";
        stringArray1[4] = "Payment: 250.5";
        stringArray1[5] = "Discount: 0.15";
        stringArray1[6] = "Issue Price: 10.0";
        stringArray1[7] = "Total Price: 10.0";

        String[] stringArray2 = new String[8];
        stringArray2[0] = "Date: 2 - 1 - 2023";
        stringArray2[1] = "Copies: 2";
        stringArray2[2] = "Journal: Journal of Testing";
        stringArray2[3] = "Subscriber: IBM";
        stringArray2[4] = "Payment: 150.0";
        stringArray2[5] = "Discount: 0.15";
        stringArray2[6] = "Issue Price: 10.0";
        stringArray2[7] = "Total Price: 20.0";

        assertEquals(stringArray1[0], subscription1.getSubscriptionInformationStringArray()[0]);
        assertEquals(stringArray1[1], subscription1.getSubscriptionInformationStringArray()[1]);
        assertEquals(stringArray1[2], subscription1.getSubscriptionInformationStringArray()[2]);
        assertEquals(stringArray1[3], subscription1.getSubscriptionInformationStringArray()[3]);
        assertEquals(stringArray1[4], subscription1.getSubscriptionInformationStringArray()[4]);
        assertEquals(stringArray1[5], subscription1.getSubscriptionInformationStringArray()[5]);
        assertEquals(stringArray1[6], subscription1.getSubscriptionInformationStringArray()[6]);
        assertEquals(stringArray1[7], subscription1.getSubscriptionInformationStringArray()[7]);

        assertEquals(stringArray2[0], subscription2.getSubscriptionInformationStringArray()[0]);
        assertEquals(stringArray2[1], subscription2.getSubscriptionInformationStringArray()[1]);
        assertEquals(stringArray2[2], subscription2.getSubscriptionInformationStringArray()[2]);
        assertEquals(stringArray2[3], subscription2.getSubscriptionInformationStringArray()[3]);
        assertEquals(stringArray2[4], subscription2.getSubscriptionInformationStringArray()[4]);
        assertEquals(stringArray2[5], subscription2.getSubscriptionInformationStringArray()[5]);
        assertEquals(stringArray2[6], subscription2.getSubscriptionInformationStringArray()[6]);
        assertEquals(stringArray2[7], subscription2.getSubscriptionInformationStringArray()[7]);
    }

    public void testGetDates() {
        assertEquals(2, subscription1.getDates().getStartMonth());
        assertEquals(1, subscription1.getDates().getEndMonth());
        assertEquals(2023, subscription1.getDates().getStartYear());
        assertEquals(2, subscription2.getDates().getStartMonth());
        assertEquals(1, subscription2.getDates().getEndMonth());
        assertEquals(2023, subscription2.getDates().getStartYear());
    }

    public void testGetPayment() {
        assertEquals(250.5, subscription1.getPayment().getReceivedPayment());
        assertEquals(150.0, subscription2.getPayment().getReceivedPayment());
    }
}