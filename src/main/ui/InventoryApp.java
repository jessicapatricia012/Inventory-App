package ui;

import model.Inventory;

import javax.xml.namespace.QName;
import java.util.Scanner;

public class InventoryApp {
    private Inventory myInventory;
    private Scanner input;

    public InventoryApp() {
        runApp();
    }

    private void runApp() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        int command = 0;
        do {
            displayMenu();
            command = input.nextInt();
            if (command == 0) {
                break;
            }
            processCommand(command);
        } while (true);

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
            doLowStockWarnings();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayMenu() {
        System.out.println("MAIN MENU");
        System.out.println("\t1 - Search item");
        System.out.println("\t2 - Add item");
        System.out.println("\t3 - Remove item");
        System.out.println("\t4 - Restock item");
        System.out.println("\t5 - Low stock warnings");
        System.out.println("\t0 - Close app");
    }

    private void doSearchItem() {
        String itemName;
        System.out.println("\nEnter the item you are looking for:");
        itemName = input.nextLine();


    }

    private void doAddItem() {
        int command;
        String itemName;
        boolean added;

        System.out.println("\nEnter the name of the item to be added:");
        itemName = input.nextLine();
        added = myInventory.addItem(itemName);
        if (added) {
            System.out.println(itemName + "is successfully added to the inventory.");
        } else {
            System.out.println(itemName + "is already registered in the inventory");
        }

    }

    private void doRemoveItem() {
        int command;
        String itemName;
        boolean removed;

        System.out.println("\nEnter the name of the item to be removed:");
        itemName = input.nextLine();
        removed = myInventory.removeItem(itemName);
        if (removed) {
            System.out.println(itemName + " and related information has been deleted.");
        } else {
            System.out.println(itemName + " not found. \nReturning to MAIN MENU...");
        }
    }

    private void doRestockItem() {
        String itemName;
        int numNewStock;
        do {
            System.out.println("\nEnter the name of the item to be restocked: ");
            itemName = input.nextLine();
            if (!myInventory.itemIsThere(itemName)) {
                System.out.println("\nItem not found.");
            }
        } while (!myInventory.itemIsThere(itemName));
        do {
            System.out.println("\nEnter the number of new stocks coming: ");
            numNewStock = input.nextInt();
            if (numNewStock <= 0) {
                System.out.println("\nValue has to be greater than 0.");
            }
        } while (numNewStock <= 0);
        myInventory.restock(itemName, numNewStock);
    }

    private void doLowStockWarnings() {

    }
}
