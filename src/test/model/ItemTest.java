package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    Item testItem;

    @BeforeEach
    public void runBefore() {
        testItem = new Item("Test Item");
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Item", testItem.getName());
        assertEquals(0, testItem.getQuantity());
        assertEquals(0, testItem.getMinimumStockLimit());
    }

    @Test
    public void testEditName() {
        testItem.editName("New Name");
        assertEquals("New Name", testItem.getName());
    }

    @Test
    public void testAddQuantityOnce() {
        testItem.addQuantity(100);
        assertEquals(100, testItem.getQuantity());
    }

    @Test
    public void testAddQuantityMultipleTimes() {
        testItem.addQuantity(100);
        assertEquals(100, testItem.getQuantity());
        testItem.addQuantity(50);
        assertEquals(150, testItem.getQuantity());
    }

    @Test
    public void testIsLowStockIsTrue(){
        testItem.addQuantity(1);
        testItem.setMinimumStockLimit(10);
        assertTrue(testItem.isLowStock());
    }

    @Test
    public void testIsLowStockIsFalse(){
        testItem.addQuantity(100);
        testItem.setMinimumStockLimit(10);
        assertFalse(testItem.isLowStock());
    }

    @Test
    public void testIsLowStockBoundary(){
        testItem.addQuantity(10);
        testItem.setMinimumStockLimit(10);
        assertFalse(testItem.isLowStock());
    }

}
