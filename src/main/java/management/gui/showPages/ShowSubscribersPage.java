package management.gui.showPages;

import management.business.Distributor;

import javax.swing.*;
import java.awt.*;

public class ShowSubscribersPage {
    public ShowSubscribersPage(Distributor distributor) {
        // Code to retrieve and display subscriber information
        StringBuilder subscribersInfo = new StringBuilder();
        String[] subscribers = distributor.getSubscribersInformation();

        for(String subscriber : subscribers) {
            subscribersInfo.append(subscriber).append("\n");
        }

        JTextArea textArea = new JTextArea(subscribersInfo.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Subscribers Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
