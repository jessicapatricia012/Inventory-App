package ui;

import javax.swing.*;

public class LowStockWindow extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 200;

    private JLabel restockLabel;
    private JScrollPane scrollPane;

    private ItemTable lowStockItemsTable;

    LowStockWindow(InventoryAppGUI inventoryApp) {
        super("Low In Stock");
        restockLabel = new JLabel("Please restock these items:");
        lowStockItemsTable = new LowStockItemsTable(inventoryApp);
        scrollPane = new JScrollPane(lowStockItemsTable.getJTable());

        setUp();
    }

    public void setUp() {
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        add(restockLabel);
        add(scrollPane);

        setVisible(true);

    }


}
