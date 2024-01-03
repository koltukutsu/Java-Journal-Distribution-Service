package management.gui.addPages;

import management.business.Corporation;
import management.business.Distributor;
import management.business.Individual;
import management.gui.JournalManagementSystem;

import javax.swing.*;
import java.awt.*;

public class AddSubscriberPage {
    public AddSubscriberPage(Distributor distributor, JournalManagementSystem subscriberObserver) {
        JFrame addSubscriberFrame = new JFrame("Add Subscriber");
        addSubscriberFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addSubscriberFrame.setLayout(new BorderLayout()); // Use BorderLayout for the frame

        // Shared fields panel
        JPanel sharedFieldsPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Panel for shared input fields

        sharedFieldsPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        sharedFieldsPanel.add(nameField);

        sharedFieldsPanel.add(new JLabel("Address:"));
        JTextField addressField = new JTextField();
        sharedFieldsPanel.add(addressField);

        // Add shared fields panel to the frame
        addSubscriberFrame.add(sharedFieldsPanel, BorderLayout.NORTH);

        // Create the panel for subscriber-specific fields
        JPanel subscriberFieldsPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // Use 0 for dynamic row allocation

        String[] options = {"Individual", "Corporation"};
        int choice = JOptionPane.showOptionDialog(
                addSubscriberFrame,
                "Choose Subscriber Type:",
                "Subscriber Type",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            // Individual subscriber fields
            addIndividualFields(subscriberFieldsPanel, distributor, nameField, addressField, subscriberObserver, addSubscriberFrame);
        } else if (choice == 1) {
            // Corporation subscriber fields
            addCorporationFields(subscriberFieldsPanel, distributor, nameField, addressField, subscriberObserver, addSubscriberFrame);
        } else {
            // Cancel was chosen
            addSubscriberFrame.dispose(); // Close the Add Subscriber page
            return;
        }

        // Add the subscriber-specific fields panel to the frame
        addSubscriberFrame.add(subscriberFieldsPanel, BorderLayout.CENTER);

        // Observer update
        subscriberObserver.updateSubscribers();
        distributor.saveState(distributor.getFilePathForState());

        addSubscriberFrame.pack(); // Fit the window to the components
        addSubscriberFrame.setLocationRelativeTo(null); // Center the window
        addSubscriberFrame.setVisible(true);
    }

    private void addIndividualFields(JPanel panel, Distributor distributor, JTextField nameField, JTextField addressField, JournalManagementSystem subscriberObserver, JFrame frame) {
        panel.add(new JLabel("Credit Card Number:"));
        JTextField creditCardNumberField = new JTextField();
        panel.add(creditCardNumberField);

        panel.add(new JLabel("Expire Month:"));
        JTextField expireMonthField = new JTextField();
        panel.add(expireMonthField);

        panel.add(new JLabel("Expire Year:"));
        JTextField expireYearField = new JTextField();
        panel.add(expireYearField);

        panel.add(new JLabel("CCV:"));
        JTextField ccvField = new JTextField();
        panel.add(ccvField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Individual individual = new Individual(
                    nameField.getText(),
                    addressField.getText(),
                    creditCardNumberField.getText(),
                    Integer.parseInt(expireMonthField.getText()),
                    Integer.parseInt(expireYearField.getText()),
                    Integer.parseInt(ccvField.getText())
            );
            distributor.addSubscriber(individual);
            JOptionPane.showMessageDialog(frame, "Individual subscriber added successfully!");
            subscriberObserver.updateSubscribers();
            frame.dispose(); // Close the Add Subscriber page
        });
        panel.add(saveButton);
    }

    private void addCorporationFields(JPanel panel, Distributor distributor, JTextField nameField, JTextField addressField, JournalManagementSystem subscriberObserver, JFrame frame) {
        panel.add(new JLabel("Bank Code:"));
        JTextField bankCodeField = new JTextField();
        panel.add(bankCodeField);

        panel.add(new JLabel("Bank Name:"));
        JTextField bankNameField = new JTextField();
        panel.add(bankNameField);

        panel.add(new JLabel("Issue Day:"));
        JTextField issueDayField = new JTextField();
        panel.add(issueDayField);

        panel.add(new JLabel("Issue Month:"));
        JTextField issueMonthField = new JTextField();
        panel.add(issueMonthField);

        panel.add(new JLabel("Issue Year:"));
        JTextField issueYearField = new JTextField();
        panel.add(issueYearField);

        panel.add(new JLabel("Account Number:"));
        JTextField accountNumberField = new JTextField();
        panel.add(accountNumberField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Corporation corporation = new Corporation(
                    nameField.getText(),
                    addressField.getText(),
                    Integer.parseInt(bankCodeField.getText()),
                    bankNameField.getText(),
                    Integer.parseInt(issueDayField.getText()),
                    Integer.parseInt(issueMonthField.getText()),
                    Integer.parseInt(issueYearField.getText()),
                    Integer.parseInt(accountNumberField.getText())
            );
            distributor.addSubscriber(corporation);
            JOptionPane.showMessageDialog(frame, "Corporation subscriber added successfully!");
            subscriberObserver.updateSubscribers();
            frame.dispose(); // Close the Add Subscriber page
        });
        panel.add(saveButton);
    }
}
