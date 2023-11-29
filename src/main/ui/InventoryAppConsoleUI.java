package ui;

import model.Inventory;
import model.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Inventory application that lets user keeps track of items inside their inventory
public class InventoryAppConsoleUI {
    private static final String JSON_STORE = "./data/inventory.json";

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Inventory myInventory;
    private Scanner input;
    private boolean running;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: run the app
    public InventoryAppConsoleUI() throws FileNotFoundException {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: starts the app and displays menu
    private void runApp() {
        init();
        while (running) {
            displayMainMenu();
            processInputMainMenu(input.nextLine());
        }
        System.out.println("Closing Inventory App...");
    }

    // MODIFIES: this
    // EFFECTS: initializes inventory and Scanner
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        myInventory = new Inventory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        running = true;
    }

    // EFFECTS: displays main menu to user and takes user input
    private void displayMainMenu() {
        System.out.println("***** MAIN MENU *****");
        System.out.println("\t1 - My Inventory");
        System.out.println("\t2 - Register new shipment");
        System.out.println("\t3 - Low stock warnings");
        System.out.println("\t4 - Save inventory");
        System.out.println("\t5 - Load inventory");
        System.out.println("\t0 - Close app");
    }

    // MODIFIES: this
    // EFFECTS: processes user's input from "Main Menu"
    private void processInputMainMenu(String command) {
        if (command.equals("1")) {
            displayMyInventory();
            processInputMyInventory(input.nextLine());
        } else if (command.equals("2")) {
            displayRegisterNewShipment();
            processInputRegisterNewShipment(input.nextLine());
        } else if (command.equals("3")) {
            doLowStockReminder();
        } else if (command.equals("4")) {
            saveInventory();
        } else if (command.equals("5")) {
            loadInventory();
        } else if (command.equals("0")) {
            running = false;
        } else {
            System.out.println("Please enter a valid option.");
        }
    }

    // EFFECTS: displays "My Inventory" menu to user and processes user input
    private void displayMyInventory() {
        System.out.println("\n*** MY INVENTORY ***");
        printAllItems();
        System.out.println("\t1 - Look up item");
        System.out.println("\t2 - Add new item");
        System.out.println("\t0 - Back to MAIN MENU");
    }

    // MODIFIES: this
    // EFFECTS: processes user's input from "My Inventory" menu
    private void processInputMyInventory(String command) {
        if (command.equals("1")) {
            doLookUpItem();
        } else if (command.equals("2")) {
            doAddNewItem();
        } else if (command.equals("0")) {
            ;
        } else {
            System.out.println("Please enter a valid option.");
        }
    }

    // EFFECTS: displays "Register New Shipment" menu to user and processes user's input
    private void displayRegisterNewShipment() {
        System.out.println("\n*** REGISTER NEW SHIPMENT ***");
        System.out.println("\t1 - Receive items");
        System.out.println("\t2 - Ship out items");
        System.out.println("\t0 - Back to MAIN MENU");
    }

    // MODIFIES: this
    // EFFECTS: processes user's input from "Register New Shipment" menu
    private void processInputRegisterNewShipment(String command) {
        if (command.equals("1")) {
            doReceiveItems();
        } else if (command.equals("2")) {
            doShipOutItems();
        } else if (command.equals("0")) {
            ;
        } else {
            System.out.println("Please enter a valid option.");
        }
    }

    // EFFECTS: displays all items in the inventory with their information.
    //          If inventory is empty, prints out a statement that says so.
    private void printAllItems() {
        if (myInventory.getItemList().isEmpty()) {
            System.out.println("Inventory is empty.\n");
        } else {
            for (Item i : myInventory.getItemList()) {
                printItemInfo(i);
            }
        }
    }

    // EFFECTS: displays the item with its information that the user is searching for (by name)
    //          If item is not found, prints out a statement that says so.
    private void doLookUpItem() {
        String itemName;
        System.out.println("Enter the item you are looking for: ");
        itemName = input.nextLine().toUpperCase();
        if (!myInventory.itemIsThere(itemName)) {
            System.out.println("Item " + itemName + " not found.\n");
        } else {
            printItemInfo(myInventory.getItem(itemName));
            displayEditOrDeleteItem();
            processInputEditOrDeleteItem(itemName, input.nextLine());
        }
    }


    // EFFECTS: displays "Edit or Delete Item" menu to user and processes user's input
    private void displayEditOrDeleteItem() {
        System.out.println("\t1 - Edit item");
        System.out.println("\t2 - Delete item");
        System.out.println("\t0 - Back to MAIN MENU");
    }

    // REQUIRES: myInventory.itemIsThere(itemName) == TRUE
    // MODIFIES: this
    // EFFECTS: processes user's input from "Edit or Delete Item" menu
    private void processInputEditOrDeleteItem(String itemName, String command) {
        if (command.equals("1")) {
            displayEditItem();
            processEditItemCommand(itemName, input.nextLine());
        } else if (command.equals("2")) {
            doDeleteItem(itemName);
        } else if (command.equals("0")) {
            ;
        } else {
            System.out.println("Selection not valid...");
        }

    }

    // EFFECTS: displays "Edit Item" menu to user and processes user's input
    private void displayEditItem() {
        System.out.println("\t1 - Edit quantity");
        System.out.println("\t2 - Edit minimum stock limit");
        System.out.println("\t0 - Back to MY INVENTORY");
    }

    // REQUIRES: myInventory.itemIsThere(itemName) == TRUE
    // MODIFIES: this
    // EFFECTS: processes user command from "Edit item" menu
    private void processEditItemCommand(String itemName, String command) {
        if (command.equals("1")) {
            setQuantity(itemName);
        } else if (command.equals("2")) {
            setMinimumStockLimit(itemName);
        } else if (command.equals("0")) {
            displayMyInventory();
            processInputMyInventory(input.nextLine());
        } else {
            System.out.println("Please enter a valid option.");
        }
    }

    // REQUIRES: myInventory.itemIsThere(itemName) == TRUE
    // MODIFIES: this
    // EFFECTS: removes item entered by user from inventory.
    private void doDeleteItem(String itemName) {
        myInventory.removeItem(itemName);
        System.out.println(itemName.toUpperCase() + " and its related information has been deleted.\n");
    }

    // MODIFIES: this
    // EFFECTS: adds new Item into myInventory.
    //          If Item already exist, prints out a statement that says so.
    private void doAddNewItem() {
        String itemName;

        System.out.println("\nEnter the name of the item to be added:");
        itemName = input.nextLine().toUpperCase();
        if (myInventory.addItem(itemName)) {
            System.out.println("A new item " + itemName + " has been registered in the inventory.");
            setMinimumStockLimit(itemName);
        } else {
            System.out.println(itemName + " is already registered in the inventory.\n");
        }
    }

    // REQUIRES: myInventory.itemIsThere(itemName) == TRUE
    // MODIFIES: myInventory.getItem(itemName)
    // EFFECTS: set the item's quantity that has to be >= 0, otherwise
    //          user will be prompted to enter another amount >= 0
    private void setQuantity(String itemName) {
        while (true) {
            System.out.println("Enter item quantity. Please enter an integer: ");
            int qty = input.nextInt();
            input.nextLine();
            if (qty < 0) {
                System.out.println("Quantity should be greater than or equal to 0.\n");
            } else {
                myInventory.getItem(itemName).setQuantity(qty);
                System.out.println(itemName + "'s quantity has been updated.\n");
                break;
            }
        }
    }

    // REQUIRES: myInventory.itemIsThere(itemName) == TRUE
    // MODIFIES: myInventory.getItem(itemName)
    // EFFECTS: set the item's minimum stock limit that has to be >= 0, otherwise
    //          user will be prompted to enter another amount >= 0
    private void setMinimumStockLimit(String itemName) {
        while (true) {
            System.out.println("Enter item minimum stock limit. Please enter an integer: ");
            int minStock = input.nextInt();
            input.nextLine();
            if (minStock < 0) {
                System.out.println("Minimum stock limit should be greater than or equal to 0.\n");
            } else {
                myInventory.getItem(itemName).setMinimumStockLimit(minStock);
                System.out.println(itemName + "'s minimum stock limit has been set.\n");
                break;
            }
        }
    }


    // MODIFIES: this, myInventory.getItem(itemName)
    // EFFECTS: process item being shipped into the inventory, prompts user to input the quantity being received, and
    //          updates the item's stock. If item is not in the inventory, register the new item.
    private void doReceiveItems() {
        System.out.println("Enter the item being received");
        String itemName = input.nextLine().toUpperCase();
        System.out.println("Enter item quantity. Please enter an integer: ");
        int qty = input.nextInt();
        input.nextLine();
        if (qty <= 0) {
            System.out.println("Quantity has to be greater than 0");
        } else {
            if (myInventory.addItem(itemName)) {
                System.out.println("A new item " + itemName + " has been registered in the inventory.");
                setMinimumStockLimit(itemName);
            } else {
                System.out.println(itemName + "'s stock has been updated.\n");
            }
            myInventory.getItem(itemName).addQuantity(qty);
        }
    }

    // MODIFIES: this, myInventory.getItem(itemName)
    // EFFECTS: process item being shipped out of the inventory. If item is not in the inventory, prints out a statement
    //          that says so. Otherwise, prompts user to input the quantity being shipped out, and updates the item's
    //          stock. If the stock is insufficient, prints out a statement that says so.
    private void doShipOutItems() {
        System.out.println("Enter the item being shipped out:");
        String itemName = input.nextLine().toUpperCase();
        if (myInventory.itemIsThere(itemName)) {
            System.out.println("Enter item quantity. Please enter an integer:");
            int qty = input.nextInt();
            input.nextLine();
            if (0 < qty && qty <= myInventory.getItem(itemName).getQuantity()) {
                myInventory.getItem(itemName).subtractQuantity(qty);
                System.out.println(itemName + "'s stock has been updated.\n");
            } else if (qty <= 0) {
                System.out.println("Quantity has to be greater than 0");
            } else {
                System.out.println("Insufficient stock.\n");
            }
        } else {
            System.out.println("Item " + itemName + " is not found.\n");
        }
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

    // EFFECTS: prints Item i's name, quantity, and minimum stock limit to console
    private void printItemInfo(Item i) {
        System.out.println("\tName: " + i.getName());
        System.out.println("\tQty: " + i.getQuantity());
        System.out.println("\tMinimum Stock Limit: " + i.getMinimumStockLimit() + "\n");
    }


    // EFFECTS: saves the inventory to file
    private void saveInventory() {
        try {
            jsonWriter.open();
            jsonWriter.write(myInventory);
            jsonWriter.close();
            System.out.println("Saved inventory to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file to " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads inventory from file
    private void loadInventory() {
        try {
            myInventory = jsonReader.read();
            System.out.println("Loaded inventory from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file from " + JSON_STORE);
        }
    }
}
