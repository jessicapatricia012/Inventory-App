package ui;

import model.Inventory;
import model.Item;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

public class LowStockItemsTable {

    protected InventoryAppGUI inventoryApp;

    private JTable table;
    private JScrollPane scrollPane;

    private LowStockCustomModel model;
    private TableRowSorter rowSorter;

    LowStockItemsTable(InventoryAppGUI inventoryApp) {
        this.inventoryApp = inventoryApp;

        model = new LowStockCustomModel(inventoryApp.getMyInventory());
        table = new JTable(model);
        rowSorter = new TableRowSorter(model);
        table.setRowSorter(rowSorter);

        for (int i = 0; i < inventoryApp.getMyInventory().getLowStockItems().size(); i++) {
            table.getRowSorter().convertRowIndexToModel(i);
        }

        scrollPane = new JScrollPane(table);
    }

    public JTable getJTable() {
        return table;
    }

    public TableRowSorter getRowSorter() {
        return rowSorter;
    }

    public LowStockCustomModel getModel() {
        return model;
    }


    public class LowStockCustomModel extends AbstractTableModel {
        private String[] cols;
        private Inventory items;

        public LowStockCustomModel(Inventory items) {
            this.items = items;
            cols = new String[]{"Item", "Quantity", "Min. Stock Limit"};
        }

        @Override
        public int getRowCount() {
            return items.getLowStockItems().size();
        }

        @Override
        public int getColumnCount() {
            return cols.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Item item = items.getLowStockItems().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return item.getName();
                case 1:
                    return item.getQuantity();
                case 2:
                    return item.getMinimumStockLimit();
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return cols[column];
        }
    }


}
