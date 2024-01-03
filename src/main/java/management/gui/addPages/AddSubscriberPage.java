package management.gui.addPages;

import management.business.Distributor;
import management.gui.JournalManagementSystem;

import javax.swing.*;
import java.awt.*;

public class AddSubscriberPage {
    public AddSubscriberPage(Distributor distributor, JournalManagementSystem subscriberObserver) {
        JFrame addSubscriberFrame = new JFrame("Add Subscriber");
        addSubscriberFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);
        panel.add(new JLabel("Address:"));
        JTextField addressField = new JTextField();
        panel.add(addressField);

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
            // main.Individual subscriber
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
        } else if (choice == 1) {
            // Corporation subscriber
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
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            // Perform necessary actions with the input data (e.g., save to a data structure or database)
            // ...
            if(choice == 0) {

            } else {
            }
            JOptionPane.showMessageDialog(addSubscriberFrame, "Subscriber added successfully!");
            subscriberObserver.updateSubscribers();
            addSubscriberFrame.dispose(); // Close the Add main.Subscriber page
        });
        panel.add(saveButton);

        addSubscriberFrame.getContentPane().add(BorderLayout.CENTER, panel);
        addSubscriberFrame.setSize(400, 300);
        addSubscriberFrame.setVisible(true);
    }
}
