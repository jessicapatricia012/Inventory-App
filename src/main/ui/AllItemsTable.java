package ui;

import model.Item;

public class AllItemsTable extends ItemTable {

    AllItemsTable(InventoryAppGUI inventoryApp) {
        super(inventoryApp);
    }

    @Override
    public int getNumRows() {
        return inventoryApp.getMyInventory().getItemList().size();
    }

    @Override
    public Object[][] getData() {
        int row = 0;
        for (Item i : inventoryApp.getMyInventory().getItemList()) {
            data[row][0] = (i.getName());
            data[row][1] = String.valueOf(i.getQuantity());
            data[row][2] = String.valueOf(i.getMinimumStockLimit());
            row++;
        }
        return data;
    }
}
