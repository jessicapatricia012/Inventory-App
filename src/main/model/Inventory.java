package model;

import java.util.*;

// represents an inventory
public class Inventory {
    private List<Item> myInventory;

    public Inventory() {
        myInventory = new ArrayList<>();
    }

    public int numItems() {
        return myInventory.size();
    }

    public Item getItem(String name) {
        for (Item i : myInventory) {
            if (i.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null;
    }

    public boolean itemIsThere(String name) {
        if (!myInventory.isEmpty()) {
            for (Item i : myInventory) {
                if (i.getName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addItem(String name) {
        Item newItem = new Item(name);
        if (itemIsThere(name)) {
            return false; //item already there
        } else {
            return myInventory.add(newItem);
        }
    }

    public boolean removeItem(String name) {
        for (Item i : myInventory) {
            if (i.getName().equalsIgnoreCase(name)) {
                if (myInventory.remove(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addItemQuantity(String name, int numNewStock) {
        for (Item i : myInventory) {
            if (i.getName().equalsIgnoreCase(name)) {
                i.addQuantity(numNewStock);
            }
        }
    }

    public List<Item> getLowStockItems() {
        List<Item> lowStockItems = new ArrayList<>();
        for (Item i : myInventory) {
            if (i.isLowStock()) {
                lowStockItems.add(i);
            }
        }
        return lowStockItems;
    }


}
