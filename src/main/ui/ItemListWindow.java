package ui;

import model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemListWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 200;
    private static final int NUM_COLS = 3;

    private String searchedItem;

    private InventoryAppGUI sia;

    private JLabel myInventoryLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton editItemButton;
    private JButton deleteItemButton;

    private JTable table;
    private JScrollPane scrollPane;
    private String[] cols;
    private Object[][] data;

    private JPanel searchPanel;

    private DefaultTableModel model;
    private TableRowSorter rowSorter;

    ItemListWindow(InventoryAppGUI sia) {
        this.sia = sia;
        myInventoryLabel = new JLabel("My Inventory:");
        searchTextField = new JTextField();
        searchButton = new JButton("Search");
        searchPanel = new JPanel();

        editItemButton = new JButton("Edit Item");
        deleteItemButton = new JButton("Delete Item");


        searchTextField.addActionListener(this);
        searchTextField.setPreferredSize(new Dimension(200, 20));

        searchPanel.setLayout(new FlowLayout());

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        //setLayout(null);

        //add(myInventoryLabel);

        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchTextField.addActionListener(this);


        add(searchPanel);

        cols = new String[]{"Item", "Quantity", "Min. Stock Limit"};
        data = new String[sia.getMyInventory().getItemList().size()][NUM_COLS];
        data = getData();

        model = new DefaultTableModel(data, cols);
        table = new JTable(model);
        rowSorter = new TableRowSorter(model);
        table.setRowSorter(rowSorter);

//        rowSorter.setRowFilter(new RowFilter() {
//            @Override
//            public boolean include(Entry entry) {
//                String name = entry.getValue(0).toString();
//                return name.contentEquals(searchedItem);
//            }
//        });



        scrollPane = new JScrollPane(table);
        add(scrollPane);





        setVisible(true);
//
//        this.sia = sia;
//        myInventoryLabel = new JLabel("My Inventory:");
//
//
//        setSize(WIDTH, HEIGHT);
//        setVisible(true);
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        add(myInventoryLabel);
//
//        cols = new String[]{"Item", "Quantity", "Min. Stock Limit"};
//        data = new String[0][NUM_COLS];
//
//
//        model = new DefaultTableModel(data, cols);
//        table = new JTable(model);
//        scrollPane = new JScrollPane(table);
//        add(scrollPane);
//
//        setVisible(true);

    }

    private Object[][] getData() {
        int row = 0;


        for (Item i : sia.getMyInventory().getItemList()) {
            data[row][0] = (i.getName());
            data[row][1] = String.valueOf(i.getQuantity());
            data[row][2] = String.valueOf(i.getMinimumStockLimit());
            row++;

        }
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchedItem = searchTextField.getText().toUpperCase();
        }
        searchedItem = searchTextField.getText().toUpperCase();

        rowSorter.setRowFilter(new RowFilter() {
            @Override
            public boolean include(Entry entry) {
                String name = entry.getValue(0).toString();
                return name.startsWith(searchedItem);
            }
        });
    }

//    public void refresh() {
//
//        data = new String[sia.getMyInventory().getItemList().size()][NUM_COLS];
//        data = getData();
//        model = new DefaultTableModel(data, cols);
//        table = new JTable(model);
//        scrollPane = new JScrollPane(table);
//        add(scrollPane);
//
//        setVisible(true);
//        setOpaque(true);
//
//    }

}
