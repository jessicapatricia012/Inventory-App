package ui;

import model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LowStockWindow extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 200;
    private static final int NUM_COLS = 3;

    private InventoryAppGUI sia;

    private JLabel restockLabel;
    private JTable table;
    private JScrollPane scrollPane;
    private String[] cols;
    private Object[][] data;
    private DefaultTableModel model;

    LowStockWindow(InventoryAppGUI sia) {
        this.sia = sia;
        restockLabel = new JLabel("Please restock these items:");


        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        add(restockLabel);

        cols = new String[]{"Item", "Quantity", "Min. Stock Limit"};
        data = new String[sia.getMyInventory().getLowStockItems().size()][NUM_COLS];
        data = getData();

        model = new DefaultTableModel(data, cols);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane);

        setVisible(true);

    }

    private Object[][] getData() {
        int row = 0;

//        data[0][0] = "a";
//        data[0][1] = "b";
//        data[0][2] = "c";

        for (Item i : sia.getMyInventory().getLowStockItems()) {
            data[row][0] = (i.getName());
            data[row][1] = String.valueOf(i.getQuantity());
            data[row][2] = String.valueOf(i.getMinimumStockLimit());
            row++;

        }
        return data;
    }

}
