package ui;


import model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ItemTable extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_COLS = 3;

    private JTable table;
    private JScrollPane scrollPane;
    private String[] cols;
    private String[][] data;
    private DefaultTableModel model;
    private InventoryAppGUI sia;


    public ItemTable(InventoryAppGUI sia) {
        this.sia = sia;
        cols = new String[]{"Item", "Quantity", "Min. Stock Limit"};
        data = new String[sia.getMyInventory().getLowStockItems().size()][NUM_COLS];

//        table = new JTable(data, cols);
//        scrollPane = new JScrollPane(
//                table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        model = new DefaultTableModel(data, cols);
//        model.setColumnIdentifiers(cols);
//        table.setModel(model);

        model = new DefaultTableModel(data, cols);
        table = new JTable(model);

        setUp();
    }

    private void setUp() {
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);
        //setOpaque(true);

//        scrollPane.setVisible(true);
//        scrollPane.setBounds(800, 0, WIDTH, HEIGHT);

        //scrollPane.add(table);
        data = getData();
        add(new JScrollPane(table));


        //scrollPane.add(table);
        //sia.getLayeredPane().add(scrollPane);
    }

    private String[][] getData() {
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

//    public void setUp() {
//        //model.setColumnIdentifiers(cols);
//        //table.setModel(model);
////        table.setBackground(Color.GREEN);
////        table.setGridColor(Color.BLACK);
//
//        scrollPane.setBackground(Color.BLUE);
//        //scrollPane.setVisible(true);
//        scrollPane.setBounds(800, 0, WIDTH, HEIGHT);
//        //scrollPane.add(table);
//
//    }

//    public void display() {
//        sia.getLayeredPane().add(scrollPane);
//
//    }


}
