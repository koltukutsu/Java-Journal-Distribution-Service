package management.business;

import java.util.Vector;

public class Journal {
    private String name;
    private String issn;
    private int frequency;
    private double issuePrice;

    private Vector<Subscription> subscriptions;

    public Journal(String name, String issn, int frequency, double issuePrice) {
        this.name = name;
        this.issn = issn;
        this.frequency = frequency;
        this.issuePrice = issuePrice;
        this.subscriptions = new Vector<Subscription>();
    }

    public void addSubscription(Subscription subscription) {
        if(doesSubscriptionExist(subscription))
            System.out.println("Journal - addSubscription(): Subscription already exists");
        else {
            System.out.println("Journal - addSubscription(): Subscription added");
            subscriptions.add(subscription);
        }
    }
    public boolean doesSubscriptionExist(Subscription subscription) {
        return subscriptions.contains(subscription);
    }
    public String getIssn() {
        return issn;
    }

    public double getIssuePrice() {
        return issuePrice;
    }

    public Vector<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public String getJournalInformation() {
        return "Journal name: " + name + "\n" +
                "Journal ISSN: " + issn + "\n" +
                "Journal frequency: " + frequency + "\n" +
                "Journal issue price: " + issuePrice + "\n";
    }

    public String getName() {
        return name;
    }

    public Object getFrequency() {
        return frequency;
    }

    public Object getJournalName() {
        return name;
    }
}

