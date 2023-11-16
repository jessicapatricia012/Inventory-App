package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends JPanel implements ActionListener {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 600;

    private JLabel mainMenuLabel;
    private List<JButton> buttons;
    private JButton allItemsButton;
    private JButton receiveItemsButton;
    private JButton shipItemsOutButton;
    private JButton lowInStockButton;

    private InventoryAppGUI inventoryApp;

    public MainMenu(InventoryAppGUI inventoryApp) {
        this.inventoryApp = inventoryApp;

        mainMenuLabel = new JLabel("Main Menu");
        buttons = new ArrayList<>();
        allItemsButton = new JButton("All Items");
        receiveItemsButton = new JButton("Receive Items");
        shipItemsOutButton = new JButton("Ship Items Out");
        lowInStockButton = new JButton("Low In Stock");

        setUp();
    }

    public void setUp() {
        setSize(WIDTH, HEIGHT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setUpLabel();
        add(mainMenuLabel);

        setUpButtons();
        for (JButton b : buttons) {
            add(b);
        }
        setVisible(true);
    }

    public void setUpLabel() {
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
        mainMenuLabel.setBounds(0, 10, 800, 50);
    }

    public void setUpButtons() {
        buttons.add(allItemsButton);
        buttons.add(receiveItemsButton);
        buttons.add(shipItemsOutButton);
        buttons.add(lowInStockButton);

//        itemListButton.setSize(400, 50);
//        receiveItemsButton.setBounds(200, 190, 400, 50);
//        shipItemsOutButton.setBounds(200, 250, 400, 50);
//        lowInStockButton.setBounds(200, 400, 400, 50);

        for (JButton b : buttons) {
            b.setSize(new Dimension(400, 50));
            b.setFocusable(false);
            b.addActionListener(this);
        }
    }


    private void doSetMinimumStockLimit(String itemName) {
        int minStockLimit = Integer.parseInt(JOptionPane.showInputDialog("Enter item minimum stock limit: "));
        while (minStockLimit < 0) {
            JOptionPane.showMessageDialog(null,
                    "Minimum stock limit should be greater than or equal to 0.\n",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            minStockLimit = Integer.parseInt(JOptionPane.showInputDialog("Enter item minimum stock limit: "));
        }
        inventoryApp.getMyInventory().getItem(itemName).setMinimumStockLimit(minStockLimit);
        JOptionPane.showMessageDialog(null, itemName
                + "'s minimum stock limit has been set.");
    }


    public void doReceiveItems() {
        String itemName = JOptionPane.showInputDialog("Enter the item being received:").toUpperCase();
        int itemQuantity = Integer.parseInt(JOptionPane.showInputDialog("Item quantity:"));


        if (itemQuantity <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity has to be greater than 0",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (inventoryApp.getMyInventory().addItem(itemName)) {
                JOptionPane.showMessageDialog(null,
                        "A new item " + itemName + " has been registered in the inventory.");

                doSetMinimumStockLimit(itemName);
            } else {
                JOptionPane.showMessageDialog(null, itemName + "'s stock has been updated.");
            }
            inventoryApp.getMyInventory().getItem(itemName).addQuantity(itemQuantity);
            checkLowStock(itemName);
        }
        inventoryApp.refreshItemList();
    }

    // MODIFIES: this, myInventory.getItem(itemName)
    // EFFECTS: process item being shipped out of the inventory. If item is not in the inventory, prints out a statement
    //          that says so. Otherwise, prompts user to input the quantity being shipped out, and updates the item's
    //          stock. If the stock is insufficient, prints out a statement that says so.
    private void doShipItemsOut() {
        String itemName = JOptionPane.showInputDialog("Enter the item being shipped out:").toUpperCase();

        if (inventoryApp.getMyInventory().itemIsThere(itemName)) {
            int itemQuantity = Integer.parseInt(JOptionPane.showInputDialog("Item quantity:"));

            if (0 < itemQuantity && itemQuantity <= inventoryApp.getMyInventory().getItem(itemName).getQuantity()) {
                inventoryApp.getMyInventory().getItem(itemName).subtractQuantity(itemQuantity);
                JOptionPane.showMessageDialog(null, itemName + "'s stock has been updated.");
                checkLowStock(itemName);
            } else if (itemQuantity <= 0) {
                JOptionPane.showMessageDialog(null, "Quantity has to be greater than 0");
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient stock.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Item " + itemName + " is not found.");
        }
        inventoryApp.refreshItemList();
    }

    private void checkLowStock(String itemName) {
        if (inventoryApp.getMyInventory().getItem(itemName).isLowStock()) {
            JOptionPane.showMessageDialog(null, "Item " + itemName + " is low in stock.");
        }
    }

    private void doLowStockWarnings() {
        if (inventoryApp.getMyInventory().getLowStockItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No item is low in stock.");
        } else {
            inventoryApp.displayLowStockWarnings();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == allItemsButton) {
            inventoryApp.displayItemList();
        } else if (e.getSource() == receiveItemsButton) {
            doReceiveItems();
        } else if (e.getSource() == shipItemsOutButton) {
            doShipItemsOut();
        } else if (e.getSource() == lowInStockButton) {
            doLowStockWarnings();
        }
    }
}
