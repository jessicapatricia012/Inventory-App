package model;

import java.util.*;

// represents an inventory
public class Inventory {
    private List<Item> myInventory;

    public Inventory() {
        myInventory = new ArrayList<>();
    }

    public boolean itemIsThere(String name) {
        for (Item i : myInventory) {
            if (i.getName().equalsIgnoreCase(name)) {
                return true;
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
        if (itemIsThere(name)) {
            return myInventory.remove(name);
        } else {
            return false; //item not found
        }
    }

    public void restock(String name, int numNewStock) {
        for (Item i : myInventory) {
            if (i.getName().equalsIgnoreCase(name)) {
                i.addQuantity(numNewStock);
            }
        }
    }

    public void checkLowStock() {

    }


}
