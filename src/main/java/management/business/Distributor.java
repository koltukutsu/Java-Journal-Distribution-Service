package management.business;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class Distributor implements Serializable {
    private static final long serialVersionUID = 1L;

    private Hashtable<String, Journal> journals;
    private Vector<Subscriber> subscribers;

    public Distributor() {
        this.journals = new Hashtable<String, Journal>();
        this.subscribers = new Vector<Subscriber>();
    }

    public boolean addJournal(Journal journal) {
        if (journals.containsKey(journal.getIssn())) {
            return false;
        } else {
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
        Journal journal = this.searchJournal(issn);
        if (journal != null) {
            // null then there is no subscriber with that name
            Vector<Subscription> subscriptions = journal.getSubscriptions();
            String subscriberName = subscription.getSubscriber().getName();
            boolean isContains = false;

            for(Subscription sub : subscriptions) {
                if (sub.getSubscriber().getName().equals(subscriberName)) {
                    isContains = true;
                    break;
                }
            }
            if (isContains) {
                System.out.println("Distributor - addSubscription(): Subscription already exists.\n\tIncreasing copies with 1.");
            } else {
                System.out.println("Distributor - addSubscription(): Subscription added.");
                journal.addSubscription(subscription);
                // TODO: get the amount of coppies from the user
            }
            subscription.increaseCoppies();
            return true;
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


    public int getJournalsSize() {
        return journals.size();
    }

    public int getSubscribersSize() {
        return subscribers.size();
    }

    public int getSubscriptionsSize() {
        int subscriptionsAmount = 0;
        for (Journal journal : journals.values()) {
            System.out.println(journal.getSubscriptions().size());
            for (Subscription subscription : journal.getSubscriptions()) {
                subscriptionsAmount += subscription.getCopies();
            }
        }
        return subscriptionsAmount;
    }

    public Object[][] getJournalsDataArray() {
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

    public Object[][] getSubscribersInformationArray() {
        if (subscribers.isEmpty()) {
            // Return a 2D array with a single row indicating no journals are added yet
            return new Object[][]{{"No subscribers are added yet!", "", "", ""}};
        } else {
            Object[][] subscribersData = new Object[subscribers.size()][3]; // Assuming you have 4 columns

            int counter = 0;
            for (Subscriber subscriber : subscribers) {
                if (subscriber instanceof Corporation) {

                    subscribersData[counter][0] = "Corporation";
                } else {
                    subscribersData[counter][0] = "Individual";

                }
                subscribersData[counter][1] = subscriber.getName();
                subscribersData[counter][2] = subscriber.getAddress();
                counter++;
            }
            return subscribersData;
        }
    }

    public Object[][] getSubscriptionsInformationArray() {
        if (journals.isEmpty()) {
            // Return a 2D array with a single row indicating no journals are added yet
            return new Object[][]{{"No subscriptions are added yet!", "", "", ""}};
        } else {
            int sizeOfJournalSubscriptions = 0;
            for (Journal journal : journals.values()) {
                sizeOfJournalSubscriptions += journal.getSubscriptions().size();
            }

            String[][] subscriptionsArray = new String[sizeOfJournalSubscriptions][8];

            int counter = 0;
            for (Journal journal : journals.values()) {
                for (Subscription subscription : journal.getSubscriptions()) {
                    subscriptionsArray[counter] = subscription.getSubscriptionInformationStringArray();
                    counter++;
                }
            }
            return subscriptionsArray;
        }
    }
    public String[] getJournalsNames() {
        String[] journalsNames = new String[journals.size()];
        int counter = 0;
        for (Journal journal : journals.values()) {
            journalsNames[counter] = journal.getName();
            counter++;
        }
        return journalsNames;
    }
    public String[] getIndividualsNames() {
        List<String> individualsNames = new ArrayList<String>();
        for (Subscriber subscriber : subscribers) {
            if (subscriber instanceof Individual) {
                individualsNames.add(subscriber.getName());
            }
        }
        return individualsNames.toArray(new String[0]);
    }

    public String[] getCorporationsNames() {
        List<String> corporationsNames = new ArrayList<String>();
        for (Subscriber subscriber : subscribers) {
            if (subscriber instanceof Corporation) {
                corporationsNames.add(subscriber.getName());
            }
        }
        return corporationsNames.toArray(new String[0]);
    }

    public synchronized void saveState(String fileName) {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(
                    new FileOutputStream( fileName ) );
            writer.writeObject( this );
            writer.close();
            System.out.println("The information you have entered has "
                    + "been successfully saved in file " + fileName);
        }
        catch( IOException e ) {
            System.out.println("An exception has occured during "
                    + "writing to file.");
            e.printStackTrace();
        }
//
//        return;
//        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
//            oos.writeObject(this);
//            System.out.println("State saved successfully.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public synchronized void loadState(String fileName) {
        try {
            ObjectInputStream reader = new ObjectInputStream(
                    new FileInputStream( fileName ) );
            Distributor loadedDistributor = (Distributor) reader.readObject();

            this.journals = loadedDistributor.journals;
            this.subscribers = loadedDistributor.subscribers;
            reader.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
//            Distributor loadedDistributor = (Distributor) ois.readObject();
//            // Copy the state to the current object
//            this.journals = loadedDistributor.journals;
//            this.subscribers = loadedDistributor.subscribers;
//            this.journalObservers = loadedDistributor.journalObservers;
//            System.out.println("State loaded successfully.");
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public void report(int month, int year, int yearRange) {
        // expired subscription -> month and year to be used

        // incomplete payments -> only yearRange to be used

    }

    public Journal getJournal(String selectedJournal) {
        for(Journal journal : journals.values()) {
            if(journal.getName().equals(selectedJournal))
                return journal;
        }
        return null;
    }
}
