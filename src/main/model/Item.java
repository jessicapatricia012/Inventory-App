package model;

//represents an item having
public class Item {
    private double buyPrice;
    private double sellPrice;
    private int quantity;
    private int minimumStock;
    private String name;


    public Item(String name) {
        this.name = name;
        this.buyPrice = 0;
        this.sellPrice = 0;
        this.quantity = 0;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

}
