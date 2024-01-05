package management.business;

import junit.framework.TestCase;

public class JournalTest extends TestCase {
private Journal journal;
private Subscriber subscriber;
private DateInfo dateInfo;
private Corporation corporation;
private Individual individual;

    public void setUp() throws Exception {
        super.setUp();
        journal = new Journal("Semih Daily", "55500123", 12, 10);
        dateInfo = new DateInfo(1, 12, 2023);
        corporation = new Corporation("Corporation 1", "Adress 1", 1, "Ziraat Bankasi", 1, 1, 1, 1);
        individual = new Individual("Individual 1", "Adress 1", "123456789", 1, 2023, 123);
    }
    public void testAddSubscription() {
        Subscription subscription1 = new Subscription(dateInfo, 1, journal, corporation);
        Subscription subscription2 = new Subscription(dateInfo, 1, journal, individual);

        journal.addSubscription(subscription1);
        journal.addSubscription(subscription2);
        assertEquals(2, journal.getSubscriptions().size());
    }

    public void testDoesSubscriptionExist() {
        Subscription subscription1 = new Subscription(dateInfo, 1, journal, corporation);
        Subscription subscription2 = new Subscription(dateInfo, 1, journal, individual);

        journal.addSubscription(subscription1);
        journal.addSubscription(subscription2);
        assertTrue(journal.doesSubscriptionExist(subscription1));
        assertTrue(journal.doesSubscriptionExist(subscription2));
    }

    public void testGetIssn() {
        assertEquals("55500123", journal.getIssn());
    }

    public void testGetIssuePrice() {
        assertEquals(10.0, journal.getIssuePrice());
    }

    public void testGetSubscriptions() {
        Subscription subscription1 = new Subscription(dateInfo, 1, journal, corporation);
        Subscription subscription2 = new Subscription(dateInfo, 1, journal, individual);

        journal.addSubscription(subscription1);
        journal.addSubscription(subscription2);
        assertEquals(2, journal.getSubscriptions().size());
    }

    public void testGetJournalInformation() {
        assertEquals("Journal name: Semih Daily\n" +
                "Journal ISSN: 55500123\n" +
                "Journal frequency: 12\n" +
                "Journal issue price: 10.0\n", journal.getJournalInformation());
    }

    public void testTestGetName() {
        assertEquals("Semih Daily", journal.getName());
    }

    public void testGetFrequency() {
        assertEquals(12, journal.getFrequency());
    }

    public void testGetJournalName() {
        assertEquals("Semih Daily", journal.getJournalName());
    }
}