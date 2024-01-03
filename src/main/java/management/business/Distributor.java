package management.business;

import java.util.Hashtable;
import java.util.Vector;

public class Distributor {
    private Hashtable<String, Journal> journals;
    private Vector<Subscriber> subscribers;

    public boolean addJournal(Journal journal) {
        if(journals.contains(journal.getIssn()))
            return false;
        else
            journals.put(journal.getIssn(), journal);
        return true;
    }

    public Journal searchJournal(String issn) {
        if(journals.get(issn) != null) {
            return journals.get(issn);
        }
        return null;
    }
    public Subscriber searchSubscriber(String name) {
        for(Subscriber subscriber : subscribers) {
            if(subscriber.getName().equals(name))
                return subscriber;
        }
        return null;
    }
    public boolean addSubscriber(Subscriber subscriber) {
        if(subscribers.contains(subscriber))
            return false;
        else
            subscribers.add(subscriber);
        return true;
    }



    public boolean addSubscription(String issn, Subscription subscription) {

        if(this.searchJournal(issn) != null) {
            // null then there is no subscriber with that name
            if(this.searchSubscriber(subscription.getSubscriber().getName()) != null)
            {
                subscription.increaseCoppies();
                return true;
            } else {
                return false;
            }
        }
        else {

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

    }

    public void saveState(String fileName){
        // do state saving of the distributor object with multithreading

    }

    public void loadState(String fileName){
        // load state of the distributor object with multithreading

    }

    public void report() {

    }

}
