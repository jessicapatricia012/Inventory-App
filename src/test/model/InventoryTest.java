package model;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    Inventory testInventory;

    @BeforeEach
    public void runBefore() {
        testInventory = new Inventory();
    }

    @Test
    public void testConstructor() {
        assertTrue(testInventory.getItemList().isEmpty());
    }

    @Test
    public void testItemIsThere() {
        assertFalse(testInventory.itemIsThere("item 1"));
        assertFalse(testInventory.itemIsThere("item 2"));
        assertFalse(testInventory.itemIsThere("item 3"));

        testAddItem(); // adds item 1, 2

        assertTrue(testInventory.itemIsThere("Item 1"));
        assertTrue(testInventory.itemIsThere("Item 2"));
        assertFalse(testInventory.itemIsThere("item 3"));
    }

    @Test
    public void testAddItem() {
        assertTrue(testInventory.addItem("item 1"));
        assertFalse(testInventory.addItem("Item 1"));
        assertTrue(testInventory.addItem("item 2"));
    }

    @Test
    public void testRemoveItem() {
        addItems(); // adds item 1, 2

        assertTrue(testInventory.removeItem("item 1"));
        assertFalse(testInventory.removeItem("item 1"));
        assertFalse(testInventory.removeItem("item 3"));
    }

    @Test
    public void testAddItemQuantity() {
        addItems(); // adds item 1, 2

        testInventory.addItemQuantity("item 1", 10);
        assertEquals(10, testInventory.getItem("item 1").getQuantity());
        testInventory.addItemQuantity("item 1", 10);
        assertEquals(20, testInventory.getItem("item 1").getQuantity());
    }

    @Test
    public void test


    public void addItems(){
        testInventory.addItem("Item 1");
        testInventory.addItem("Item 2");
    }
}