package persistence;

import model.Inventory;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Inventory iTest = new Inventory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(iTest);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            iTest = reader.read();
            assertTrue(iTest.getItemList().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Inventory iTest = new Inventory();
            iTest.addItem("Item 1");
            iTest.addItem("Item 2");
            iTest.getItem("Item 1").setQuantity(10);
            iTest.getItem("Item 1").setMinimumStockLimit(1);
            iTest.getItem("Item 2").setQuantity(20);
            iTest.getItem("Item 2").setMinimumStockLimit(2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(iTest);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            iTest = reader.read();
            List<Item> actual = iTest.getItemList();
            assertEquals(2, actual.size());
            checkItem("Item 1", 10, 1, actual.get(0));
            checkItem("Item 2", 20, 2, actual.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}