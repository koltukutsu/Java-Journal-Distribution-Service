package management.gui.showPages;

import management.business.Distributor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShowJournalsPage {
    public ShowJournalsPage(Distributor distributor) {
        String[] columnNames = {"ISSN", "Frequency", "Journal Name", "Issue Price"};

        Object[][] data = distributor.getJournalsDataArray(); // Assuming getJournalsData returns a 2D array of journal data

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable jTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(null, scrollPane, "Journals Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
