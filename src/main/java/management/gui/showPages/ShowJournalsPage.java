package management.gui.showPages;

import javax.swing.*;
import java.awt.*;

public class ShowJournalsPage {
    public ShowJournalsPage() {
        // Code to retrieve and display journal information
        String journalsInfo = "main.Journal A\nISSN: 12345\nFrequency: Monthly\nIssue Price: $10.00\n\n"
                + "main.Journal B\nISSN: 67890\nFrequency: Weekly\nIssue Price: $15.00\n";

        JTextArea textArea = new JTextArea(journalsInfo);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Journals Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
