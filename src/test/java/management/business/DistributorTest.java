package management.business;

import junit.framework.TestCase;

import java.io.File;

public class DistributorTest extends TestCase {
    private Distributor distributor;
    private Journal journal;
    public void setUp() throws Exception {
//        super.setUp();
        distributor = new Distributor();
        journal = new Journal("Semih Daily", "55500123", 12, 10);
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
    }

    public void testSearchJournal() {
    }

    public void testSearchSubscriber() {
    }

    public void testAddSubscriber() {
    }

    public void testAddSubscription() {
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
    }

    public void testGetSubscribersSize() {
    }

    public void testGetSubscriptionsSize() {
    }

    public void testGetJournalsDataArray() {
    }

    public void testGetSubscribersInformationArray() {
    }

    public void testGetSubscriptionsInformationArray() {
    }

    public void testGetJournalsNames() {
    }

    public void testGetIndividualsNames() {
    }

    public void testGetCorporationsNames() {
    }

    public void testGetJournal() {
    }

    public void testGetFilePathForState() {
    }
}