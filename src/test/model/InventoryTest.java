package model;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    Inventory testInventory;

    @BeforeEach
    public void runBefore(){
        testInventory = new Inventory();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testInventory.numItems());
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
    public void testAddItem(){
        assertTrue(testInventory.addItem("item 1"));
        assertFalse(testInventory.addItem("Item 1"));
        assertTrue(testInventory.addItem("item 2"));
    }

    @Test
    public void testRemoveItem(){
        testAddItem();

        assertTrue(testInventory.removeItem("item 1"));
    }

}