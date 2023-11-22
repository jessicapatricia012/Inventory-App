package ui;

import model.Inventory;
import model.Item;

// Represents a table model for items that are low in stock
public class LowStockCustomTableModel extends CustomTableModel {

    // EFFECTS : initializes field
    public LowStockCustomTableModel(Inventory items) {
        super(items);
    }

    // EFFECTS : returns number of rows or number of low stock items
    @Override
    public int getRowCount() {
        return items.getLowStockItems().size();
    }

    // EFFECTS : returns Object contained in the cell that corresponds to rowIndex and columnIndex
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

}