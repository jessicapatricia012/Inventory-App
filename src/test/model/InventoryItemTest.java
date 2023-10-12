package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryItemTest {
    InventoryItem testInventoryItem;

    @BeforeEach
    public void runBefore() {
        testInventoryItem = new InventoryItem("Test Item");
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Item", testInventoryItem.getName());
        assertEquals(0, testInventoryItem.getQuantity());
        assertEquals(0, testInventoryItem.getMinimumStockLimit());
    }

    @Test
    public void testAddQuantityOnce() {
        testInventoryItem.addQuantity(100);
        assertEquals(100, testInventoryItem.getQuantity());
    }

    @Test
    public void testAddQuantityBoundary() {
        testInventoryItem.addQuantity(1);
        assertEquals(1, testInventoryItem.getQuantity());
    }

    @Test
    public void testAddQuantityMultipleTimes() {
        testInventoryItem.addQuantity(100);
        assertEquals(100, testInventoryItem.getQuantity());
        testInventoryItem.addQuantity(50);
        assertEquals(150, testInventoryItem.getQuantity());
    }

    @Test
    public void testSubtractQuantityOnce() {
        testInventoryItem.addQuantity(100);
        testInventoryItem.subtractQuantity(10);
        assertEquals(90, testInventoryItem.getQuantity());
    }

    @Test
    public void testSubtractQuantityLowerBoundary() {
        testInventoryItem.addQuantity(100);
        testInventoryItem.subtractQuantity(1);
        assertEquals(99, testInventoryItem.getQuantity());
    }

    @Test
    public void testSubtractQuantityUpperBoundary() {
        testInventoryItem.addQuantity(100);
        testInventoryItem.subtractQuantity(100);
        assertEquals(0, testInventoryItem.getQuantity());
    }

    @Test
    public void testSubtractQuantityMultipleTimes() {
        testInventoryItem.addQuantity(100);
        assertEquals(100, testInventoryItem.getQuantity());
        testInventoryItem.addQuantity(50);
        assertEquals(150, testInventoryItem.getQuantity());
    }

    @Test
    public void testIsLowStockIsTrue(){
        testInventoryItem.addQuantity(1);
        testInventoryItem.setMinimumStockLimit(10);
        assertTrue(testInventoryItem.isLowStock());
    }

    @Test
    public void testIsLowStockIsFalse(){
        testInventoryItem.addQuantity(100);
        testInventoryItem.setMinimumStockLimit(10);
        assertFalse(testInventoryItem.isLowStock());
    }

    @Test
    public void testIsLowStockBoundary(){
        testInventoryItem.addQuantity(10);
        testInventoryItem.setMinimumStockLimit(10);
        assertFalse(testInventoryItem.isLowStock());
    }

    @Test
    public void setQuantity() {
        testInventoryItem.setQuantity(100);
        assertEquals(100, testInventoryItem.getQuantity());
    }

    @Test
    public void setQuantityBoundary() {
        testInventoryItem.setQuantity(0);
        assertEquals(0, testInventoryItem.getQuantity());
    }

    @Test
    public void setMinimumStockLimit() {
        testInventoryItem.setMinimumStockLimit(100);
        assertEquals(100, testInventoryItem.getMinimumStockLimit());
    }

    @Test
    public void setMinimumStockLimitBoundary() {
        testInventoryItem.setMinimumStockLimit(0);
        assertEquals(0, testInventoryItem.getMinimumStockLimit());
    }

}
