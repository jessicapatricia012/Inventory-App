package ui;

import model.Event;
import model.EventLog;
import model.Inventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Inventory application that lets user keeps track of items inside their inventory
public class InventoryAppGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/inventory.json";

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private Inventory myInventory;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadItem;
    private JMenuItem saveItem;

    private MainMenu mainMenu;
    private AllItemsWindow allItemsWindow;
    private LowStockWindow lowStockWindow;

    //EFFECTS: run the app
    public InventoryAppGUI() throws FileNotFoundException {
        super("Inventory App");

        runApp();
    }

    // MODIFIES: this
    // EFFECTS : starts the app and displays main menu
    private void runApp() {
        init();

        setUpJFrame();
        setUpMenuBar();
        setJMenuBar(menuBar);

        displayMainMenu();
    }

    // MODIFIES: this
    // EFFECTS : initializes fields
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        myInventory = new Inventory();

        mainMenu = new MainMenu(this);
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
    }

    // MODIFIES: this
    // EFFECTS : sets up JFrame
    private void setUpJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog log = EventLog.getInstance();
                for (Event ev : log) {
                    System.out.println(ev.toString());
                }
                e.getWindow().dispose();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS : sets up menuBar, JMenus and JMenuItems
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

    // MODIFIES: this
    // EFFECTS : displays main menu panel
    public void displayMainMenu()    {
        add(mainMenu);
    }

    // EFFECTS : opens a new window that displays item list
    public void displayItemList() {
        allItemsWindow = new AllItemsWindow(this);
    }

    // EFFECTS : opens a new window that displays low stock item list
    public void displayLowStockItemList() {
        lowStockWindow = new LowStockWindow(this);
    }

    // MODIFIES: AllItemsWindow
    // EFFECTS : closes item list window
    public void closeItemList() {
        allItemsWindow.dispose();
    }

    // MODIFIES: LowStockWindow
    // EFFECTS : closes low stock item list window
    public void closeLowStockItemList() {
        lowStockWindow.dispose();
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

    // MODIFIES: this
    // EFFECTS : handles ActionEvent for loading and saving
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem) {
            loadInventory();
        } else if (e.getSource() == saveItem) {
            saveInventory();
        }
    }

}

