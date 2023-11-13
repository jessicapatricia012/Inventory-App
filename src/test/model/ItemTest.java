package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item testItem;

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
    public void testAddQuantityOnce() {
        testItem.addQuantity(100);
        assertEquals(100, testItem.getQuantity());
    }

    @Test
    public void testAddQuantityBoundary() {
        testItem.addQuantity(1);
        assertEquals(1, testItem.getQuantity());
    }

    @Test
    public void testAddQuantityMultipleTimes() {
        testItem.addQuantity(100);
        testItem.addQuantity(50);
        assertEquals(150, testItem.getQuantity());
    }

    @Test
    public void testSubtractQuantityOnce() {
        testItem.addQuantity(100);
        testItem.subtractQuantity(10);
        assertEquals(90, testItem.getQuantity());
    }

    @Test
    public void testSubtractQuantityLowerBoundary() {
        testItem.addQuantity(100);
        testItem.subtractQuantity(1);
        assertEquals(99, testItem.getQuantity());
    }

    @Test
    public void testSubtractQuantityUpperBoundary() {
        testItem.addQuantity(100);
        testItem.subtractQuantity(100);
        assertEquals(0, testItem.getQuantity());
    }

    @Test
    public void testSubtractQuantityMultipleTimes() {
        testItem.addQuantity(100);
        testItem.subtractQuantity(10);
        testItem.subtractQuantity(50);
        assertEquals(40, testItem.getQuantity());
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

    @Test
    public void setQuantity() {
        testItem.setQuantity(100);
        assertEquals(100, testItem.getQuantity());
    }

    @Test
    public void setQuantityBoundary() {
        testItem.setQuantity(0);
        assertEquals(0, testItem.getQuantity());
    }

    @Test
    public void setMinimumStockLimit() {
        testItem.setMinimumStockLimit(100);
        assertEquals(100, testItem.getMinimumStockLimit());
    }

    @Test
    public void setMinimumStockLimitBoundary() {
        testItem.setMinimumStockLimit(0);
        assertEquals(0, testItem.getMinimumStockLimit());
    }

}