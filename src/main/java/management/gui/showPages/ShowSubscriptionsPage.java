package management.gui.showPages;

import javax.swing.*;
import java.awt.*;

public class ShowSubscriptionsPage {
    public ShowSubscriptionsPage() {
        // Code to retrieve and display subscription information
        String subscriptionsInfo = "main.Subscription 1\nDate: 01/01/2023\nPayment Info: Paid\nCopies: 5\n"
                + "main.Journal: main.Journal A\nmain.Subscriber: John Doe, 123 Main St\n\n"
                + "main.Subscription 2\nDate: 02/01/2023\nPayment Info: Pending\nCopies: 3\n"
                + "main.Journal: main.Journal B\nmain.Subscriber: XYZ Corp, 456 Business St\n";

        JTextArea textArea = new JTextArea(subscriptionsInfo);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Subscriptions Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
