package ui;

import model.Inventory;
import model.InventoryItem;

import java.util.Scanner;

//Inventory application
public class InventoryAppNew {
    private Inventory myInventory;
    private Scanner input;
    private boolean running;

    //EFFECTS: run the app
    public InventoryAppNew() {
        runApp();
    }

    //codes are based on TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        init();
        while (running) {
            displayMainMenu();
        }
        System.out.println("Closing Inventory App...");
    }

    private int command() {
        int command = input.nextInt();
        input.nextLine();

        return command;
    }

    // codes are based on TellerApp
    // MODIFIES: this
    // EFFECTS: initializes inventory and Scanner
    private void init() {
        myInventory = new Inventory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        running = true;
    }

    // EFFECTS: displays main menu to user
    private void displayMainMenu() {
        System.out.println("***** MAIN MENU *****");
        System.out.println("\t1 - My Inventory");
        System.out.println("\t2 - Register new Shipment");
        System.out.println("\t3 - Low stock warnings");
        System.out.println("\t0 - Close app");
        processCommandMainMenu(command());

    }

    //method is based on TellerApp
    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommandMainMenu(int command) {
        if (command == 1) {
            displayMyInventory();
            processCommandMyInventory(command());
        } else if (command == 2) {
            displayRegisterNewShipment();
            processCommandRegisterNewShipment(command());
        } else if (command == 3) {
            doLowStockReminder();
        } else if (command == 0) {
            running = false;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays "My Inventory" menu to user
    private void displayMyInventory() {
        System.out.println("\n*** MY INVENTORY ***");
        printAllItems();
        System.out.println("\t1 - Look up item");
        System.out.println("\t2 - Add new item");
        System.out.println("\t0 - Back to MAIN MENU");

    }

    //MODIFIES: this
    //EFFECTS: processes user command from "My Inventory" menu
    private void processCommandMyInventory(int command) {
        if (command == 1) {
            doLookUpItem();
        } else if (command == 2) {
            doAddNewItem();
        } else if (command == 0) {
            ;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays "My Inventory" menu to user
    private void displayRegisterNewShipment() {
        System.out.println("\n*** REGISTER NEW SHIPMENT ***");
        System.out.println("\t1 - Receive items");
        System.out.println("\t2 - Ship out items");
        System.out.println("\t0 - Back to MAIN MENU");
    }

    //MODIFIES: this
    //EFFECTS: processes user command from "My Inventory" menu
    private void processCommandRegisterNewShipment(int command) {
        if (command == 1) {
            doReceiveItems();
        } else if (command == 2) {
            doShipOutItems();
        } else if (command == 0) {
            ;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays all items in the inventory with their information.
    //          If inventory is empty, prints out a statement that says so.
    private void printAllItems() {
        if (myInventory.getItemList().isEmpty()) {
            System.out.println("Inventory is empty.\n");
        } else {
            for (InventoryItem i : myInventory.getItemList()) {
                printItemInfo(i);
            }
        }
    }

    private void doLookUpItem() {
        String itemName;
        System.out.println("Enter the item you are looking for: ");
        itemName = input.nextLine();
        if (!myInventory.itemIsThere(itemName)) {
            System.out.println("Item not found.\n");
        } else {
            printItemInfo(myInventory.getItem(itemName));
            System.out.println("\t1 - Edit info");
            System.out.println("\t2 - Delete item");
            System.out.println("\t0 - Back to MAIN MENU");
            processCommandLookUpItem(itemName, command());
        }
    }

    private void processCommandLookUpItem(String itemName, int command) {
        if (command == 1) {
            doEditItem(itemName);
        } else if (command == 2) {
            doDeleteItem(itemName);
        } else if (command == 0) {
            ;
        } else {
            System.out.println("Selection not valid...");
        }

    }

    // MODIFIES: this
    // EFFECTS: displays the information of the item that the user searched for (by name) and provides the user
    //          with options to edit the information related to the item.
    private void doEditItem(String itemName) {
        System.out.println("\nSelect item info to be edited:");
        System.out.println("\t1 - Quantity");
        System.out.println("\t2 - Minimum stock limit");
        System.out.println("\n\t0 - Back to MY INVENTORY");
        processEditItemCommand(itemName, command());
    }

    //MODIFIES: this
    //EFFECTS: processes user command from "Search and edit item info" menu
    private void processEditItemCommand(String itemName, int command) {
        if (command == 1) {
            setQuantity(itemName);
        } else if (command == 2) {
            setMinimumStockLimit(itemName);
        } else if (command == 0) {
            displayMyInventory();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: remove item entered by user from inventory.
    //          If item does not exist, prints out a statement that says so.
    private void doDeleteItem(String itemName) {
        myInventory.removeItem(itemName);
        System.out.println(itemName + " and related information has been deleted.\n");
    }

    // MODIFIES: this
    // EFFECTS: add new Item and its info into myInventory.
    //          If Item already exist, prints out a statement that says so.
    private void doAddNewItem() {
        String itemName;

        System.out.println("\nEnter the name of the item to be added:");
        itemName = input.nextLine();
        if (myInventory.addItem(itemName)) {
            setMinimumStockLimit(itemName);

            System.out.println(itemName + " is successfully added to the list.\n");
        } else {
            System.out.println(itemName + " is already registered in the list.\n");
        }
    }

    // MODIFIES: Item myInventory.getItem(itemName)
    // EFFECTS: set the item's quantity that has to be >= 0, otherwise
    //          user will be prompted to enter another amount >= 0
    private void setQuantity(String itemName) {
        while (true) {
            System.out.println("Enter item quantity: ");
            int qty = input.nextInt();
            input.nextLine();
            if (qty < 0) {
                System.out.println("Value should be greater or equal to 0.\n");
            } else {
                myInventory.getItem(itemName).setQuantity(qty);
                System.out.println(itemName + "quantity updated.\n");
                break;
            }
        }
    }

    // MODIFIES: Item myInventory.getItem(itemName)
    // EFFECTS: set the item's minimum stock limit that has to be >= 0, otherwise
    //          user will be prompted to enter another amount >= 0
    private void setMinimumStockLimit(String itemName) {
        while (true) {
            System.out.println("Enter item minimum stock limit: ");
            int minStock = input.nextInt();
            input.nextLine();
            if (minStock < 0) {
                System.out.println("Value should be greater or equal to 0.\n");
            } else {
                myInventory.getItem(itemName).setMinimumStockLimit(minStock);
                System.out.println(itemName + "minimum stock limit updated.\n");
                break;
            }
        }
    }

    private void doReceiveItems() {
        while (true) {
            System.out.println("Enter the item being received");
            String itemName = input.nextLine();
            System.out.println("Enter the quantity.");
            int qty = input.nextInt();
            input.nextLine();
            if (qty <= 0) {
                System.out.println("Quantity has to be greater than 0");
            } else {
                myInventory.addItem(itemName);
                myInventory.getItem(itemName).addQuantity(qty);
                break;
            }
        }
    }

    private void doShipOutItems() {
        while (true) {
            System.out.println("Enter the item being shipped out");
            String itemName = input.nextLine();
            if (myInventory.itemIsThere(itemName)) {
                System.out.println("Enter the quantity.");
                int qty = input.nextInt();
                input.nextLine();
                if (qty <= myInventory.getItem(itemName).getQuantity()) {
                    myInventory.getItem(itemName).subtractQuantity(qty);
                    break;
                } else if (qty <= 0) {
                    System.out.println("Quantity has to be greater than 0");
                } else {
                    System.out.println("Insufficient stock.\n");
                }
            } else {
                System.out.println("Item not found.\n");
            }
        }
    }

    // EFFECTS: displays item that has low stock.
    private void doLowStockReminder() {
        if (myInventory.getLowStockItems().isEmpty()) {
            System.out.println("No item is low in stock.\n");
        } else {
            System.out.println("Please restock these items:");
            for (InventoryItem i : myInventory.getLowStockItems()) {
                printItemInfo(i);
            }
        }
    }

    // EFFECTS: prints Item i's information to console
    private void printItemInfo(InventoryItem i) {
        System.out.println("\tName: " + i.getName());
        System.out.println("\tQty: " + i.getQuantity());
        System.out.println("\tMinimum Stock Limit: " + i.getMinimumStockLimit() + "\n");
    }


//    // MODIFIES: this, Item myInventory.getItem(itemName)
//    // EFFECTS: restock item based on amount input by user that has to be > 0 , otherwise
//    //          user will be prompted to enter another amount > 0
//    private void doRestockItem() {
//        String itemName;
//        int amount;
//        while (true) {
//            System.out.println("\nEnter the name of the item to be restocked: ");
//            itemName = input.nextLine();
//            if (!myInventory.itemIsThere(itemName)) {
//                System.out.println("\nItem not found.");
//            } else {
//                break;
//            }
//        }
//        while (true) {
//            System.out.println("Enter the number of new stocks coming: ");
//            amount = input.nextInt();
//            if (amount <= 0) {
//                System.out.println("Value has to be greater than 0.");
//            } else {
//                break;
//            }
//        }
//        myInventory.getItem(itemName).addQuantity(amount);
//        System.out.println(itemName + " has been restocked."
//                + "\nQty: " + myInventory.getItem(itemName).getQuantity() + "\n");
//    }

}