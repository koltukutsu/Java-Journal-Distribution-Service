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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class JournalManagementSystem {
    private JFrame mainFrame;
    private Distributor distributor;
    private JLabel journalsLabel;
    private JLabel subscribersLabel;
    private JLabel subscriptionsLabel;

    public JournalManagementSystem() {
        this.distributor = new Distributor();

        mainFrame = new JFrame("main.Journal Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(2, 2));

        // Left Column
        JPanel leftPanel = new JPanel(new GridLayout(3, 1));
        journalsLabel = new JLabel("Journals: 0" );
        subscribersLabel = new JLabel("Subscribers: 0");
        subscriptionsLabel = new JLabel("Subscriptions: 0");
        leftPanel.add(journalsLabel);
        leftPanel.add(subscribersLabel);
        leftPanel.add(subscriptionsLabel);
        // Right Column
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));
        JButton showJournalsButton = new JButton("Show Journals");
        JButton showSubscribersButton = new JButton("Show Subscribers");
        JButton showSubscriptionsButton = new JButton("Show Subscriptions");
        rightPanel.add(showJournalsButton);
        rightPanel.add(showSubscribersButton);
        rightPanel.add(showSubscriptionsButton);

        // Add panels to the main frame
        mainFrame.add(leftPanel);
        mainFrame.add(rightPanel);

        // Buttons below text columns
        // Buttons below text columns
        JButton takeReportButton = new JButton("Take Report");
        JButton addJournalButton = new JButton("Add a Journal");
        JButton addSubscriberButton = new JButton("Add a Subscriber");
        JButton addSubscriptionButton = new JButton("Add a Subscription");

        // Add Action Listeners to buttons below text columns
        addJournalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Add main.Journal page
                new AddJournalPage(distributor, JournalManagementSystem.this);
            }
        });

        addSubscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Add main.Subscriber page
                new AddSubscriberPage(distributor, JournalManagementSystem.this);
            }
        });

        addSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Add main.Subscription page
                new AddSubscriptionPage(distributor, JournalManagementSystem.this);
            }
        });

        // Add Action Listeners to buttons below text columns
        showJournalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Journals page
                new ShowJournalsPage(getDistributor());
            }
        });

        showSubscribersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Subscribers page
                new ShowSubscribersPage(getDistributor());
            }
        });

        showSubscriptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Subscriptions page
                new ShowSubscriptionsPage(getDistributor());
            }
        });

        takeReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Subscriptions page
                new TakeReportPage(getDistributor());
            }
        });

        // Add buttons below text columns to the main frame
        // add a filler / blank space
        mainFrame.add(takeReportButton);
        mainFrame.add(addJournalButton);
        mainFrame.add(addSubscriberButton);
        mainFrame.add(addSubscriptionButton);

        mainFrame.setSize(600, 400);
        mainFrame.setMaximumSize(new Dimension(600, 400));
        mainFrame.setMinimumSize(new Dimension(600, 400));
        mainFrame.setVisible(true);


        final String stateFileName = "./JournalManagementSystemState.dat";
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Save state when the window is closing
                System.out.println("Creating the state object in " + stateFileName);
                distributor.saveState(stateFileName);
                System.exit(0);
            }
        });

        // Load state when the application starts
        // and control whether the dat file is present or not?

        File file = new File(stateFileName);
        if(file.exists()) {
            System.out.println("Loading State from saved state, ");
            distributor.loadState(stateFileName);
            updateJournals();
            updateSubscribers();
            updateSubscriptions();
        }
        else {
            System.out.println("Fresh Start");
        }

    }
    public void updateJournals() {
        // Update the label displaying the number of journals
        journalsLabel.setText("Journals: " + distributor.getJournalsSize());
    }

    public void updateSubscribers() {
        subscribersLabel.setText("Subscribers: " + distributor.getSubscribersSize());
    }

    public void updateSubscriptions() {
        subscriptionsLabel.setText("Subscriptions: " + distributor.getSubscriptionsSize());
    }
    public Distributor getDistributor() {
        return distributor;
    }

    public static void main(String[] args) {
        // Create an instance of the Distributor class

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JournalManagementSystem();
            }
        });
    }

}
