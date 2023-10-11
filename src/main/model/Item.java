package model;

//represents an item having a name, quantity, buy and sell prices,
//
public class Item {
    private String name;
    private int quantity;
    private int minimumStockLimit;
    //    private double buyPrice;
//    private double sellPrice;
    private boolean isLowStock;


    public Item(String name) {
        this.name = name;
        this.quantity = 0;
        this.isLowStock = true;
//        this.buyPrice = 0;
//        this.sellPrice = 0;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

//    public void setBuyPrice(double buyPrice) {
//        this.buyPrice = buyPrice;
//    }
//
//    public void setSellPrice(double sellPrice) {
//        this.sellPrice = sellPrice;
//    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMinimumStockLimit(int minimumStockLimit) {
        this.minimumStockLimit = minimumStockLimit;
    }

    public boolean isLowStock() {
        if (quantity < minimumStockLimit) {
            return true;
        } else {
            return false;
        }
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
