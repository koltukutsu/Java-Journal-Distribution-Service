package management.business;

import junit.framework.TestCase;

import java.io.File;
import java.util.Arrays;

public class DistributorTest extends TestCase {
    private Distributor distributor;
    private Journal journal;
    private Subscriber subscriber;
    private Individual individual1;
    private Individual individual2;
    private Corporation corporation1;
    private Corporation corporation2;
    public void setUp() throws Exception {
//        super.setUp();
        distributor = new Distributor();
        subscriber = new Individual("Semih", "123 Main St. Anytown, NY 12345", "1234-5678-9012-3456", 12, 2023, 123);
        journal = new Journal("Semih Daily", "55500123", 12, 10);

        individual1 = new Individual("John Doe", "123 Main St. Anytown, NY 12345", "1234-5678-9012-3456", 12, 2023, 123);
        individual2 = new Individual("Jane Doe", "123 Main St. Anytown, NY 12345", "1234-5678-9012-3456", 12, 2023, 123);
        corporation1 = new Corporation("IBM", "123 Main St. Anytown, NY 12345", 123456, "Bank of America", 1, 1, 2020, 123456789);
        corporation2 = new Corporation("Apple", "123 Main St. Anytown, NY 12345", 123456, "Bank of America", 1, 1, 2020, 123456789);

    }


    public void testSaveState() {
        String fileName = "./currentState.dat";
        distributor.addJournal(journal);
        distributor.saveState(fileName);
        File file = new File(fileName);
        boolean isCreated = file.exists();
        assertTrue(isCreated);
    }

    public void testLoadState() {
        String fileName = "./currentState.dat";
        distributor.loadState(fileName);
        String journalIssn = journal.getIssn();
        Journal foundJournal = distributor.searchJournal(journalIssn);
        assertEquals(foundJournal.getJournalInformation(), journal.getJournalInformation());
        File file = new File(fileName);
        boolean isDeleted = file.delete();
        assertTrue(isDeleted);
    }

    public void testReport() {

    }

    public void testAddJournal() {
        distributor.addJournal(journal);
        String journalIssn = journal.getIssn();
        Journal foundJournal = distributor.searchJournal(journalIssn);
        assertEquals(foundJournal.getJournalInformation(), journal.getJournalInformation());
    }

    public void testSearchJournal() {
        distributor.addJournal(journal);
        String journalIssn = journal.getIssn();
        Journal foundJournal = distributor.searchJournal(journalIssn);
        assertEquals(foundJournal.getJournalInformation(), journal.getJournalInformation());
    }

    public void testSearchSubscriber() {
        distributor.addSubscriber(subscriber);
        String subscriberName = subscriber.getName();
        Subscriber foundSubscriber = distributor.searchSubscriber(subscriberName);
        assertEquals(foundSubscriber.getName(), subscriberName);

    }

    public void testAddSubscriber() {
        distributor.addSubscriber(subscriber);
        String subscriberName = subscriber.getName();
        Subscriber foundSubscriber = distributor.searchSubscriber(subscriberName);
        assertEquals(foundSubscriber.getName(), subscriberName);
    }

    public void testAddSubscription() {
        distributor.addJournal(journal);
        DateInfo dates = new DateInfo(2, 1, 2023);
        Subscription subscription = new Subscription(dates, 1, journal, subscriber);
        String issn = journal.getIssn();
        distributor.addSubscription(issn, subscription);
        Subscription foundSubscription = null;
        for(Subscription s : distributor.searchJournal(issn).getSubscriptions()) {
            if(s.getSubscriptionInformation().equals(subscription.getSubscriptionInformation())) {
                foundSubscription = s;
                break;
            }
        }
        assertNotNull(foundSubscription);
        assertEquals(foundSubscription.getSubscriptionInformation(), subscription.getSubscriptionInformation());
    }

    public void testListAllSendingOrders() {
    }

    public void testListSendingOrders() {
    }

    public void testListIncompletePayments() {
    }

    public void testListSubscriptions() {
    }

    public void testGetJournalsSize() {
        distributor.addJournal(journal);
        distributor.getJournalsSize();
        assertEquals(1, distributor.getJournalsSize());
    }

    public void testGetSubscribersSize() {
        distributor.addSubscriber(subscriber);
        distributor.getSubscribersSize();
        assertEquals(1, distributor.getSubscribersSize());
    }

    public void testGetSubscriptionsSize() {
distributor.addJournal(journal);
        DateInfo dates = new DateInfo(2, 1, 2023);
        Subscription subscription = new Subscription(dates, 1, journal, subscriber);
        String issn = journal.getIssn();
        distributor.addSubscription(issn, subscription);
        distributor.getSubscriptionsSize();
        assertEquals(1, distributor.getSubscriptionsSize());
    }

    public void testGetJournalsDataArray() {

    }

    public void testGetSubscribersInformationArray() {
    }

    public void testGetSubscriptionsInformationArray() {
    }

    public void testGetJournalsNames() {
        distributor.addJournal(journal);
        Journal journal2 = new Journal("Semih Daily", "12300", 12, 10);
        distributor.addJournal(journal2);
        String[] names = distributor.getJournalsNames();
        assertEquals(names[0], journal.getName());
        assertEquals(names[1], journal2.getName());
    }

    public void testGetIndividualsNames() {
        distributor.addSubscriber(individual1);
        distributor.addSubscriber(individual2);
        distributor.addSubscriber(corporation1);
        distributor.addSubscriber(corporation2);
        String[] names = distributor.getIndividualsNames();
        assertEquals(names[0], individual1.getName());
        assertEquals(names[1], individual2.getName());
    }

    public void testGetCorporationsNames() {
        distributor.addSubscriber(individual1);
        distributor.addSubscriber(individual2);
        distributor.addSubscriber(corporation1);
        distributor.addSubscriber(corporation2);
        String[] names = distributor.getCorporationsNames();
        assertEquals(names[0], corporation1.getName());
        assertEquals(names[1], corporation2.getName());

    }

    public void testGetJournal() {
        distributor.addJournal(journal);
        String journalName = journal.getName();
        Journal foundJournal = distributor.getJournal(journalName);
        assertEquals(foundJournal.getJournalInformation(), journal.getJournalInformation());
    }

    public void testGetFilePathForState() {
        distributor.getFilePathForState();
        assertEquals("./JournalManagementSystemState.dat", distributor.getFilePathForState());
    }
}