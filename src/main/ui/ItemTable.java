package ui;


import model.Inventory;
import model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ItemTable extends JScrollPane {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 600;

    private JTable table;
    private String[] cols;
    private String[][] data;
    private DefaultTableModel model;
    private Inventory myInventory;


    public ItemTable(Inventory myInventory) {
        cols = new String[]{"Item", "Quantity", "Min. Stock Limit"};
        data = getdata();
        table = new JTable(data, cols);
        this.myInventory = myInventory;


        setUp();
        display();
    }

    private String[][] getdata() {
        List<String> rows = new ArrayList<>();
        int row = 0;
        for (Item i : myInventory.getItemList()) {
            data[row][0] = (i.getName());
            data[row][1] = String.valueOf(i.getQuantity());
            data[row][2] = String.valueOf(i.getMinimumStockLimit());
            row++;
        }
        return data;
    }

    public void setUp() {
        model.setColumnIdentifiers(cols);
        table.setModel(model);
        table.setBackground(Color.GREEN);
        table.setGridColor(Color.BLACK);

        setBackground(Color.BLUE);
        setVisible(true);
        setBounds(800, 0, WIDTH, HEIGHT);
        add(table);

    }

    public void display() {
        if (myInventory.getItemList().isEmpty()) {
            System.out.println("Inventory is empty.\n");
        } else {

        }
    }
}
