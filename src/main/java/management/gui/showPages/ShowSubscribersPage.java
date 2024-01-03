package management.gui.showPages;

import javax.swing.*;
import java.awt.*;

public class ShowSubscribersPage {
    public ShowSubscribersPage() {
        // Code to retrieve and display subscriber information
        String subscribersInfo = "main.Individual main.Subscriber\nName: John Doe\nAddress: 123 Main St\n"
                + "Credit Card Number: **** **** **** 1234\nExpire Date: 12/25\nCCV: 456\n\n"
                + "Corporation main.Subscriber\nName: XYZ Corp\nAddress: 456 Business St\n"
                + "Bank Code: 789\nBank Name: Business Bank\nIssue Date: 01/22/2023\nAccount Number: 987654321\n";

        JTextArea textArea = new JTextArea(subscribersInfo);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Subscribers Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
