package ui;

import model.Inventory;
import model.Item;

import java.util.Scanner;

//Inventory application
public class InventoryApp {
    private Inventory myInventory;
    private Scanner input;

    //EFFECTS: run the app
    public InventoryApp() {
        runApp();
    }

    //codes are based on TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        init();
        while (true) {
            displayMenu();
            int command = input.nextInt();
            input.nextLine();
            if (command == 0) {
                break;
            }
            processCommand(command);
        }
        System.out.println("Closing Inventory App...");
    }

    //codes are based on TellerApp
    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(int command) {
        if (command == 1) {
            doAllItems();
        } else if (command == 2) {
            doSearchEditItem();
        } else if (command == 3) {
            doAddItem();
        } else if (command == 4) {
            doRemoveItem();
        } else if (command == 5) {
            doRestockItem();
        } else if (command == 6) {
            doLowStockReminder();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // codes are based on TellerApp
    // MODIFIES: this
    // EFFECTS: initializes inventory and Scanner
    private void init() {
        myInventory = new Inventory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // codes are based on TellerApp
    // EFFECTS: displays main menu to user
    private void displayMenu() {
        System.out.println("MAIN MENU");
        System.out.println("\t1 - All items");
        System.out.println("\t2 - Search and edit item info");
        System.out.println("\t3 - Add new item");
        System.out.println("\t4 - Remove item");
        System.out.println("\t5 - Restock item");
        System.out.println("\t6 - Low stock reminder");
        System.out.println("\t0 - Close app");
    }

    // EFFECTS: displays all items in the inventory with their information.
    //          If inventory is empty, prints out a statement that says so.
    private void doAllItems() {
        if (myInventory.getItemList().isEmpty()) {
            System.out.println("Inventory is empty.\n");
        } else {
            for (Item i : myInventory.getItemList()) {
                printItemInfo(i);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the information of the item that the user searched for (by name) and provides the user
    //          with options to edit the information related to the item.
    private void doSearchEditItem() {
        String itemName;
        System.out.println("\nEnter the item you are looking for:");
        itemName = input.nextLine();
        if (myInventory.itemIsThere(itemName)) {
            while (true) {
                printItemInfo(myInventory.getItem(itemName));
                System.out.println("\nSelect item info to be edited:");
                System.out.println("\t1 - Name");
                System.out.println("\t2 - Quantity");
                System.out.println("\t3 - Minimum stock limit");
                System.out.println("\t0 - Back to MAIN MENU");
                int command = input.nextInt();
                input.nextLine();
                if (command == 0) {
                    break;
                }
                processSearchEditItemCommand(itemName, command);
            }
        } else {
            System.out.println("Item not found");
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command from "Search and edit item info" menu
    private void processSearchEditItemCommand(String itemName, int command) {
        if (command == 1) {
            setName(itemName);
        } else if (command == 2) {
            setQuantity(itemName);
        } else if (command == 3) {
            setMinimumStockLimit(itemName);
        } else if (command == 0) {
            displayMenu();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: add new Item and its info into myInventory.
    //          If Item already exist, prints out a statement that says so.
    private void doAddItem() {
        String itemName;

        System.out.println("\nEnter the name of the item to be added:");
        itemName = input.nextLine();
        if (myInventory.addItem(itemName)) {
            setQuantity(itemName);
            setMinimumStockLimit(itemName);

            System.out.println(itemName + " is successfully added to the inventory.\n");
        } else {
            System.out.println(itemName + " is already registered in the inventory.\n");
        }
    }

    // MODIFIES: Item myInventory.getItem(itemName)
    // EFFECTS: set(rename) the item's name
    private void setName(String itemName) {
        String newName;
        System.out.println("Enter new item name: ");
        newName = input.nextLine();
        myInventory.getItem(itemName).editName(newName);
    }

    // MODIFIES: Item myInventory.getItem(itemName)
    // EFFECTS: set the item's quantity that has to be >= 0, otherwise
    //          user will be prompted to enter another amount >= 0
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
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: remove item entered by user from inventory.
    //          If item does not exist, prints out a statement that says so.
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

    // MODIFIES: this, Item myInventory.getItem(itemName)
    // EFFECTS: restock item based on amount input by user that has to be > 0 , otherwise
    //          user will be prompted to enter another amount > 0
    private void doRestockItem() {
        String itemName;
        int amount;
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
            amount = input.nextInt();
            if (amount <= 0) {
                System.out.println("Value has to be greater than 0.");
            } else {
                break;
            }
        }
        myInventory.getItem(itemName).addQuantity(amount);
        System.out.println(itemName + " has been restocked."
                + "\nQty: " + myInventory.getItem(itemName).getQuantity() + "\n");
    }

    // EFFECTS: displays item that has low stock.
    private void doLowStockReminder() {
        if (myInventory.getLowStockItems().isEmpty()) {
            System.out.println("No item is low in stock.\n");
        } else {
            System.out.println("Please restock these items:");
            for (Item i : myInventory.getLowStockItems()) {
                printItemInfo(i);
            }

        }
    }

    // EFFECTS: prints Item i's information to console
    private void printItemInfo(Item i) {
        System.out.println("\tName: " + i.getName());
        System.out.println("\tQty: " + i.getQuantity());
        System.out.println("\tMinimum Stock Limit: " + i.getMinimumStockLimit() + "\n");
    }
}