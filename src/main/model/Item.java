package model;

import org.json.JSONObject;
import persistence.Writable;

// represents an item having a name, quantity, and minimum stock limit
public class Item implements Writable {
    private String name;
    private int quantity;
    private int minimumStockLimit;

    //EFFECTS: constructs an item with given name, a quantity of 0, and a minimum stock limit of 0
    public Item(String name) {
        this.name = name;
        this.quantity = 0;
        this.minimumStockLimit = 0;
    }

    public Item(String name, int quantity, int minimumStockLimit) {
        this.name = name;
        this.quantity = quantity;
        this.minimumStockLimit = minimumStockLimit;
    }

    //REQUIRES: amount > 0
    //MODIFIES: this
    //EFFECTS: adds quantity by amount
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    // REQUIRES: 0 < amount <= getQuantity()
    // MODIFIES: this
    // EFFECTS: subtract quantity by amount
    public void subtractQuantity(int amount) {
        this.quantity -= amount;
    }

    // EFFECTS: checks if item is low in stock.
    //          Returns true if quantity is less than minimum stock limit.
    //          Returns false if quantity is greater than or equal to minimum stock limit
    public boolean isLowStock() {
        return quantity < minimumStockLimit;
    }

    // REQUIRES: quantity >= 0
    // MODIFIES: this
    // EFFECTS: assigns this.quantity to quantity
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: quantity >= 0
    // MODIFIES: this
    // EFFECTS: assigns this.quantity to quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // REQUIRES: minimumStockLimit >= 0
    // MODIFIES: this
    // EFFECTS: assigns this.minimumStockLimit to minimumStockLimit;
    public void setMinimumStockLimit(int minimumStockLimit) {
        this.minimumStockLimit = minimumStockLimit;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinimumStockLimit() {
        return minimumStockLimit;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Quantity", quantity);
        json.put("Minimum Stock Limit", minimumStockLimit);
        return json;
    }

}
