package management.gui.addPages;

import management.business.Distributor;
import management.business.Journal;
import management.gui.JournalManagementSystem;

import javax.swing.*;
import java.awt.*;

public class AddJournalPage {
    public AddJournalPage(Distributor distributor, JournalManagementSystem journalObserver) {
        JFrame addJournalFrame = new JFrame("Add Journal");
        addJournalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("ISSN:"));
        JTextField issnField = new JTextField();
        panel.add(issnField);
        panel.add(new JLabel("Journal Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);
        panel.add(new JLabel("Frequency:"));
        JTextField frequencyField = new JTextField();
        panel.add(frequencyField);
        panel.add(new JLabel("Issue Price:"));
        JTextField issuePriceField = new JTextField();
        panel.add(issuePriceField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            if (isFieldEmpty(issnField) || isFieldEmpty(frequencyField) || isFieldEmpty(nameField) || isFieldEmpty(issuePriceField)) {
                JOptionPane.showMessageDialog(addJournalFrame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Journal journal = new Journal(nameField.getText(), issnField.getText(), Integer.parseInt(frequencyField.getText()), Integer.parseInt(issuePriceField.getText()));
                    boolean isAdded = distributor.addJournal(journal);
                    if (isAdded) {
                        JOptionPane.showMessageDialog(addJournalFrame, "Journal added successfully!");
                        journalObserver.updateJournals();
                        distributor.saveState(distributor.getFilePathForState());
                    } else {
                        JOptionPane.showMessageDialog(addJournalFrame, "Journal ISSN is already signed, cannot add Journal!");
                    }
                    addJournalFrame.dispose(); // Close the window
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addJournalFrame, "Please enter valid numeric values for Frequency and Issue Price.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(saveButton);

        addJournalFrame.getContentPane().add(BorderLayout.CENTER, panel);
        addJournalFrame.setSize(300, 200);
        addJournalFrame.setVisible(true);
    }

    private boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }
}
