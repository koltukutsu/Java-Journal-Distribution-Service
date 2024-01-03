package management.gui.addPages;

import javax.swing.*;
import java.awt.*;

public class AddJournalPage {
    public AddJournalPage() {
        JFrame addJournalFrame = new JFrame("Add main.Journal");
        addJournalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("ISSN:"));
        JTextField issnField = new JTextField();
        panel.add(issnField);
        panel.add(new JLabel("Frequency:"));
        JTextField frequencyField = new JTextField();
        panel.add(frequencyField);
        panel.add(new JLabel("main.Journal Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);
        panel.add(new JLabel("Issue Price:"));
        JTextField issuePriceField = new JTextField();
        panel.add(issuePriceField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            // Perform necessary actions with the input data (e.g., save to a data structure or database)
            // ...

            JOptionPane.showMessageDialog(addJournalFrame, "main.Journal added successfully!");
            addJournalFrame.dispose(); // Close the Add main.Journal page
        });
        panel.add(saveButton);

        addJournalFrame.getContentPane().add(BorderLayout.CENTER, panel);
        addJournalFrame.setSize(300, 200);
        addJournalFrame.setVisible(true);
    }
}
