package persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(String name, int quantity, int minimumStockLimit, Item item) {
        assertEquals(name, item.getName());
        assertEquals(quantity, item.getQuantity());
        assertEquals(minimumStockLimit, item.getMinimumStockLimit());
    }
}
