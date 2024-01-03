package management.gui.addPages;

import management.business.Distributor;
import management.gui.JournalManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddSubscriptionPage {
    public AddSubscriptionPage(Distributor distributor, JournalManagementSystem subscriptionObserver) {
        JFrame addSubscriptionFrame = new JFrame("Add main.Subscription");
        addSubscriptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        String[] journalOptions = {"main.Journal A", "main.Journal B", "main.Journal C"};
        String selectedJournal = (String) JOptionPane.showInputDialog(
                addSubscriptionFrame,
                "Choose main.Journal:",
                "main.Journal Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                journalOptions,
                journalOptions[0]);
        panel.add(new JLabel("Selected main.Journal:"));
        panel.add(new JLabel(selectedJournal));

        String[] subscriberOptions = {"main.Individual", "Corporation"};
        int subscriberChoice = JOptionPane.showOptionDialog(
                addSubscriptionFrame,
                "Choose main.Subscriber Type:",
                "main.Subscriber Type",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                subscriberOptions,
                subscriberOptions[0]);
        panel.add(new JLabel("main.Subscriber Type:"));
        panel.add(new JLabel(subscriberOptions[subscriberChoice]));

        panel.add(new JLabel("Number of Copies:"));
        JTextField copiesField = new JTextField();
        panel.add(copiesField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener((ActionEvent e) -> {
            // Perform necessary actions with the input data (e.g., save to a data structure or database)
            // ...

            JOptionPane.showMessageDialog(addSubscriptionFrame, "main.Subscription added successfully!");
            addSubscriptionFrame.dispose(); // Close the Add main.Subscription page
        });
        subscriptionObserver.updateSubscriptions();
        panel.add(saveButton);

        addSubscriptionFrame.getContentPane().add(BorderLayout.CENTER, panel);
        addSubscriptionFrame.setSize(300, 200);
        addSubscriptionFrame.setVisible(true);
    }
}
