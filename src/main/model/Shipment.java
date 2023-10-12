package model;

import java.util.ArrayList;
import java.util.List;

// represents a batch of items being shipped in or out of the inventory.
// class contains a list of items in a batch and a string indicating whether the batch is
// shipped out or into the user's inventory
public class Shipment {

    List<Item> itemsShipped;
    private String inOrOut; //shows whether items are received or shipped out

    public Shipment(String inOrOut) {
        itemsShipped = new ArrayList<>();
        this.inOrOut = inOrOut;
    }

    public List<Item> getItemsShipped() {
        return itemsShipped;
    }



}
