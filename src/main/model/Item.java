package model;

//represents an item having a name, quantity, and minimum stock limit
public class Item {
    private String name;
    private int quantity;
    private int minimumStockLimit;

    //EFFECTS: constructs an item with given name, a quantity of 0, and a minimum stock limit of 0
    public Item(String name) {
        this.name = name;
        this.quantity = 0;
        this.minimumStockLimit = 0;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: adds quantity of item by amount
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    //EFFECTS: checks if item is low in stock.
    //         Returns true if quantity is less than minimum stock limit.
    //         Returns false if quantity is greater than or equal to minimum stock limit
    public boolean isLowStock() {
        if (quantity < minimumStockLimit) {
            return true;
        } else {
            return false;
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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
