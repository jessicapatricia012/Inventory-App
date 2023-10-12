package model;

public class Item {
    protected String name;
    protected int quantity;

    public Item(String name) {
        this.name = name;
        quantity = 0;
    }

    //EFFECTS: constructs an item with given name, a quantity of 0, and a minimum stock limit of 0
    public Item(String name, int qty) {
        this.name = name;
        this.quantity = qty;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: adds quantity of item by amount
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}

