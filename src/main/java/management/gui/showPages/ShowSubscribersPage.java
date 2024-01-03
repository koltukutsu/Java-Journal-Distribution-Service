package management.gui.showPages;

import management.business.Distributor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShowSubscribersPage {
    public ShowSubscribersPage(Distributor distributor) {
        String[] columnNames = {"Type", "Name", "Address"};

        Object[][] data = distributor.getSubscribersInformationArray();

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable jTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(null, scrollPane, "Subscribers Information", JOptionPane.INFORMATION_MESSAGE);

    }
}
