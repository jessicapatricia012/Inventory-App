package ui;

import model.Inventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Inventory application that lets user keeps track of items inside their inventory
public class InventoryAppGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/inventory.json";

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Inventory myInventory;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadItem;
    private JMenuItem saveItem;



    //EFFECTS: run the app
    public InventoryAppGUI() throws FileNotFoundException {
        super("Inventory App");

        runApp();
    }

    // MODIFIES: this
    // EFFECTS: starts the app and displays menu
    private void runApp() {
        init();

        setUpJFrame();
        setUpMenuBar();
        setJMenuBar(menuBar);


        displayMainMenu();
        //displayItemList();

    }

    // MODIFIES: this
    // EFFECTS: initializes inventory and Scanner
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        myInventory = new Inventory();

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
    }


    private void setUpJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);
    }

    private void setUpMenuBar() {
        menuBar.add(fileMenu);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);

        fileMenu.setMnemonic(KeyEvent.VK_F);
        loadItem.setMnemonic(KeyEvent.VK_L);
        saveItem.setMnemonic(KeyEvent.VK_S);

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
    }

    public void displayMainMenu() {
        add(new MainMenu(this));
    }

    public void displayItemList() {
        new ItemListWindow(this);
    }

    public void refreshItemList() {
        // STUB

    }

    public void displayLowStockWarnings() {
        new LowStockWindow(this);
    }

    // EFFECTS: saves the inventory to file
    private void saveInventory() {
        try {
            jsonWriter.open();
            jsonWriter.write(myInventory);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved inventory to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write from file to " + JSON_STORE,"ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads inventory from file
    private void loadInventory() {
        try {
            myInventory = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Loaded inventory from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file from " + JSON_STORE,"ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Inventory getMyInventory() {
        return myInventory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem) {
            loadInventory();

        } else if (e.getSource() == saveItem) {
            saveInventory();
        }
    }
}

