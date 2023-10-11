package ui;

import model.Inventory;
import model.Item;

import java.util.Scanner;

public class InventoryApp {
    private Inventory myInventory;
    private Scanner input;

    public InventoryApp() {
        runApp();
    }

    private void runApp() {
        int command;

        init();

        while (true) {
            displayMenu();
            command = input.nextInt();
            input.nextLine();
            if (command == 0) {
                break;
            }
            processCommand(command);
        }

        System.out.println("Closing Inventory App...");

    }

    private void processCommand(int command) {
        if (command == 1) {
            doSearchItem();
        } else if (command == 2) {
            doAddItem();
        } else if (command == 3) {
            doRemoveItem();
        } else if (command == 4) {
            doRestockItem();
        } else if (command == 5) {
            doLowStockReminder();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void init() {
        myInventory = new Inventory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("MAIN MENU");
        System.out.println("\t1 - Search item");
        System.out.println("\t2 - Add new item");
        System.out.println("\t3 - Remove item");
        System.out.println("\t4 - Restock item");
        System.out.println("\t5 - Low stock reminder");
        System.out.println("\t0 - Close app");
    }

    private void doSearchItem() {
        String itemName;
        System.out.println("\nEnter the item you are looking for:");
        itemName = input.nextLine();


    }

    private void doAddItem() {
        String itemName;
        int qty;

        System.out.println("\nEnter the name of the item to be added:");
        itemName = input.nextLine();
        if (myInventory.addItem(itemName)) {
            setQuantity(itemName);
            setMinimumStockLimit(itemName);

            System.out.println(itemName + " is successfully added to the inventory.\n");
//            do {
//                System.out.println("Enter item quantity: ");
//                qty = input.nextInt();
//                input.nextLine();
//                if (qty < 0) {
//                    System.out.println("Value should be greater or equal to 0.\n");
//                } else {
//                    System.out.println(itemName + " is successfully added "
//                            + "to the inventory with a quantity of " + qty + "\n");
//                }
//            } while (qty < 0);
//            myInventory.addItemQuantity(itemName, qty);
        } else {
            System.out.println(itemName + " is already registered in the inventory.\n");
        }
    }

    private void setQuantity(String itemName) {
        int qty;
        while (true) {
            System.out.println("Enter item quantity: ");
            qty = input.nextInt();
            input.nextLine();
            if (qty < 0) {
                System.out.println("Value should be greater or equal to 0.\n");
            } else {
                myInventory.getItem(itemName).setQuantity(qty);
                break;
            }
        }
    }

    private void setMinimumStockLimit(String itemName) {
        int minStock;
        while (true) {
            System.out.println("Enter item minimum stock limit: ");
            minStock = input.nextInt();
            input.nextLine();
            if (minStock < 0) {
                System.out.println("Value should be greater or equal to 0.\n");
            } else {
                myInventory.getItem(itemName).setMinimumStockLimit(minStock);
                break;
            }
        }
    }

    private void doRemoveItem() {
        String itemName;
        boolean removed;

        System.out.println("\nEnter the name of the item to be removed:");
        itemName = input.nextLine();
        removed = myInventory.removeItem(itemName);
        if (removed) {
            System.out.println(itemName + " and related information has been deleted.\n");
        } else {
            System.out.println(itemName + " not found. \n");
        }
    }

    private void doRestockItem() {
        String itemName;
        int numNewStock;
        while (true) {
            System.out.println("\nEnter the name of the item to be restocked: ");
            itemName = input.nextLine();
            if (!myInventory.itemIsThere(itemName)) {
                System.out.println("\nItem not found.");
            } else {
                break;
            }
        }
        while (true) {
            System.out.println("Enter the number of new stocks coming: ");
            numNewStock = input.nextInt();
            if (numNewStock <= 0) {
                System.out.println("Value has to be greater than 0.");
            } else {
                break;
            }
        }
        myInventory.addItemQuantity(itemName, numNewStock);
        System.out.println(itemName + " has been restocked."
                + "\nQty: " + myInventory.getItem(itemName).getQuantity() + "\n");
    }

    private void doLowStockReminder() {
        if (myInventory.getLowStockItems().isEmpty()) {
            System.out.println("No item is low in stock.\n");
        } else {
            System.out.println("Please restock these items:");
            for (Item i : myInventory.getLowStockItems()) {
                System.out.println("\tName: " + i.getName());
                System.out.println("\tQty: " + i.getQuantity());
                System.out.println("\tMinimum Stock Limit: " + i.getMinimumStockLimit() + "\n");
            }
        }
    }
}
