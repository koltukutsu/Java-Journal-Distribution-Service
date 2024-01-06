package management.gui.takePages;

import management.business.Distributor;

import javax.swing.*;
import java.awt.*;

public class TakeReportPage {
    public TakeReportPage(Distributor distributor) {
        JFrame frame = new JFrame("Generate Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for the main layout

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // Panel for input fields

        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField yearStartRangeField = new JTextField();
        JTextField yearEndRangeField = new JTextField();

        inputPanel.add(new JLabel("Expired Subscriptions - Month:"));
        inputPanel.add(monthField);
        inputPanel.add(new JLabel("Expired Subscriptions - Year:"));
        inputPanel.add(yearField);

        inputPanel.add(new JLabel("Collected Payments - Year Start Range:"));
        inputPanel.add(yearStartRangeField);

        inputPanel.add(new JLabel("Collected Payments - Year End Range:"));
        inputPanel.add(yearEndRangeField);
        frame.add(inputPanel, BorderLayout.CENTER);

        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(e -> {
            try {
                int queryMonth = Integer.parseInt(monthField.getText().trim());
                int queryYear = Integer.parseInt(yearField.getText().trim());
                int yearStartRange = Integer.parseInt(yearStartRangeField.getText().trim());
                int yearEndRange = Integer.parseInt(yearEndRangeField.getText().trim());

                // Now call the report function with the collected data
                distributor.report(queryMonth, queryYear, yearStartRange, yearEndRange);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for month and year.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel.add(generateButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
}
