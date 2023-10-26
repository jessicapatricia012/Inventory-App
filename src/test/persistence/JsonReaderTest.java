package persistence;

import model.Inventory;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Inventory iTest = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Inventory iTest = reader.read();
            assertTrue(iTest.getItemList().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Inventory iTest = reader.read();

            List<Item> actual = iTest.getItemList();
            assertEquals(2, actual.size());
            checkItem("Item 1", 10, 1, actual.get(0));
            checkItem("Item 2", 20, 2, actual.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}