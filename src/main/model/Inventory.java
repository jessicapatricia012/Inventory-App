package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// represents an inventory containing a list of items
public class Inventory implements Writable {
    private List<Item> itemList;

    //EFFECTS: construct an empty list of items
    public Inventory() {
        itemList = new ArrayList<>();
    }

    //EFFECTS: Returns true if the inventory contains the item with corresponding name
    //         Returns false if item is not found
    public boolean itemIsThere(String name) {
        for (Item i : itemList) {
            if (i.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this (if Item is not already in the inventory)
    //EFFECTS: If item with the corresponding name exists, do nothing and return false.
    //         Otherwise, adds item into itemList and returns true
    public boolean addItem(String name) {
        Item newItem = new Item(name);
        if (itemIsThere(name)) {
            return false;
        } else {
            return itemList.add(newItem);
        }
    }

    //MODIFIES: this (if Item is in the inventory)
    //EFFECTS: If Item with the corresponding name does not exist, do nothing and return false.
    //         Otherwise, removes Item from the inventory (itemList) and returns true
    public boolean removeItem(String name) {
        for (Item i : itemList) {
            if (i.getName().equalsIgnoreCase(name)) {
                return itemList.remove(i);
            }
        }
        return false;
    }

    // REQUIRES: itemIsThere(name) == TRUE
    // EFFECTS: returns the Item with corresponding name
    public Item getItem(String name) {
        Item returned = null;
        for (Item i : itemList) {
            if (i.getName().equalsIgnoreCase(name)) {
                returned = i;
            }
        }
        return returned;
    }

    // EFFECTS: return itemList
    public List<Item> getItemList() {
        return itemList;
    }

    //EFFECTS: returns a list of inventory items that are low in stock
    public List<Item> getLowStockItems() {
        List<Item> lowStockItems = new ArrayList<>();
        for (Item i : itemList) {
            if (i.isLowStock()) {
                lowStockItems.add(i);
            }
        }
        return lowStockItems;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: returns items in inventory as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : itemList) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }


}

