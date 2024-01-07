package management.gui.takePages;

import management.business.*;
import management.gui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

public class TakePaymentPage {
    public TakePaymentPage(Distributor distributor, Main updateState) {
        JFrame takePaymentFrame = new JFrame("Make Payment");
        takePaymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        takePaymentFrame.setLayout(new BorderLayout()); // Use BorderLayout for the frame

        // Main panel with a GridLayout for 6 rows and 2 columns
        JPanel panel = new JPanel(new GridLayout(12, 2)); // Added gaps for clarity

        // Use a sub-panel to hold the non-editable labels for selected journal and subscriber
        JPanel selectedInfoPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // This panel will contain 4 labels

        // Journal selection
        String selectedJournal = selectJournal(takePaymentFrame, distributor);
        if (selectedJournal == null) return;
        panel.add(new JLabel("Selected Journal:"));
        panel.add(new JLabel(selectedJournal));

        // Subscriber type selection
//        String subscriberType = selectSubscriberType(takePaymentFrame);
//        if (subscriberType == null) return;
//        selectedInfoPanel.add(new JLabel("Subscriber Type:"));
//        selectedInfoPanel.add(new JLabel(subscriberType));

        // Add the non-editable information panel to the top
//        takePaymentFrame.add(selectedInfoPanel, BorderLayout.NORTH);
        // get the journal
        Journal journal = distributor.getJournal(selectedJournal);

        // subscriber
        String selectedSubscriber = selectSubscriber(takePaymentFrame, distributor, journal);
        if (selectedSubscriber == null) return;
        panel.add(new JLabel("Selected Subscriber:"));
        panel.add(new JLabel(selectedSubscriber));

        Subscription subscription = null;
        Individual individual = null;
        Corporation corporation = null;
        Subscriber subscriber = null;
        for (Subscription subscription1 : journal.getSubscriptions()) {
            if (subscription1.getSubscriber().getName().equals(selectedSubscriber)) {
                panel.add(new JLabel("Subscription Start Month:"));
                panel.add(new JLabel(subscription1.getDates().getStartMonth() + ""));
                panel.add(new JLabel("Subscription End Month:"));
                panel.add(new JLabel(subscription1.getDates().getEndMonth() + ""));
                panel.add(new JLabel("Subscription Year:"));
                panel.add(new JLabel(subscription1.getDates().getStartYear() + ""));
                panel.add(new JLabel("Number of Copies:"));
                panel.add(new JLabel(subscription1.getCopies() + ""));
                subscription = subscription1;
                subscriber = subscription1.getSubscriber();
                if(subscription1.getSubscriber() instanceof Individual){
                    individual = (Individual) subscription1.getSubscriber();
                } else{
                    corporation = (Corporation) subscription1.getSubscriber();
                }
                break;
            }
        }
        if(subscription == null) return;

//        if(individual != null) {
//            JLabel billingInfoField = new JLabel("Billing Information:");
//            panel.add(billingInfoField);
//            panel.add(new JLabel(individual.getBillingInformation()));
//        } else{
//            JLabel billingInfoField = new JLabel("Billing Information:");
//            panel.add(billingInfoField);
//            panel.add(new JLabel(corporation.getBillingInformation()));
//        }
        // Number of copies
        JLabel copiesField = new JLabel("Number of Copies");
        panel.add(copiesField);
        panel.add(new JLabel("" + subscription.getCopies()));

        JLabel requiredTotalPaymentField = new JLabel("Requiered Total Payment:");
        panel.add(requiredTotalPaymentField);
        double requiredTotalPayment = subscription.getCopies() * journal.getIssuePrice() * (1 - subscription.getPayment().getDiscountRatio());
        panel.add(new JLabel("" + (requiredTotalPayment)));

        // Payment receiving
        JLabel paymentField = new JLabel("Payment:");
        panel.add(paymentField);
        JTextField paymentInput = new JTextField();
        panel.add(paymentInput);

        // Save button
        JButton saveButton = new JButton("Make Payment");
        Subscription finalSubscription = subscription;
        saveButton.addActionListener((ActionEvent e) -> {

            int endMonth = finalSubscription.getDates().getEndMonth();
            endMonth = endMonth == 12? 12 : endMonth % 12;

            int endYear = finalSubscription.getDates().getEndMonth();
            int paymentTaken = Integer.parseInt(paymentInput.getText());
            if(requiredTotalPayment > paymentTaken){
                JOptionPane.showMessageDialog(takePaymentFrame, "Payment is not enough", "Input Error", JOptionPane.ERROR_MESSAGE);
                distributor.saveState(distributor.getFilePathForState());
                return;
            } else{
                // approve the user that it is taken wihtout any error
                JOptionPane.showMessageDialog(takePaymentFrame, "Payment is taken", "Payment Taken", JOptionPane.INFORMATION_MESSAGE);
                finalSubscription.acceptPayment(paymentTaken);
                distributor.saveState(distributor.getFilePathForState());
            }

            takePaymentFrame.dispose(); // Close the Add Subscription page
        });
        panel.add(saveButton);

        // Add the rest of the components to the center
        takePaymentFrame.add(panel, BorderLayout.CENTER);

        // Observer update

        takePaymentFrame.pack(); // Use pack() to fit the window size to the components
        takePaymentFrame.setLocationRelativeTo(null); // Center the window
        takePaymentFrame.setVisible(true);
    }

    private String selectJournal(JFrame frame, Distributor distributor) {
        String[] journalOptions = distributor.getJournalsNames();
        return (String) JOptionPane.showInputDialog(
                frame,
                "Choose a Journal:",
                "Journal Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                journalOptions,
                null);
    }

    private String selectSubscriberType(JFrame frame) {
        String[] subscriberOptions = {"Individual", "Corporation"};
        int choice = JOptionPane.showOptionDialog(
                frame,
                "Choose Subscriber Type:",
                "Subscriber Type",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                subscriberOptions,
                -1);
        return choice != -1 ? subscriberOptions[choice] : null;
    }

    private String selectSubscriber(JFrame frame, Distributor distributor, Journal journal) {
        ArrayList<String> subscribers = new ArrayList<String>();
        Vector<Subscription> subscriptions = journal.getSubscriptions();
        for (Subscription subscription : subscriptions) {
            subscribers.add(subscription.getSubscriber().getName());
        }
        if(subscribers.isEmpty()){
            JOptionPane.showMessageDialog(frame, "No subscribers for this journal", "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return (String) JOptionPane.showInputDialog(
                frame,
                "Choose a Subscriber :",
                "Subscriber Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                subscribers.toArray(),
                null);
    }
}
