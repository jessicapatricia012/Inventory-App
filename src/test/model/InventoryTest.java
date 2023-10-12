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

        testAddItem();

        assertTrue(testInventory.itemIsThere("Item 1"));
        assertTrue(testInventory.itemIsThere("Item 2"));
        assertTrue(testInventory.itemIsThere("Item 3"));
        assertFalse(testInventory.itemIsThere("item 4"));
    }

    @Test
    public void testAddItem() {
        assertTrue(testInventory.addItem("item 1"));
        assertFalse(testInventory.addItem("Item 1"));
        assertTrue(testInventory.addItem("item 2"));

        assertTrue(testInventory.itemIsThere("Item 1"));
        assertTrue(testInventory.itemIsThere("Item 2"));
    }

    @Test
    public void testRemoveItem() {
        addItems();

        assertTrue(testInventory.removeItem("item 1"));
        assertFalse(testInventory.removeItem("item 1"));
        assertFalse(testInventory.removeItem("item 4"));

        assertFalse(testInventory.itemIsThere("item 1"));
    }

    @Test
    public void testGetItem() {
        addItems();
        assertEquals("Item 1",testInventory.getItem("Item 1").getName());
    }


    @Test
    public void testGetLowStockOItems() {
        addItems();
        testInventory.getItem("Item 1").setQuantity(10);
        testInventory.getItem("Item 2").setQuantity(0);
        testInventory.getItem("Item 3").setQuantity(1);

        testInventory.getItem("Item 1").setMinimumStockLimit(5);
        testInventory.getItem("Item 2").setMinimumStockLimit(5);
        testInventory.getItem("Item 3").setMinimumStockLimit(5);

        List<InventoryItem> expected = new ArrayList<>();
        expected.add(testInventory.getItem("Item 2"));
        expected.add(testInventory.getItem("Item 3"));

        assertEquals(expected, testInventory.getLowStockItems());
    }



    public void addItems(){
        testInventory.addItem("Item 1");
        testInventory.addItem("Item 2");
        testInventory.addItem("Item 3");
    }
}