package ui;

import model.Inventory;
import model.Item;

import java.util.ArrayList;
import java.util.List;

// Represents a table model for all items in the inventory
public class AllItemsCustomTableModel extends CustomTableModel {

    // EFFECTS: initializes field
    public AllItemsCustomTableModel(Inventory items) {
        super(items);
        items.createDisplayLog();
    }

    // EFFECTS : returns number of rows or number of all items
    @Override
    public int getRowCount() {
        return items.getItemList().size();
    }

    // REQUIRES: (rowIndex >= 0) && (columnIndex >= 0)
    // EFFECTS : returns Object contained in the cell that corresponds to rowIndex and columnIndex
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = items.getItemList().get(rowIndex);
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

    // REQUIRES: item is not in inventory
    // MODIFIES: this
    // EFFECTS : adds a new row with a new item
    public void addRow(Item item) {
        items.addItem(item.getName());
        int row = items.getItemList().indexOf(item);
        fireTableRowsInserted(row, row);
    }

    // REQUIRES: name is not an empty string
    // MODIFIES: items.getItemList().get(row)
    // EFFECTS : edits row
    public void editRow(String name, int quantity, int minStockLimit, int row) {
        items.getItemList().get(row).setName(name);
        items.getItemList().get(row).setQuantity(quantity);
        items.getItemList().get(row).setMinimumStockLimit(minStockLimit);
    }

    // MODIFIES: this
    // EFFECTS : deletes rows
    public void deleteRows(int[] rows) {
        List<Item> removedItems = new ArrayList<>();
        for (int i : rows) {
            removedItems.add(items.getItemList().get(i));
        }
        for (Item i : removedItems) {
            items.removeItem(i.getName());
        }
        //fireTableRowsDeleted(rows[0], rows[rows.length - 1]);
    }
}
