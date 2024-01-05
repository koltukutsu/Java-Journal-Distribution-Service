package management.gui.takePages;

import management.business.Distributor;

import javax.swing.*;
import java.awt.*;

public class TakeReportPage {
    public TakeReportPage(Distributor distributor) {
        JFrame frame = new JFrame("Generate Reports");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for the main layout

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // Panel for input fields

        // Create input fields for both reports
        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField yearRangeField = new JTextField();

        // Add components for expired subscriptions
        inputPanel.add(new JLabel("Expired Subscriptions - Month:"));
        inputPanel.add(monthField);
        inputPanel.add(new JLabel("Expired Subscriptions - Year:"));
        inputPanel.add(yearField);

        // Add components for collected payments
        inputPanel.add(new JLabel("Collected Payments - Year Range:"));
        inputPanel.add(yearRangeField);

        // Add inputPanel to the center of the main frame
        frame.add(inputPanel, BorderLayout.CENTER);

        // Create the generate button and span it across the bottom
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(e -> {
            // Here you can collect the input data and pass it to the report function
            // You may want to validate the inputs before proceeding
            try {
                int month = Integer.parseInt(monthField.getText().trim());
                int year = Integer.parseInt(yearField.getText().trim());
                int yearRange = Integer.parseInt(yearRangeField.getText().trim());

                // Now call the report function with the collected data
                distributor.report(month, year, yearRange);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for month and year.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Create a panel to hold the generate button and add it to the bottom
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel.add(generateButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Set the window size and make it visible
        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
}
