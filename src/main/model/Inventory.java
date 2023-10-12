package model;

import java.util.*;

// represents an inventory containing a list of inventory items
public class Inventory {
    private List<InventoryItem> inventoryItemList;

    //EFFECTS: construct an empty list of items
    public Inventory() {
        inventoryItemList = new ArrayList<>();
    }

    //EFFECTS: Returns true if the inventory contains the Item with corresponding name
    //         Returns false if Item is not found
    public boolean itemIsThere(String name) {
        for (InventoryItem i : inventoryItemList) {
            if (i.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this (if Item is not already in the inventory)
    //EFFECTS: adds Item with given name into the inventory. If Item with the same name exists,
    //         do nothing and return false. Otherwise, adds Item into the inventory and returns true
    public boolean addItem(String name) {
        InventoryItem newInventoryItem = new InventoryItem(name);
        if (itemIsThere(name)) {
            return false; //item already there
        } else {
            return inventoryItemList.add(newInventoryItem);

        }
    }

    //MODIFIES: this (if Item is in the inventory)
    //EFFECTS: removes Item with corresponding name from the inventory. If Item with the same does not exists,
    //         do nothing and return false. Otherwise, removes Item from the inventory and returns true
    public boolean removeItem(String name) {
        for (InventoryItem i : inventoryItemList) {
            if (i.getName().equalsIgnoreCase(name)) {
                return inventoryItemList.remove(i);
            }
        }
        return false;
    }

    //REQUIRES: Item with corresponding name should be in the inventory
    //EFFECTS: returns the Item with corresponding name
    public InventoryItem getItem(String name) {
        InventoryItem returned = null;
        for (InventoryItem i : inventoryItemList) {
            if (i.getName().equalsIgnoreCase(name)) {
                returned = i;
            }
        }
        return returned;
    }

    public List<InventoryItem> getItemList() {
        return inventoryItemList;
    }

    //EFFECTS: returns a list of items that are low in stock (
    public List<InventoryItem> getLowStockItems() {
        List<InventoryItem> lowStockInventoryItems = new ArrayList<>();
        for (InventoryItem i : inventoryItemList) {
            if (i.isLowStock()) {
                lowStockInventoryItems.add(i);
            }
        }
        return lowStockInventoryItems;
    }


}

