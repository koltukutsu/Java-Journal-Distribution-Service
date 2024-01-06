package management.business;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.*;
import java.util.*;

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

            for (Subscription sub : subscriptions) {
                if (sub.getSubscriber().getName().equals(subscriberName)) {
                    isContains = true;
                    break;
                }
            }
            if (isContains) {
                System.out.println("Distributor - addSubscription(): Subscription already exists.\n\tIncreasing copies with 1.");
                subscription.increaseCoppies();
            } else {
                System.out.println("Distributor - addSubscription(): Subscription added.");
                journal.addSubscription(subscription);
                // TODO: get the amount of coppies from the user
            }
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
        Journal journal = journals.get(issn);
        if (journal == null) {
            System.out.println("Journal with ISSN " + issn + " not found.");
            return;
        }

        System.out.println("Sending orders for Journal: " + journal.getJournalName() + " (ISSN: " + issn + ") for " + month + "/" + year);
        for (Subscription subscription : journal.getSubscriptions()) {
            // Check if the year matches the subscription's year
            if (subscription.getDates().getStartYear() <= year && subscription.getDates().getEndYear() >= year) {
                if (subscription.canSend(month)) {
                    System.out.println("Send to: " + subscription.getSubscriber().getName());
                } else {
                    System.out.println("Payment incomplete for: " + subscription.getSubscriber().getName());
                }
            }
        }
    }


    public void listIncompletePayments() {
        System.out.println("Incomplete Payments:");

        for (Journal journal : journals.values()) {
            final double journalPrice = journal.getIssuePrice();
            for (Subscription subscription : journal.getSubscriptions()) {
                // Check if the received payment is less than the required payment amount
                final double requiredPaymentAmount = ((1 - subscription.getPayment().getDiscountRatio()) * journalPrice) * subscription.getCopies();
                if (subscription.getPayment().getReceivedPayment() < requiredPaymentAmount) {
                    System.out.println("Journal: " + journal.getJournalName() +
                            " (ISSN: " + journal.getIssn() +
                            "), Subscriber: " + subscription.getSubscriber().getName() +
                            ", Received Payment: " + subscription.getPayment().getReceivedPayment());
                }
            }
        }
    }


    public void listSubscriptions(String subscriberName) {
        for (Journal jn : journals.values()) {
            for (Subscription sb : jn.getSubscriptions()) {
                if (sb.getSubscriber().getName().equals(subscriberName)) {
                    System.out.println(sb.getSubscriptionInformation());
                }
            }
        }
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
                    new FileOutputStream(fileName));
            writer.writeObject(this);
            writer.close();
            System.out.println("The information you have entered has "
                    + "been successfully saved in file " + fileName);
        } catch (IOException e) {
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
                    new FileInputStream(fileName));
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

    public Journal getJournal(String selectedJournal) {
        for (Journal journal : journals.values()) {
            if (journal.getName().equals(selectedJournal))
                return journal;
        }
        return null;
    }

    public String getFilePathForState() {
        return "./JournalManagementSystemState.dat";
    }

    public void report(int queryMonth, int queryYear, int queryYearStartRange, int queryYearEndRange) {
        new Thread(() -> {
            HashMap<Integer, Double> payments = new HashMap<Integer, Double>();
            Vector<String> subscriptions = new Vector<String>();
            TreeMap<Integer, Integer> sortedSubscriptions = new TreeMap<Integer, Integer>();
            for (Journal journal : journals.values()) {
                for (Subscription subscription : journal.getSubscriptions()) {
                    DateInfo subscriptionDate = subscription.getDates();
                    int startYear = subscriptionDate.getStartYear();
                    int startMonth = subscriptionDate.getStartMonth();
                    int endMonth = subscriptionDate.getEndMonth();
                    int endYear = subscriptionDate.getEndYear();
                    // for the 1.part
                    if (queryYear > endYear) {
                        if(sortedSubscriptions.containsKey(endYear)){
                            sortedSubscriptions.put(endYear, sortedSubscriptions.get(endYear) + 1);
                        } else{
                            sortedSubscriptions.put(endYear, 1);
                        }
                        subscriptions.add(subscription.getSubscriptionInformation());
                    } else {
                        if (queryYear == endYear) {
                            if (queryMonth > endMonth) {
                                if(sortedSubscriptions.containsKey(endYear)){
                                    sortedSubscriptions.put(endYear, sortedSubscriptions.get(endYear) + 1);
                                } else{
                                    sortedSubscriptions.put(endYear, 1);
                                }
                                subscriptions.add(subscription.getSubscriptionInformation());
                            }
                        }
                    }
                    // for the 2. part
                    double receivedPayment = subscription.getPayment().getReceivedPayment();
                    if(startYear >= queryYearStartRange && startYear <= queryYearEndRange) {
                        if (payments.containsKey(startYear)) {
                            payments.put(startYear, payments.get(startYear) + receivedPayment);
                        } else {
                            payments.put(startYear, receivedPayment);
                        }
                    }

                }
            }
            // 1. logic
            // give a barplot that shows the amount of subscriptions that are not renewed by using the size of the vector
            // there will be only one bar in the plot
            DefaultCategoryDataset datasetForEndedSubscriptions = new DefaultCategoryDataset();
//            int endedSubscriptions = subscriptions.size();
            for (Map.Entry<Integer, Integer> entry : sortedSubscriptions.entrySet()) {
                datasetForEndedSubscriptions.addValue(entry.getValue(), "Subscriptions", entry.getKey());
            }
//            datasetForEndedSubscriptions.addValue(endedSubscriptions, "Subscriptions", "Ended Subscriptions");
            JFreeChart barChartForEndedSubscriptions = ChartFactory.createBarChart(
                    "Ended Subscriptions",
                    "Subscriptions",
                    "Amount",
                    datasetForEndedSubscriptions,
                    PlotOrientation.VERTICAL,
                    true, true, false);
            File fileForEndedSubscriptions = new File("ended_subscriptions.png");
            try{
                ChartUtilities.saveChartAsPNG(fileForEndedSubscriptions, barChartForEndedSubscriptions, 560, 367);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            SwingUtilities.invokeLater(() -> {
                ChartPanel chartPanel = new ChartPanel(barChartForEndedSubscriptions);
                chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
                JFrame chartFrame = new JFrame();
                chartFrame.add(chartPanel);
                chartFrame.pack();
                chartFrame.setVisible(true);
            });

            // 2. logic: save hash as a chart which shows based on year
            TreeMap<Integer, Double> sortedPayments = new TreeMap<>(payments);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Map.Entry<Integer, Double> entry : sortedPayments.entrySet()) {
                dataset.addValue(entry.getValue(), "Payments", entry.getKey());
            }

            JFreeChart barChart = ChartFactory.createBarChart(
                    "Annual Payments",
                    "Year",
                    "Payment",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false);

            // Save the chart as a PNG file
            File file = new File("received_payments.png");
            try {
                ChartUtilities.saveChartAsPNG(file, barChart, 560, 367);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            SwingUtilities.invokeLater(() -> {
                ChartPanel chartPanel = new ChartPanel(barChart);
                chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
                JFrame chartFrame = new JFrame();
                chartFrame.add(chartPanel);
                chartFrame.pack();
                chartFrame.setVisible(true);
            });
            // Output the report - This can be displayed on the GUI or saved to a file
        }).start();

    }


}
