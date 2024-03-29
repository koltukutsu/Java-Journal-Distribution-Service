package management.gui;

import management.business.Distributor;
import management.gui.addPages.AddJournalPage;
import management.gui.addPages.AddSubscriberPage;
import management.gui.addPages.AddSubscriptionPage;
import management.gui.showPages.ShowJournalsPage;
import management.gui.showPages.ShowSubscribersPage;
import management.gui.showPages.ShowSubscriptionsPage;
import management.gui.takePages.TakePaymentPage;
import management.gui.takePages.TakeReportPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class Main {
    private JFrame mainFrame;
    private Distributor distributor;
    private JLabel journalsLabel;
    private JLabel subscribersLabel;
    private JLabel subscriptionsLabel;

    public Main() {
        this.distributor = new Distributor();

        mainFrame = new JFrame("Journal Management System");
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

        mainFrame.add(leftPanel);
        mainFrame.add(rightPanel);

        JPanel payAndReportPanel = new JPanel(new GridLayout(2, 1));
        JButton takeReportButton = new JButton("Generate Report");
        JButton takePaymentButton = new JButton("Make Payment");
        payAndReportPanel.add(takeReportButton);
        payAndReportPanel.add(takePaymentButton);

        JButton addJournalButton = new JButton("Add a Journal");
        JButton addSubscriberButton = new JButton("Add a Subscriber");
        JButton addSubscriptionButton = new JButton("Add a Subscription");

        // Add Action Listeners to buttons below text columns
        addJournalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Add main.Journal page
                new AddJournalPage(distributor, Main.this);
            }
        });

        addSubscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Add main.Subscriber page
                new AddSubscriberPage(distributor, Main.this);
            }
        });

        addSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Add main.Subscription page
                new AddSubscriptionPage(distributor, Main.this);
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
        takePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the Show Subscriptions page
                new TakePaymentPage(getDistributor(), Main.this);
            }
        });
        // Add buttons below text columns to the main frame
        // add a filler / blank space
        mainFrame.add(payAndReportPanel);
        mainFrame.add(addJournalButton);
        mainFrame.add(addSubscriberButton);
        mainFrame.add(addSubscriptionButton);

        mainFrame.setSize(600, 400);
        mainFrame.setMaximumSize(new Dimension(600, 400));
        mainFrame.setMinimumSize(new Dimension(600, 400));
        mainFrame.setVisible(true);


        final String stateFileName = distributor.getFilePathForState();
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

}
