package model;

//represents an item having a name, quantity, and minimum stock limit
public class InventoryItem {
    private String name;
    private int quantity;
    private int minimumStockLimit;

    //EFFECTS: constructs an item with given name, a quantity of 0, and a minimum stock limit of 0
    public InventoryItem(String name) {
        this.name = name;
        quantity = 0;
    }

    //REQUIRES: amount > 0
    //MODIFIES: this
    //EFFECTS: adds quantity of item by amount
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    //REQUIRES: 0 < amount <= getQuantity()
    //MODIFIES: this
    //EFFECTS: subtract quantity of item by amount
    public void subtractQuantity(int amount) {
        this.quantity -= amount;
    }

    //EFFECTS: checks if item is low in stock.
    //         Returns true if quantity is less than minimum stock limit.
    //         Returns false if quantity is greater than or equal to minimum stock limit
    public boolean isLowStock() {
        return quantity < minimumStockLimit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //REQUIRES: minimumStockLimit >= 0
    //MODIFIES: this
    //EFFECTS: set item's minimum stock limit
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

}
