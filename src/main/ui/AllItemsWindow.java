package ui;

import model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AllItemsWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private String searchedItem;
    private String enteredName;
    private int enteredQuantity;
    private int enteredMinStockLimit;

    private InventoryAppGUI inventoryApp;
    private List<Item> items;

    private List<JLabel> labels;
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


    private ItemTable allItemsTable;
    private JScrollPane scrollPane;
    private JPanel searchPanel;
    private JPanel textFieldPanel;
    private JPanel buttonsPanel;


    AllItemsWindow(InventoryAppGUI inventoryApp) {
        super("Item List");
        this.inventoryApp = inventoryApp;
        items = inventoryApp.getMyInventory().getItemList();

        searchPanel = new JPanel(new FlowLayout());
        textFieldPanel = new JPanel(new FlowLayout());
        buttonsPanel = new JPanel(new FlowLayout());


        textFields = new ArrayList<>();
        searchTextField = new JTextField();
        nameTextField = new JTextField();
        quantityTextField = new JTextField();
        minStockLimitTextField = new JTextField();

        buttons = new ArrayList<>();
        searchButton = new JButton("Search");
        addItemButton = new JButton("Add Item");
        editItemButton = new JButton("Edit Item");
        deleteItemButton = new JButton("Delete Item");

        labels = new ArrayList<>();
        nameLabel = new JLabel("Name:");
        quantityLabel = new JLabel("Quantity");
        minStockLimitLabel = new JLabel("Min. Stock Limit:");


        allItemsTable = new ItemTable(inventoryApp);
        scrollPane = new JScrollPane(allItemsTable.getJTable());

        setUp();
    }

    public void setUp() {
        setSize(WIDTH, HEIGHT);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        setUpButtons();
        setUpTextField();

        setUpSearchPanel();
        add(searchPanel);

        add(scrollPane);

        setUpTextFieldsPanel();
        add(textFieldPanel);

        setUpButtonsPanel();
        add(buttonsPanel);

        pack();
        setVisible(true);
    }

    public void setUpSearchPanel() {
        searchTextField.addActionListener(this);
        searchTextField.setPreferredSize(new Dimension(200, 20));

        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchTextField.addActionListener(this);

    }

    public void setUpTextFieldsPanel() {
        textFieldPanel.add(nameLabel);
        textFieldPanel.add(nameTextField);
        textFieldPanel.add(quantityLabel);
        textFieldPanel.add(quantityTextField);
        textFieldPanel.add(minStockLimitLabel);
        textFieldPanel.add(minStockLimitTextField);


    }

    public void setUpButtonsPanel() {
        buttonsPanel.add(addItemButton);
        buttonsPanel.add(editItemButton);
        buttonsPanel.add(deleteItemButton);
    }

    public void setUpButtons() {
        buttons.add(searchButton);
        buttons.add(addItemButton);
        buttons.add(editItemButton);
        buttons.add(deleteItemButton);

//        itemListButton.setSize(400, 50);
//        receiveItemsButton.setBounds(200, 190, 400, 50);
//        shipItemsOutButton.setBounds(200, 250, 400, 50);
//        lowInStockButton.setBounds(200, 400, 400, 50);

        for (JButton b : buttons) {
            b.setSize(new Dimension(400, 20));
            b.setFocusable(false);
            b.addActionListener(this);
        }
    }

    public void setUpTextField() {
        textFields.add(nameTextField);
        textFields.add(quantityTextField);
        textFields.add(minStockLimitTextField);

        for (JTextField tf : textFields) {
            tf.addActionListener(this);
            tf.setPreferredSize(new Dimension(100, 20));
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int[] rows = convertRows(allItemsTable.getJTable().getSelectedRows());
//        if (rows.length == 1) {
//            editItemButton.setEnabled(true);
//        } else if (rows.length > 1) {
//            editItemButton.setEnabled(true);
//            deleteItemButton.setEnabled(true);
//        }

        collectInputFromTextFields();

        if (e.getSource() == searchButton) {
            searchedItem = searchTextField.getText().toUpperCase();
        } else if (e.getSource() == addItemButton) {
            doAddItem();
        } else if (e.getSource() == editItemButton) {
            doEditItem(rows);
        } else if (e.getSource() == deleteItemButton) {
            doDeleteItem(rows);
        }

        allItemsTable.getRowSorter().setRowFilter(new RowFilter() {
            @Override
            public boolean include(Entry entry) {
                String name = entry.getValue(0).toString();
                return name.startsWith(searchedItem);
            }
        });
    }

    private void doAddItem() {
        if (inventoryApp.getMyInventory().itemIsThere(enteredName)) {
            JOptionPane.showMessageDialog(null, "Item already exist.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (enteredName.equals("")) {
            JOptionPane.showMessageDialog(null, "Name should not be blank.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            allItemsTable.getModel().addRow(new Item(enteredName, enteredQuantity, enteredMinStockLimit));
        }
    }

    private void doEditItem(int[] rows) {
        if (allItemsTable.getJTable().getSelectedRows().length != 1) {
            JOptionPane.showMessageDialog(null, "Please select one item you would like to edit.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!enteredName.equalsIgnoreCase((String) allItemsTable.getModel().getValueAt(rows[0], 0))
                && inventoryApp.getMyInventory().itemIsThere(enteredName)) {
            JOptionPane.showMessageDialog(null, "Item with the same name already exist.",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (enteredName.equals("")) {
                enteredName = (String) allItemsTable.getModel().getValueAt(rows[0], 0);
            }
            if (enteredQuantity == 0) {
                enteredQuantity = (int) allItemsTable.getModel().getValueAt(rows[0], 1);
            }
            if (enteredMinStockLimit == 0) {
                enteredMinStockLimit = (int) allItemsTable.getModel().getValueAt(rows[0], 2);
            }
            allItemsTable.getModel().editRow(enteredName, enteredQuantity, enteredMinStockLimit, rows[0]);
            for (JTextField tf : textFields) {
                tf.setText("");
            }
        }
    }

    private void doDeleteItem(int[] rows) {
        if (allItemsTable.getJTable().getSelectedRows().length != 0) {
            allItemsTable.getModel().deleteRows(rows);
        } else {
            JOptionPane.showMessageDialog(null, "Please select the item(s) "
                    + "you would like to delete", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void collectInputFromTextFields() {
        searchedItem = searchTextField.getText().toUpperCase();
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

    public int[] convertRows(int[] selectedRow) {
        int[] convertedRows = new int[selectedRow.length];
        int iterator = 0;
        for (int i : selectedRow) {
            convertedRows[iterator] = allItemsTable.getRowSorter().convertRowIndexToView(i);
            iterator++;
        }
        return convertedRows;
    }
}
