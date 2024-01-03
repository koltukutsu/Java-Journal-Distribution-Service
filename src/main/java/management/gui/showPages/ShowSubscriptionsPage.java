package management.gui.showPages;

import management.business.Distributor;

import javax.swing.*;
import java.awt.*;

public class ShowSubscriptionsPage {
    public ShowSubscriptionsPage(Distributor distributor) {
        // Code to retrieve and display subscription information
        StringBuilder subscriptionsInfo = new StringBuilder();
        String[] subscriptions = distributor.getSubscriptionsInformation();
        for(String subscription : subscriptions) {
            subscriptionsInfo.append(subscription).append("\n");
        }
        JTextArea textArea = new JTextArea(subscriptionsInfo.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Subscriptions Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
