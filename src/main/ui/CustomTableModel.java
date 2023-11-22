package ui;

import model.Inventory;

import javax.swing.table.AbstractTableModel;

// Represents a table model
public abstract class CustomTableModel extends AbstractTableModel {
    private String[] cols;
    protected Inventory items;

    // EFFECTS : initializes fields
    public CustomTableModel(Inventory items) {
        this.items = items;
        cols = new String[]{"Item", "Quantity", "Min. Stock Limit"};
    }


    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    @Override
    public abstract int getRowCount();

    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);

}
