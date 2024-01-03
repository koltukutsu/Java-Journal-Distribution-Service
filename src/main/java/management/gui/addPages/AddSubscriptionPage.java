package management.gui.addPages;

import management.business.DateInfo;
import management.business.Distributor;
import management.business.Subscription;
import management.gui.JournalManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddSubscriptionPage {
    public AddSubscriptionPage(Distributor distributor, JournalManagementSystem subscriptionObserver) {
        JFrame addSubscriptionFrame = new JFrame("Add a Subscription");
        addSubscriptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addSubscriptionFrame.setLayout(new BorderLayout()); // Use BorderLayout for the frame

        // Main panel with a GridLayout for 6 rows and 2 columns
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); // Added gaps for clarity

        // Use a sub-panel to hold the non-editable labels for selected journal and subscriber
        JPanel selectedInfoPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // This panel will contain 4 labels

        // Journal selection
        String selectedJournal = selectJournal(addSubscriptionFrame, distributor);
        if (selectedJournal == null) return;
        selectedInfoPanel.add(new JLabel("Selected Journal:"));
        selectedInfoPanel.add(new JLabel(selectedJournal));

        // Subscriber type selection
        String subscriberType = selectSubscriberType(addSubscriptionFrame);
        if (subscriberType == null) return;
        selectedInfoPanel.add(new JLabel("Subscriber Type:"));
        selectedInfoPanel.add(new JLabel(subscriberType));

        // Add the non-editable information panel to the top
        addSubscriptionFrame.add(selectedInfoPanel, BorderLayout.NORTH);

        // Individual or Corporation selection based on subscriberType
        String selectedSubscriber = selectSubscriber(addSubscriptionFrame, distributor, subscriberType);
        if (selectedSubscriber == null) return;
        panel.add(new JLabel("Selected Subscriber:"));
        panel.add(new JLabel(selectedSubscriber));

        // Subscription month and year
        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();
        panel.add(new JLabel("Subscription Month:"));
        panel.add(monthField);
        panel.add(new JLabel("Subscription Year:"));
        panel.add(yearField);

        // Number of copies
        JTextField copiesField = new JTextField();
        panel.add(new JLabel("Number of Copies:"));
        panel.add(copiesField);

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener((ActionEvent e) -> {
            // Perform necessary actions with the input data (e.g., save to a data structure or database)
            // ...
            int endMonth = Integer.parseInt(monthField.getText()) + 11;
            endMonth = endMonth == 12? 12 : endMonth % 12;

            DateInfo dates = new DateInfo(Integer.parseInt(monthField.getText()), endMonth, Integer.parseInt(yearField.getText()));

            Subscription subscription = new Subscription(
                    dates,
                    Integer.parseInt(copiesField.getText()),
                    distributor.getJournal(selectedJournal),
                    distributor.searchSubscriber(selectedSubscriber)
            );
            try {
                boolean result = distributor.addSubscription(subscription.getJournal().getIssn(), subscription);
                System.out.println("Result: " + result);
                JOptionPane.showMessageDialog(addSubscriptionFrame, "Subscription added successfully!");
                subscriptionObserver.updateSubscriptions();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(addSubscriptionFrame, "Error while adding subscription: " + exception.getMessage());
            }
            addSubscriptionFrame.dispose(); // Close the Add Subscription page
        });
        panel.add(saveButton);

        // Add the rest of the components to the center
        addSubscriptionFrame.add(panel, BorderLayout.CENTER);

        // Observer update

        addSubscriptionFrame.pack(); // Use pack() to fit the window size to the components
        addSubscriptionFrame.setLocationRelativeTo(null); // Center the window
        addSubscriptionFrame.setVisible(true);
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

    private String selectSubscriber(JFrame frame, Distributor distributor, String subscriberType) {
        String[] subscribers = subscriberType.equals("Individual") ? distributor.getIndividualsNames() : distributor.getCorporationsNames();
        return (String) JOptionPane.showInputDialog(
                frame,
                "Choose a Subscriber " + subscriberType + ":",
                "Subscriber Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                subscribers,
                null);
    }
}
