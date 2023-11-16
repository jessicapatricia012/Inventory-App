package ui;

import model.Item;

public class LowStockItemsTable extends ItemTable {

    LowStockItemsTable(InventoryAppGUI inventoryApp) {
        super(inventoryApp);
    }

    @Override
    public int getNumRows() {
        return inventoryApp.getMyInventory().getLowStockItems().size();
    }

    @Override
    public Object[][] getData() {
        int row = 0;
        for (Item i : inventoryApp.getMyInventory().getLowStockItems()) {
            data[row][0] = (i.getName());
            data[row][1] = String.valueOf(i.getQuantity());
            data[row][2] = String.valueOf(i.getMinimumStockLimit());
            row++;
        }
        return data;
    }
}
