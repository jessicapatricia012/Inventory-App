package ui;

import model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JLabel mainMenuLabel;
    private JButton itemListButton;
    private JButton receiveItemsButton;
    private JButton shipItemsOutButton;
    private JButton lowStockWarningsButton;
    private SwingInventoryApp sia;

    public MainMenu(SwingInventoryApp sia) {
        this.sia = sia;

        mainMenuLabel = new JLabel("Main Menu");
        itemListButton = new JButton("Item List");
        receiveItemsButton = new JButton("Receive Items");
        shipItemsOutButton = new JButton("Ship Items Out");
        lowStockWarningsButton = new JButton("Low Stock Warnings");
        setUp();
    }

    public void setUp() {
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);

        setUpButtons();
        add(itemListButton);
        add(receiveItemsButton);
        add(shipItemsOutButton);
        add(lowStockWarningsButton);

        setUpLabel();
        add(mainMenuLabel);

    }

    public void setUpButtons() {
        itemListButton.setBounds(200, 100, 400, 50);
        receiveItemsButton.setBounds(200, 190, 400, 50);
        shipItemsOutButton.setBounds(200, 250, 400, 50);
        lowStockWarningsButton.setBounds(200, 400, 400, 50);

        itemListButton.setFocusable(false);
        receiveItemsButton.setFocusable(false);
        shipItemsOutButton.setFocusable(false);
        lowStockWarningsButton.setFocusable(false);

        itemListButton.addActionListener(this);
        receiveItemsButton.addActionListener(this);
        shipItemsOutButton.addActionListener(this);
        lowStockWarningsButton.addActionListener(this);
    }

    public void setUpLabel() {
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
        mainMenuLabel.setBounds(0, 10, 800, 50);
    }

    private void doSetMinimumStockLimit(String itemName) {
        int minStockLimit = Integer.parseInt(JOptionPane.showInputDialog("Enter item minimum stock limit: "));
        while (minStockLimit < 0) {
            JOptionPane.showMessageDialog(null,
                    "Minimum stock limit should be greater than or equal to 0.\n",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            minStockLimit = Integer.parseInt(JOptionPane.showInputDialog("Enter item minimum stock limit: "));
        }
        sia.getMyInventory().getItem(itemName).setMinimumStockLimit(minStockLimit);
        JOptionPane.showMessageDialog(null, itemName
                + "'s minimum stock limit has been set.");
        checkLowStock(itemName);
    }


    public void doReceiveItems() {
        String itemName = JOptionPane.showInputDialog("Enter the item being received:").toUpperCase();
        int itemQuantity = Integer.parseInt(JOptionPane.showInputDialog("Item quantity:"));


        if (itemQuantity <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity has to be greater than 0",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (sia.getMyInventory().addItem(itemName)) {
                JOptionPane.showMessageDialog(null,
                        "A new item " + itemName + " has been registered in the inventory.");

                doSetMinimumStockLimit(itemName);
            } else {
                JOptionPane.showMessageDialog(null, itemName + "'s stock has been updated.");
            }
            sia.getMyInventory().getItem(itemName).addQuantity(itemQuantity);
        }

    }

    // MODIFIES: this, myInventory.getItem(itemName)
    // EFFECTS: process item being shipped out of the inventory. If item is not in the inventory, prints out a statement
    //          that says so. Otherwise, prompts user to input the quantity being shipped out, and updates the item's
    //          stock. If the stock is insufficient, prints out a statement that says so.
    private void doShipItemsOut() {
        String itemName = JOptionPane.showInputDialog("Enter the item being shipped out:").toUpperCase();

        if (sia.getMyInventory().itemIsThere(itemName)) {
            int itemQuantity = Integer.parseInt(JOptionPane.showInputDialog("Item quantity:"));

            if (0 < itemQuantity && itemQuantity <= sia.getMyInventory().getItem(itemName).getQuantity()) {
                sia.getMyInventory().getItem(itemName).subtractQuantity(itemQuantity);
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
    }

    private void checkLowStock(String itemName) {
        if (sia.getMyInventory().getItem(itemName).isLowStock()) {
            JOptionPane.showMessageDialog(null, "Item " + itemName + " is low in stock.");
        }
    }


    private void doLowStockWarnings() {
        if (sia.getMyInventory().getLowStockItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No item is low in stock.");
        } else {
//            System.out.println("Please restock these items:");
//            for (Item i : sia.getMyInventory().getLowStockItems()) {
//                printItemInfo(i);
//            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemListButton) {
            sia.displayItemList();
        } else if (e.getSource() == receiveItemsButton) {
            doReceiveItems();
        } else if (e.getSource() == shipItemsOutButton) {
            doShipItemsOut();
        } else if (e.getSource() == lowStockWarningsButton) {
            doLowStockWarnings();
        }
    }


}
