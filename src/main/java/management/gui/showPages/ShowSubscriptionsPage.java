package management.gui.showPages;

import management.business.Distributor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShowSubscriptionsPage {
    public ShowSubscriptionsPage(Distributor distributor) {
        String[] columnNames = {"Data", "Copies", "Journal", "Subscriber", "Payment", "Discount", "Issue Price", "Total Price"};

        JScrollPane scrollPane = getjScrollPane(distributor, columnNames);

        JOptionPane.showMessageDialog(null, scrollPane, "Subscriptions Information", JOptionPane.INFORMATION_MESSAGE);

    }

    private static JScrollPane getjScrollPane(Distributor distributor, String[] columnNames) {
        Object[][] data = distributor.getSubscriptionsInformationArray(); // Assuming getJournalsData returns a 2D array of journal data

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable jTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setPreferredSize(new Dimension(900, 400));
        return scrollPane;
    }
}
