package model;

import java.util.*;

// represents an inventory
public class Inventory {
    private List<Item> myInventory;

    public Inventory() {
        myInventory = new ArrayList<>();
    }

    public void addItem(String name, String brand) {
        Item newItem = new Item(name, brand);
        myInventory.add(newItem);
    }




}
