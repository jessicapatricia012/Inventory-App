//package ui;
//
//import model.Inventory;
//import model.Item;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//import java.util.Scanner;
//
//// Inventory application that lets user keeps track of items inside their inventory
//public class Swing extends JFrame implements ActionListener {
//    private static final String JSON_STORE = "./data/inventory.json";
//
//    private static final int WIDTH = 1100;
//    private static final int HEIGHT = 600;
//
//    private Inventory myInventory;
//    private Scanner input;
//    private boolean running;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    private JPanel mainMenuPanel;
//    private JPanel myInventoryPanel;
//    private JPanel itemListPanel;
//    private JLayeredPane layeredPane;
//
//    private JLabel mainMenuLabel;
//
//    //private List<JButton> buttons;
//    private JButton myInventoryButton;
//    private JButton registerNewShipmentButton;
//    private JButton lowStockWarningsButton;
//    private JButton lookUpItemButton;
//    private JButton addNewItemButton;
//    private JButton backToMainMenuButton;
//
//    private JMenuBar menuBar;
//    private JMenu fileMenu;
//    private JMenuItem loadItem;
//    private JMenuItem saveItem;
//
//    private ItemTable itemTable;
//
//
//    //EFFECTS: run the app
//    public Swing() throws FileNotFoundException {
//        super("Inventory App");
//
//        myInventoryButton = new JButton("My Inventory");
//        registerNewShipmentButton = new JButton("Register New Shipment");
//        lowStockWarningsButton = new JButton("Low Stock Warnings");
//        myInventoryButton.setBounds(200, 100, 400, 50);
//        registerNewShipmentButton.setBounds(200, 175, 400, 50);
//        lowStockWarningsButton.setBounds(200, 250, 400, 50);
//        myInventoryButton.setFocusable(false);
//        registerNewShipmentButton.setFocusable(false);
//        lowStockWarningsButton.setFocusable(false);
//        myInventoryButton.addActionListener(this);
//
//        lookUpItemButton = new JButton("Look Up Item");
//        addNewItemButton = new JButton("Add New Item");
//        backToMainMenuButton = new JButton("Back to Main Menu");
//        lookUpItemButton.setBounds(200, 100, 400, 50);
//        addNewItemButton.setBounds(200, 175, 400, 50);
//        backToMainMenuButton.setBounds(200, 250, 400, 50);
//        lookUpItemButton.setFocusable(false);
//        addNewItemButton.setFocusable(false);
//        backToMainMenuButton.setFocusable(false);
//
//        runApp();
//
//
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: starts the app and displays menu
//    private void runApp() {
//
//        init();
//
//        setUpJFrame();
//        setUpMenuBar();
//
//
//
//        layeredPane.setBounds(0, 0, WIDTH, HEIGHT);
//        add(layeredPane);
//
//        setUpMainMenuPanel();
//        setUpMyInventoryPanel();
//        setUpItemListPanel();
//        layeredPane.add(mainMenuPanel, new Integer(0));
//        layeredPane.add(itemListPanel);
//
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes inventory and Scanner
//    private void init() {
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        myInventory = new Inventory();
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//        running = true;
//
//        menuBar = new JMenuBar();
//        fileMenu = new JMenu("File");
//        loadItem = new JMenuItem("Load");
//        saveItem = new JMenuItem("Save");
//        layeredPane = new JLayeredPane();
//        mainMenuPanel = new JPanel();
//        myInventoryPanel = new JPanel();
//        itemListPanel = new JPanel();
//
//        itemTable = new ItemTable(this);
//
//    }
//
//    private void setUpJFrame() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(WIDTH, HEIGHT);
//        setVisible(true);
//        layeredPane = new JLayeredPane();
//        layeredPane.setBounds(0, 0, WIDTH, HEIGHT);
//        add(layeredPane);
//        setJMenuBar(menuBar);
//
//    }
//
//    private void setUpMenuBar() {
//        menuBar.add(fileMenu);
//        fileMenu.add(loadItem);
//        fileMenu.add(saveItem);
//
//        fileMenu.setMnemonic(KeyEvent.VK_F);
//        loadItem.setMnemonic(KeyEvent.VK_L);
//        saveItem.setMnemonic(KeyEvent.VK_S);
//
//        loadItem.addActionListener(this);
//        saveItem.addActionListener(this);
//    }
//
//    private void setUpButton(JButton b, String name) {
//        //b.setBounds(200, 100, 400, 50);
//        b.setSize(400, 50);
//        b.setText(name);
//        b.setFocusable(false);
//        b.addActionListener(this);
//    }
//
//    private void setUpPanel(JPanel p) {
//        p.setSize(800, HEIGHT);
//        p.setVisible(true);
//        p.setLayout(null);
//
//    }
//
//    private void setUpMainMenuPanel() {
//        setUpPanel(mainMenuPanel);
//        //mainMenuPanel.add(myInventoryButton);
//        mainMenuPanel.add(registerNewShipmentButton);
//        mainMenuPanel.add(lowStockWarningsButton);
//
//        mainMenuLabel = new JLabel("Main Menu");
//        mainMenuPanel.add(mainMenuLabel);
//        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
//        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
//        mainMenuLabel.setBounds(0, 10, 800, 50);
//    }
//
//    private void setUpMyInventoryPanel() {
//        setUpPanel(myInventoryPanel);
//        myInventoryPanel.add(lookUpItemButton);
//        myInventoryPanel.add(addNewItemButton);
//        myInventoryPanel.add(backToMainMenuButton);
//
//    }
//
//    private void setUpregisterNewShipment() {
//        setUpPanel(myInventoryPanel);
//        myInventoryPanel.add(lookUpItemButton);
//        myInventoryPanel.add(addNewItemButton);
//        myInventoryPanel.add(backToMainMenuButton);
//
//    }
//
//    private void setUpItemListPanel() {
//        itemListPanel.setBounds(800, 0 , 300, HEIGHT);
//        itemListPanel.setBackground(Color.RED);
//        itemListPanel.setVisible(true);
//        itemListPanel.setLayout(null);
//        itemListPanel.add(itemTable);
//    }
//
//
//
//
//    // EFFECTS: saves the inventory to file
//    private void saveInventory() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(myInventory);
//            jsonWriter.close();
//            System.out.println("Saved inventory to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file to " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads inventory from file
//    private void loadInventory() {
//        try {
//            myInventory = jsonReader.read();
//            System.out.println("Loaded inventory from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file from " + JSON_STORE);
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == myInventoryButton) {
//            layeredPane.add(myInventoryPanel,new Integer(1));
//        } else if (e.getSource() == loadItem) {
//            loadInventory();
//        } else if (e.getSource() == saveItem) {
//            saveInventory();
//        }
//    }
//}
//
