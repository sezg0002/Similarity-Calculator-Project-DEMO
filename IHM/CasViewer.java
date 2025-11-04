import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class CasViewer extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public CasViewer() {
        super("Cas Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font customFont = new Font("Arial", Font.PLAIN, 12);
        UIManager.put("Label.font", customFont);
        UIManager.put("TextField.font", customFont);
        UIManager.put("Button.font", customFont);

        String[] columns = {"Cas normal ID", "Cas défaillant ID", "État", "Triplet"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));


        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        table.setRowHeight(28);


        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);

        JTextField keywordField = new JTextField();
        keywordField.setPreferredSize(new Dimension(200, keywordField.getPreferredSize().height));

        JButton filterButton = new JButton("Filtrer par mot-clé");


        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterByKeyword(keywordField.getText());
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Mot-clé: "));
        controlPanel.add(keywordField);
        controlPanel.add(filterButton);

        add(controlPanel, BorderLayout.NORTH);

        loadData("dataBase/casDefaillants.txt");

        setColumnWidths();

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setColumnWidths() {
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(200);
        for (int i = 3; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(8000); 
        }
    }

    private void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int casNormalId = -1;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Cas normal ID: ")) {
                    casNormalId = Integer.parseInt(line.substring("Cas normal ID: ".length()));
                } else if (line.startsWith("  Cas défaillant")) {
                    int casDefaillantId = -1;
                    Matcher matcher = Pattern.compile("Cas ID: (\\d+),").matcher(line);
                    if (matcher.find()) {
                        casDefaillantId = Integer.parseInt(matcher.group(1));
                    }

                    Object[] rowData = new Object[4];
                    rowData[0] = casNormalId;
                    rowData[1] = casDefaillantId;
                    rowData[2] = "Défaillant";

                    String triplet = line.split("État: Defaillant", 2)[1].trim();
                    rowData[3] = triplet;

                    model.addRow(rowData);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void filterByKeyword(String keyword) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) table.getRowSorter();
    
        if (keyword.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(keyword), 3));
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CasViewer());
    }
}
