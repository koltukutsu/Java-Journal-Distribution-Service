package management.gui;

import management.business.Distributor;
import management.gui.addPages.AddJournalPage;
import management.gui.addPages.AddSubscriberPage;
import management.gui.addPages.AddSubscriptionPage;
import management.gui.showPages.ShowJournalsPage;
import management.gui.showPages.ShowSubscribersPage;
import management.gui.showPages.ShowSubscriptionsPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JournalManagementSystem {
    private JFrame mainFrame;
    private Distributor distributor;

    public JournalManagementSystem() {
        mainFrame = new JFrame("main.Journal Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(2, 2));

        // Left Column
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        JLabel journalsLabel = new JLabel("Journals: ");
        JLabel subscribersLabel = new JLabel("Subscribers: ");
        leftPanel.add(journalsLabel);
        leftPanel.add(subscribersLabel);

        // Right Column
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));
        JButton showJournalsButton = new JButton("Show Journals");
        JButton showSubscribersButton = new JButton("Show Subscribers");
        JButton showSubscriptionsButton = new JButton("Show Subscriptions");
        rightPanel.add(showJournalsButton);
        rightPanel.add(showSubscribersButton);
        rightPanel.add(showSubscriptionsButton);

        // Add Action Listeners to buttons
        showJournalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Journals page
                JOptionPane.showMessageDialog(mainFrame, "Showing Journals...");
            }
        });

        showSubscribersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Subscribers page
                JOptionPane.showMessageDialog(mainFrame, "Showing Subscribers...");
            }
        });

        showSubscriptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Subscriptions page
                JOptionPane.showMessageDialog(mainFrame, "Showing Subscriptions...");
            }
        });

        // Add panels to the main frame
        mainFrame.add(leftPanel);
        mainFrame.add(rightPanel);

        // Buttons below text columns
        // Buttons below text columns
        JButton addJournalButton = new JButton("Add main.Journal");
        JButton addSubscriberButton = new JButton("Add main.Subscriber");
        JButton addSubscriptionButton = new JButton("Add main.Subscription");

        // Add Action Listeners to buttons below text columns
        addJournalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Add main.Journal page
                new AddJournalPage();
            }
        });

        addSubscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Add main.Subscriber page
                new AddSubscriberPage();
            }
        });

        addSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Add main.Subscription page
                new AddSubscriptionPage();
            }
        });

        // Add Action Listeners to buttons below text columns
        showJournalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Journals page
                new ShowJournalsPage();
            }
        });

        showSubscribersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Subscribers page
                new ShowSubscribersPage();
            }
        });

        showSubscriptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Subscriptions page
                new ShowSubscriptionsPage();
            }
        });

        // Add buttons below text columns to the main frame
        // add a filler / blank space
        mainFrame.add(new JLabel(""));
        mainFrame.add(addJournalButton);
        mainFrame.add(addSubscriberButton);
        mainFrame.add(addSubscriptionButton);

        mainFrame.setSize(600, 400);
        mainFrame.setMaximumSize(new Dimension(600, 400));
        mainFrame.setMinimumSize(new Dimension(600, 400));
        mainFrame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JournalManagementSystem();
            }
        });
    }
}
