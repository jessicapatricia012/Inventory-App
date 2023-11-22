package persistence;

import model.Inventory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads inventory from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses inventory from JSON object and returns it
    private Inventory parseInventory(JSONObject jsonObject) {
        Inventory i = new Inventory();
        addItems(i, jsonObject);
        return i;
    }


    // MODIFIES: i
    // EFFECTS: parses items from JSON object and adds them to inventory
    private void addItems(Inventory i, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(i, nextItem);
        }

    }

    // MODIFIES: i
    // EFFECTS: parses item from JSON object and adds it to inventory
    private void addItem(Inventory i, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        int quantity = jsonObject.getInt("Quantity");
        int minimumStockLimit = jsonObject.getInt("Minimum Stock Limit");
        i.addItem(name);
        i.getItem(name).setQuantity(quantity);
        i.getItem(name).setMinimumStockLimit(minimumStockLimit);
    }
}
