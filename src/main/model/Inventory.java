package model;

import java.util.*;

// represents an inventory containing a list of items
public class Inventory {
    private List<Item> itemList;

    //EFFECTS: construct an empty list of items
    public Inventory() {
        itemList = new ArrayList<>();
    }


    public Item getItem(String name) {
        for (Item i : itemList) {
            if (i.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null;
    }

    public boolean itemIsThere(String name) {
        if (!itemList.isEmpty()) {
            for (Item i : itemList) {
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
            return itemList.add(newItem);
        }
    }

    public boolean removeItem(String name) {
        for (Item i : itemList) {
            if (i.getName().equalsIgnoreCase(name)) {
                if (itemList.remove(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addItemQuantity(String name, int numNewStock) {
        for (Item i : itemList) {
            if (i.getName().equalsIgnoreCase(name)) {
                i.addQuantity(numNewStock);
            }
        }
    }

    public List<Item> getItemList() {
        return itemList;
    }


    public List<Item> getLowStockItems() {
        List<Item> lowStockItems = new ArrayList<>();
        for (Item i : itemList) {
            if (i.isLowStock()) {
                lowStockItems.add(i);
            }
        }
        return lowStockItems;
    }


}

