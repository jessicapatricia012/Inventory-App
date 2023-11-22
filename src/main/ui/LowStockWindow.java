package ui;

import javax.swing.*;
import javax.swing.table.TableRowSorter;

// A new window that displays list of items that are low in stock
public class LowStockWindow extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 200;

    private JLabel restockLabel;
    private JTable table;
    private JScrollPane scrollPane;
    private CustomTableModel model;
    private TableRowSorter rowSorter;

    // EFFECTS : creates a new window and initialized fields
    LowStockWindow(InventoryAppGUI inventoryApp) {
        super("Low In Stock");

        setUpTable(inventoryApp);


        restockLabel = new JLabel("Please restock these items:");

        setUp();
    }

    // MODIFIES: this
    // EFFECTS : initializes and sets up table
    public void setUpTable(InventoryAppGUI inventoryApp) {
        model = new LowStockCustomTableModel(inventoryApp.getMyInventory());
        table = new JTable(model);
        rowSorter = new TableRowSorter(model);
        table.setRowSorter(rowSorter);
        scrollPane = new JScrollPane(table);
    }

    // MODIFIES: this
    // EFFECTS : sets up JFrame and adds components
    public void setUp() {
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        add(restockLabel);
        add(scrollPane);

        setVisible(true);
    }
}
