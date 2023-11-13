package ui;

import model.Inventory;
import model.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Inventory application that lets user keeps track of items inside their inventory
public class Swing extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/inventory.json";

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Inventory myInventory;
    private Scanner input;
    private boolean running;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel mainMenuPanel;
    private JPanel myInventoryPanel;

    private JLayeredPane layeredPane;

    private JLabel mainMenuLabel;

    //private List<JButton> buttons;
    private JButton myInventoryButton;
    private JButton registerNewShipmentButton;
    private JButton lowStockWarningsButton;
    private JButton lookUpItemButton;
    private JButton addNewItemButton;
    private JButton backToMainMenuButton;

    private JMenuBar menuBar;
    private JMenu fileMenu;


    //EFFECTS: run the app
    public Swing() throws FileNotFoundException {
        super("Inventory App");






        myInventoryButton = new JButton();
        registerNewShipmentButton = new JButton();
        lowStockWarningsButton = new JButton();
        myInventoryButton.setBounds(200, 100, 400, 50);
        registerNewShipmentButton.setBounds(200, 175, 400, 50);
        lowStockWarningsButton.setBounds(200, 250, 400, 50);
        myInventoryButton.setText("My Inventory");
        registerNewShipmentButton.setText("Register New Shipment");
        lowStockWarningsButton.setText("Low Stock Wa rnings");
        myInventoryButton.setFocusable(false);
        registerNewShipmentButton.setFocusable(false);
        lowStockWarningsButton.setFocusable(false);
        myInventoryButton.addActionListener(this);

        lookUpItemButton = new JButton();
        addNewItemButton = new JButton();
        backToMainMenuButton = new JButton();
        lookUpItemButton.setBounds(200, 100, 400, 50);
        addNewItemButton.setBounds(200, 175, 400, 50);
        backToMainMenuButton.setBounds(200, 250, 400, 50);
        lookUpItemButton.setText("Look Up Item");
        addNewItemButton.setText("Add New Item");
        backToMainMenuButton.setText("Back to Main Menu");
        lookUpItemButton.setFocusable(false);
        addNewItemButton.setFocusable(false);
        backToMainMenuButton.setFocusable(false);

        runApp();



    }

    // MODIFIES: this
    // EFFECTS: starts the app and displays menu
    private void runApp() {

        init();

        setUpJFrame();


        layeredPane.setBounds(0, 0, WIDTH, HEIGHT);
        add(layeredPane);

        setUpMainMenuPanel();
        setUpMyInventoryPanel();
        layeredPane.add(mainMenuPanel, new Integer(0));


    }

    // MODIFIES: this
    // EFFECTS: initializes inventory and Scanner
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        myInventory = new Inventory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        running = true;

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        layeredPane = new JLayeredPane();
        mainMenuPanel = new JPanel();
        myInventoryPanel = new JPanel();

    }

    private void setUpJFrame() {
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, WIDTH, HEIGHT);
        add(layeredPane);
        setJMenuBar(menuBar);
    }

    private void setupMenuBar() {
        menuBar.add(fileMenu);
    }

    private void setUpButton(JButton b, String name) {
        //b.setBounds(200, 100, 400, 50);
        b.setSize(400, 50);
        b.setText(name);
        b.setFocusable(false);
        b.addActionListener(this);
    }

    private void setUpPanel(JPanel p) {
        p.setSize(WIDTH, HEIGHT);
        p.setVisible(true);
        p.setLayout(null);

    }

    private void setUpMainMenuPanel() {
        setUpPanel(mainMenuPanel);
        mainMenuPanel.add(myInventoryButton);
        mainMenuPanel.add(registerNewShipmentButton);
        mainMenuPanel.add(lowStockWarningsButton);

        mainMenuLabel = new JLabel("Main Menu");
        mainMenuPanel.add(mainMenuLabel);
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
        mainMenuLabel.setBounds(0, 10, 800, 50);
    }

    private void setUpMyInventoryPanel() {
        setUpPanel(mainMenuPanel);
        myInventoryPanel.add(lookUpItemButton);
        myInventoryPanel.add(addNewItemButton);
        myInventoryPanel.add(backToMainMenuButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myInventoryButton) {
            layeredPane.add(myInventoryPanel,new Integer(1));
        } else if (e.getSource() == registerNewShipmentButton) {

        }
    }
}
