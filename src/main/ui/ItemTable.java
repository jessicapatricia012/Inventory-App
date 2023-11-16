package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public abstract class ItemTable extends JTable {
    private static final int NUM_COLS = 3;

    protected InventoryAppGUI inventoryApp;

    private JTable table;
    private JScrollPane scrollPane;
    private String[] cols;
    protected Object[][] data;

    private DefaultTableModel model;
    private TableRowSorter rowSorter;

    ItemTable(InventoryAppGUI inventoryApp) {
        this.inventoryApp = inventoryApp;

        cols = new String[]{"Item", "Quantity", "Min. Stock Limit"};
        data = new String[getNumRows()][NUM_COLS];
        data = getData();

        model = new DefaultTableModel(data, cols);
        table = new JTable(model);
        rowSorter = new TableRowSorter(model);
        table.setRowSorter(rowSorter);


        scrollPane = new JScrollPane(table);



    }

    public JTable getJTable() {
        return table;
    }

    public TableRowSorter getRowSorter() {
        return rowSorter;
    }

    public abstract int getNumRows();


    public abstract Object[][] getData();



//    public void refresh() {
//
//        data = new String[sia.getMyInventory().getItemList().size()][NUM_COLS];
//        data = getData();
//        model = new DefaultTableModel(data, cols);
//        table = new JTable(model);
//        scrollPane = new JScrollPane(table);
//        add(scrollPane);
//
//        setVisible(true);
//        setOpaque(true);
//
//    }

}


