package ui;


import model.Inventory;
import model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ItemTable extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_COLS = 3;

    private JTable table;
    private JScrollPane scrollPane;
    private String[] cols;
    private String[][] data;
    private DefaultTableModel model;
    private SwingInventoryApp sia;


    public ItemTable(SwingInventoryApp sia) {
        this.sia = sia;
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);

        //display();
        cols = new String[]{"Item", "Quantity", "Min. Stock Limit"};
        data = new String[sia.getMyInventory().getItemList().size() + 1][NUM_COLS];
        data = getData();
        table = new JTable(data, cols);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        //model = new DefaultTableModel(data, cols);
        //setUp();


    }

    private String[][] getData() {
        List<String> rows = new ArrayList<>();
        int row = 0;

        data[0][0] = "a";
        data[0][1] = "b";
        data[0][2] = "c";

        if (!sia.getMyInventory().getItemList().isEmpty()) {
            for (Item i : sia.getMyInventory().getItemList()) {
                data[row][0] = (i.getName());
                data[row][1] = String.valueOf(i.getQuantity());
                data[row][2] = String.valueOf(i.getMinimumStockLimit());
                row++;
            }
        }


        return data;
    }

    public void setUp() {
        //model.setColumnIdentifiers(cols);
        //table.setModel(model);
//        table.setBackground(Color.GREEN);
//        table.setGridColor(Color.BLACK);

        scrollPane.setBackground(Color.BLUE);
        //scrollPane.setVisible(true);
        scrollPane.setBounds(800, 0, WIDTH, HEIGHT);
        //scrollPane.add(table);

    }

//    public void display() {
//        sia.getLayeredPane().add(scrollPane);
//
//    }


}
