package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AllItemsWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 200;

    private String searchedItem;

    private InventoryAppGUI sia;

    private JTextField searchTextField;
    private List<JButton> buttons;
    private JButton searchButton;
    private JButton editItemButton;
    private JButton deleteItemButton;

    private ItemTable allItemsTable;

    private JScrollPane scrollPane;

    private JPanel searchPanel;


    AllItemsWindow(InventoryAppGUI inventoryApp) {
        super("Item List");
        this.sia = inventoryApp;
        searchTextField = new JTextField();
        searchPanel = new JPanel();

        buttons = new ArrayList<>();
        searchButton = new JButton("Search");
        editItemButton = new JButton("Edit Item");
        deleteItemButton = new JButton("Delete Item");

        allItemsTable = new AllItemsTable(inventoryApp);
        scrollPane = new JScrollPane(allItemsTable.getJTable());

        setUp();
    }

    public void setUp() {
        setSize(WIDTH, HEIGHT);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));



        setUpSearchPanel();
        add(searchPanel);
        add(scrollPane);

        setUpButtons();
//        for (JButton b : buttons) {
//            add(b);
//        }


        setVisible(true);

    }

    public void setUpSearchPanel() {
        searchTextField.addActionListener(this);
        searchTextField.setPreferredSize(new Dimension(200, 20));

        searchPanel.setLayout(new FlowLayout());

        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchTextField.addActionListener(this);

    }

    public void setUpButtons() {
        buttons.add(searchButton);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchedItem = searchTextField.getText().toUpperCase();
        }
        searchedItem = searchTextField.getText().toUpperCase();

        allItemsTable.getRowSorter().setRowFilter(new RowFilter() {
            @Override
            public boolean include(Entry entry) {
                String name = entry.getValue(0).toString();
                return name.startsWith(searchedItem);
            }
        });
    }
}
