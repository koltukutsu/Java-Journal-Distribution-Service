package management.business;

import management.gui.observers.JournalObserver;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class Distributor {
    private List<JournalObserver> journalObservers = new ArrayList<>();

    private Hashtable<String, Journal> journals;
    private Vector<Subscriber> subscribers;

    public Distributor() {
        this.journals = new Hashtable<String, Journal>();
        this.subscribers = new Vector<Subscriber>();
    }
    public void addObserver(JournalObserver observer) {
        journalObservers.add(observer);
    }

    private void notifyJournalObservers() {
        for (JournalObserver observer : journalObservers) {
            observer.updateJournals();
        }
    }

    public boolean addJournal(Journal journal) {
        if (journals.containsKey(journal.getIssn())) {
            return false;
        }
        else {
            journals.put(journal.getIssn(), journal);
            return true;
        }
    }

    public Journal searchJournal(String issn) {
        if (journals.get(issn) != null) {
            return journals.get(issn);
        }
        return null;
    }

    public Subscriber searchSubscriber(String name) {
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getName().equals(name))
                return subscriber;
        }
        return null;
    }

    public boolean addSubscriber(Subscriber subscriber) {
        if (subscribers.contains(subscriber))
            return false;
        else
            subscribers.add(subscriber);
        return true;
    }


    public boolean addSubscription(String issn, Subscription subscription) {

        if (this.searchJournal(issn) != null) {
            // null then there is no subscriber with that name
            if (this.searchSubscriber(subscription.getSubscriber().getName()) != null) {
                subscription.increaseCoppies();
                return true;
            } else {
                addSubscriber(subscription.getSubscriber());
                // TODO: get the amount of coppies from the user
                subscription.increaseCoppies();
                return false;
            }
        } else {
            return false;
        }

    }

    public void listAllSendingOrders(int month, int year) {
        journals.forEach((issn, journal) -> {
            this.listSendingOrders(journal.getIssn(), month, year);
        });

    }

    public void listSendingOrders(String issn, int month, int year) {

    }

    public void listlncompletePayments() {

    }

    public void listSubscriptions(String subscriberName) {

//        Subscriber sub = subscribers.get(0);
//        // whether sub a corporation or an individual
//        if(sub instanceof Corporation) {
//            System.out.println("Corporation");
//        } else {
//            System.out.println("Individual");
//        }
    }

    public void saveState(String fileName) {
        // do state saving of the distributor object with multithreading

    }

    public void loadState(String fileName) {
        // load state of the distributor object with multithreading

    }

    public void report() {

    }

    public String hello() {
        return "hello";
    }

    public int getJournalsSize() {
        return journals.size();
    }

    public int getSubscribersSize() {
        return subscribers.size();
    }

    public int getSubscriptionsSize() {
        int subscriptionsAmount = 0;
        for (Journal journal : journals.values()) {
            for (Subscription subscription : journal.getSubscriptions()) {
                subscriptionsAmount += subscription.getCopies();
            }
        }
        return subscriptionsAmount;
    }

    public Object[][] getJournalsData() {
        if (journals.isEmpty()) {
            // Return a 2D array with a single row indicating no journals are added yet
            return new Object[][]{{"No journals are added yet!", "", "", ""}};
        } else {
            Object[][] journalsData = new Object[journals.size()][4]; // Assuming you have 4 columns

            int counter = 0;
            for (String issn : journals.keySet()) {
                Journal journal = journals.get(issn);
                journalsData[counter][0] = journal.getIssn();
                journalsData[counter][1] = journal.getFrequency();
                journalsData[counter][2] = journal.getJournalName();
                journalsData[counter][3] = journal.getIssuePrice();
                counter++;
            }
            return journalsData;
        }
    }


    public String[] getSubscribersInformation() {
        if (subscribers.isEmpty()) {
            String[] subscribersArray = new String[1];
            subscribersArray[0] = "No subscribers are added yet!";
            return subscribersArray;
        } else {
            String[] subscribersArray = new String[subscribers.size()];
            int counter = 0;
            for (Subscriber subscriber : subscribers) {
                if (subscriber instanceof Corporation)
                    subscribersArray[counter] = ((Corporation) subscriber).getCorporationInformation();
                else if (subscriber instanceof Individual)
                    subscribersArray[counter] = ((Individual) subscriber).getIndividualInformation();
                else
                    counter++;
            }
            return subscribersArray;
        }
    }

    public String[] getSubscriptionsInformation() {
        if (journals.isEmpty()) {
            String[] subscriptionsArray = new String[1];
            subscriptionsArray[0] = "No subscriptions are added yet!";
            return subscriptionsArray;
        } else {
            String[] subscriptionsArray = new String[getSubscriptionsSize()];
            int counter = 0;
            for (Journal journal : journals.values()) {
                for (Subscription subscription : journal.getSubscriptions()) {
                    subscriptionsArray[counter] = subscription.getSubscriptionInformation();
                    counter++;
                }
            }
            return subscriptionsArray;
        }
    }

}
