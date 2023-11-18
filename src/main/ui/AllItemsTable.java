//package ui;
//
//import model.Inventory;
//import model.Item;
//
//import javax.swing.table.AbstractTableModel;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AllItemsTable extends ItemTable {
//
//    AllItemsTable(InventoryAppGUI inventoryApp) {
//        super(inventoryApp);
//    }
//
//
//    public static class CustomModelAllItems extends CustomModel {
//        private String[] cols;
//        private Inventory items;
//
//        public CustomModelAllItems(Inventory items) {
//            super(items);
//        }
//
//        @Override
//        public int getRowCount() {
//            return items.getItemList().size();
//        }
//
//        @Override
//        public int getColumnCount() {
//            return cols.length;
//        }
//
//        @Override
//        public Object getValueAt(int rowIndex, int columnIndex) {
//            Item item = items.getItemList().get(rowIndex);
//            switch (columnIndex) {
//                case 0:
//                    return item.getName();
//                case 1:
//                    return item.getQuantity();
//                case 2:
//                    return item.getMinimumStockLimit();
//            }
//            return null;
//        }
//
//        @Override
//        public String getColumnName(int column) {
//            return cols[column];
//        }
//
//        public void addRow(Item item) {
//            items.getItemList().add(item);
//            int row = items.getItemList().indexOf(item);
//            fireTableRowsInserted(row, row);
//        }
//
//        public void editRow(String name, int quantity, int minStockLimit, int row) {
//            items.getItemList().get(row).setName(name);
//            items.getItemList().get(row).setQuantity(quantity);
//            items.getItemList().get(row).setMinimumStockLimit(minStockLimit);
//        }
//
//        public void deleteRows(int[] rows) {
//            List<Item> removedItems = new ArrayList<>();
//            for (int i : rows) {
//                removedItems.add(items.getItemList().get(i));
//            }
//            items.getItemList().removeAll(removedItems);
//            //fireTableRowsDeleted(rows[0], rows[rows.length - 1]);
//        }
//    }
//
//
//}
