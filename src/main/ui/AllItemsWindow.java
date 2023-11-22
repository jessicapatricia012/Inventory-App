package ui;

import model.Item;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// A new window that displays list of all items in the inventory
public class AllItemsWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private String searchedItem;
    private String enteredName;
    private int enteredQuantity;
    private int enteredMinStockLimit;

    private InventoryAppGUI inventoryApp;

    private JLabel nameLabel;
    private JLabel quantityLabel;
    private JLabel minStockLimitLabel;

    private List<JTextField> textFields;
    private JTextField searchTextField;
    private JTextField nameTextField;
    private JTextField quantityTextField;
    private JTextField minStockLimitTextField;

    private List<JButton> buttons;
    private JButton searchButton;
    private JButton addItemButton;
    private JButton editItemButton;
    private JButton deleteItemButton;

    private ImageIcon searchIcon;
    private ImageIcon addIcon;
    private ImageIcon editIcon;
    private ImageIcon deleteIcon;

    private JScrollPane scrollPane;
    private JTable table;
    private AllItemsCustomTableModel model;
    private TableRowSorter rowSorter;

    private JPanel searchPanel;
    private JPanel textFieldPanel;
    private JPanel buttonsPanel;



    // EFFECTS : creates a new window
    AllItemsWindow(InventoryAppGUI inventoryApp) {
        super("Item List");
        this.inventoryApp = inventoryApp;

        setUp();
    }

    // MODIFIES: this
    // EFFECTS : sets up JFrame and adds components
    public void setUp() {
        setSize(WIDTH, HEIGHT);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        setUpLabels();
        setUpTextFields();
        setUpIcons();
        setUpButtons();

        setUpSearchPanel();
        add(searchPanel);

        setUpTablePanel();
        add(scrollPane);

        setUpTextFieldsPanel();
        add(textFieldPanel);

        setUpButtonsPanel();
        add(buttonsPanel);

        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS : initializes, sets up, and adds components to searchPanel
    public void setUpSearchPanel() {
        searchPanel = new JPanel(new FlowLayout());

        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
    }

    // MODIFIES: this
    // EFFECTS : initializes and sets up scrollPane
    public void setUpTablePanel() {
        model = new AllItemsCustomTableModel(inventoryApp.getMyInventory());
        table = new JTable(model);
        rowSorter = new TableRowSorter(model);
        table.setRowSorter(rowSorter);
        scrollPane = new JScrollPane(table);
    }

    // MODIFIES: this
    // EFFECTS : initializes and adds components to textFieldPanel
    public void setUpTextFieldsPanel() {
        textFieldPanel = new JPanel(new FlowLayout());

        textFieldPanel.add(nameLabel);
        textFieldPanel.add(nameTextField);
        textFieldPanel.add(quantityLabel);
        textFieldPanel.add(quantityTextField);
        textFieldPanel.add(minStockLimitLabel);
        textFieldPanel.add(minStockLimitTextField);
    }

    // MODIFIES: this
    // EFFECTS : initializes and adds components to buttonsPanel
    public void setUpButtonsPanel() {
        buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.add(addItemButton);
        buttonsPanel.add(editItemButton);
        buttonsPanel.add(deleteItemButton);
    }

    // MODIFIES: this
    // EFFECTS : initializes and sets up JLabels
    public void setUpLabels() {
        nameLabel = new JLabel("Name:");
        quantityLabel = new JLabel("Quantity:");
        minStockLimitLabel = new JLabel("Min. Stock Limit:");
    }

    // MODIFIES: this
    // EFFECTS : initializes and sets up JTextFields
    public void setUpTextFields() {
        textFields = new ArrayList<>();
        searchTextField = new JTextField();
        nameTextField = new JTextField();
        quantityTextField = new JTextField();
        minStockLimitTextField = new JTextField();

        textFields.add(nameTextField);
        textFields.add(quantityTextField);
        textFields.add(minStockLimitTextField);

        for (JTextField tf : textFields) {
            tf.addActionListener(this);
            tf.setPreferredSize(new Dimension(100, 20));
            tf.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }

        searchTextField.addActionListener(this);
        searchTextField.setPreferredSize(new Dimension(200, 20));
        searchTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

    }

    // MODIFIES: this
    // EFFECTS : initializes and sets up JButtons
    public void setUpButtons() {
        buttons = new ArrayList<>();
        searchButton = new JButton("Search");
        addItemButton = new JButton("Add Item");
        editItemButton = new JButton("Edit Item");
        deleteItemButton = new JButton("Delete Item");
        buttons.add(searchButton);
        buttons.add(addItemButton);
        buttons.add(editItemButton);
        buttons.add(deleteItemButton);
        for (JButton b : buttons) {
            b.setPreferredSize(new Dimension(120, 30));
            b.setFocusable(false);
            b.addActionListener(this);
            b.setBorder(BorderFactory.createEtchedBorder());
            b.setBackground(new Color(248, 248, 248));
        }
        searchButton.setPreferredSize(new Dimension(100, 25));
        deleteItemButton.setForeground(Color.WHITE);
        deleteItemButton.setBackground(new Color(229, 74, 74));
        searchButton.setIcon(searchIcon);
        addItemButton.setIcon(addIcon);
        editItemButton.setIcon(editIcon);
        deleteItemButton.setIcon(deleteIcon);
    }

    // MODIFIES: this
    // EFFECTS : initializes and sets up ImageIcons
    private void setUpIcons() {
        searchIcon = new ImageIcon(((new ImageIcon("./data/Search icon.png"))
                .getImage()).getScaledInstance(15,15, java.awt.Image.SCALE_SMOOTH));
        addIcon = new ImageIcon(((new ImageIcon("./data/Add icon.png"))
                .getImage()).getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH));
        editIcon = new ImageIcon(((new ImageIcon("./data/Edit icon.png"))
                .getImage()).getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH));
        deleteIcon = new ImageIcon(((new ImageIcon("./data/Delete icon.png"))
                .getImage()).getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH));
    }

    // MODIFIES: this
    // EFFECTS : handles ActionEvent
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] rows = convertRows(table.getSelectedRows());
        searchedItem = searchTextField.getText().toUpperCase();

        if (e.getSource() == searchButton) {
            searchedItem = searchTextField.getText().toUpperCase();
        } else if (e.getSource() == addItemButton) {
            doAddItem();
        } else if (e.getSource() == editItemButton) {
            try {
                doEditItem(rows);
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Please select the item you would like to "
                        + "edit.","ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteItemButton) {
            doDeleteItem(rows);
        }
        rowSorter.setRowFilter(new RowFilter() {
            @Override
            public boolean include(Entry entry) {
                String name = entry.getValue(0).toString();
                return name.startsWith(searchedItem);
            }
        });
    }

    // MODIFIES: inventoryApp.getMyInventory()
    // EFFECTS : adds new item based on user's input.
    //           If item is already in inventory, displays a message and does nothing.
    //           If the name field is blank, displays a message and does nothing.
    private void doAddItem() {
        getTextForAddItem();
        if (inventoryApp.getMyInventory().itemIsThere(enteredName)) {
            JOptionPane.showMessageDialog(null, "Item already exist.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (enteredName.equals("")) {
            JOptionPane.showMessageDialog(null, "Name should not be blank.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (enteredQuantity < 0) {
            JOptionPane.showMessageDialog(null, "Quantity should be greater than or equals to 0",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (enteredMinStockLimit < 0) {
            JOptionPane.showMessageDialog(null, "Minimum stock limit should be greater than or "
                    + "equals to 0","ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            model.addRow(new Item(enteredName, enteredQuantity, enteredMinStockLimit));
            emptyTextFields();
        }
    }

    // MODIFIES: inventoryApp.getMyInventory()
    // EFFECTS : edits item of selected row based on user's input.
    //           If no row or more than one row is selected, displays a message and does nothing.
    //           If item with the same name exists, displays a message and does nothing.
    //           If quantity or min. stock limit < 0, displays a message and does nothing.
    //           For any blank field(s), no changes are made.
    private void doEditItem(int[] rows) {
        getTextForEditItem(rows);
        if (table.getSelectedRows().length != 1) {
            JOptionPane.showMessageDialog(null, "Please select only one item.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!enteredName.equalsIgnoreCase((String) model.getValueAt(rows[0], 0))
                && inventoryApp.getMyInventory().itemIsThere(enteredName)) {
            JOptionPane.showMessageDialog(null, "Item with the same name already exist.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (enteredQuantity < 0) {
            JOptionPane.showMessageDialog(null, "Quantity should be greater than or equals to 0",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (enteredMinStockLimit < 0) {
            JOptionPane.showMessageDialog(null, "Minimum stock limit should be greater than or "
                    + "equals to 0", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            model.editRow(enteredName, enteredQuantity, enteredMinStockLimit, rows[0]);
            emptyTextFields();
        }
    }

    // MODIFIES: inventoryApp.getMyInventory()
    // EFFECTS : deletes item(s) of selected row(s).
    //           If no row is selected, displays a message.
    private void doDeleteItem(int[] rows) {
        if (table.getSelectedRows().length != 0) {
            model.deleteRows(rows);
        } else {
            JOptionPane.showMessageDialog(null, "Please select the item(s) "
                    + "you would like to delete", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS : collects input from JTextFields.
    //           If the quantity and/or min. stock limit text fields are empty, takes 0 as the value.
    public void getTextForAddItem() {
        enteredName = nameTextField.getText().toUpperCase();

        if (quantityTextField.getText().equals("")) {
            enteredQuantity = 0;
        } else {
            enteredQuantity = Integer.valueOf(quantityTextField.getText());
        }
        if (minStockLimitTextField.getText().equals("")) {
            enteredMinStockLimit = 0;
        } else {
            enteredMinStockLimit = Integer.valueOf(minStockLimitTextField.getText());
        }
    }

    // MODIFIES: this
    // EFFECTS : collects input from JTextFields.
    //           If any of the field is empty,
    private void getTextForEditItem(int[] rows) {
        enteredName = nameTextField.getText().toUpperCase();

        if (enteredName.equals("")) {
            enteredName = (String) model.getValueAt(rows[0], 0);
        }
        if (quantityTextField.getText().equals("")) {
            enteredQuantity = (int) model.getValueAt(rows[0], 1);
        } else {
            enteredQuantity = Integer.valueOf(quantityTextField.getText());
        }
        if (minStockLimitTextField.getText().equals("")) {
            enteredMinStockLimit = (int) model.getValueAt(rows[0], 2);
        } else {
            enteredMinStockLimit = Integer.valueOf(minStockLimitTextField.getText());
        }
    }


    // EFFECT : convert row number after sorted or filtered
    public int[] convertRows(int[] selectedRow) {
        int[] convertedRows = new int[selectedRow.length];
        int iterator = 0;
        for (int i : selectedRow) {
            convertedRows[iterator] = rowSorter.convertRowIndexToModel(i);
            iterator++;
        }
        return convertedRows;
    }

    // MODIFIES: this
    // EFFECTS : clears JTextFields
    public void emptyTextFields() {
        for (JTextField tf : textFields) {
            tf.setText("");
        }
    }
}
