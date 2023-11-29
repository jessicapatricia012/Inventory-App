# My Personal Project: Storage Inventory Tracker

I am designing an application that keeps track of storage inventory items. The app will let the user know when an item
is low in stock, and the user will be able to set the limit. The user will also be able to register items going in
or out of the inventory.

This program will be useful for storage or warehouse owner, who wants to be able to update their inventory everytime
an item is received or shipped out of the storage/warehouse.

## User Story
-  As a user, I want to be able to view the list of all items I have in my inventory
-  As a user, I want to be able to add or remove an item from my inventory
-  As a user, I want to be able to update the stock of my items everytime an item is received by or sent out from the
   storage
-  As a user, I want to be able to view the information related to my item and edit it
-  As a user, I want to be able to be able to check the list of items that are low in stock
-  As a user, I want to be able to be informed when an item is low in stock after its quantity is updated.
-  As a user, I want to be informed when a new item that is not yet registered in the inventory is received, and
   register that new item.
-  As a user, I want to be informed when an item does not have enough stock to be shipped out.
-  As a user, I want to have the options to save the list of items I have in my inventory.
-  As a user, I want to be able to load my inventory from file

# Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y", which is adding
  new items to the inventory, by clicking on "Receive Item" then enter a new item that is not yet registered in the
  inventory, or clicking on "All Items" then add items manually by filling the text fields and click "Add Item".
- You can generate the second required action related to the user story "adding multiple Xs to a Y", which is deleting
  items, by clicking on "All Items" then select the row(s) on item(s) and click "Delete Item".
- You can locate my visual component (ImageIcons) by clicking on "All Items" and you will see ImageIcons on the JButtons
- You can save the state of my application by clicking "File" on the menu bar then click "Save", or Alt+F then S
- You can reload the state of my application by clicking "File" on the menu bar then click "Load", or Alt+F then L

# Phase 4: Task 2
Tue Nov 28 21:30:24 PST 2023
New item A added.

Tue Nov 28 21:30:27 PST 2023
New item B added.

Tue Nov 28 21:30:29 PST 2023
Item B removed.

Tue Nov 28 21:30:31 PST 2023
Saved to file.

# Phase 4: Task 3
The first thing that bugs me is the presence of AllItemsWindow and LowStockWindow. They are two separate windows from 
the main window, InventoryAppGUI. AllItemsWindow displays a table of all items, while LowStockWindow displays a table 
of items that are low in stock, which is a subset of all items. I would like to combine those tables and mark the rows 
of items that are low in stock in red so that I do not have to have a separate view of low-stock items.
 
Having one separate window to view the items is good enough, but I would love to combine that window with the main
JFrame, i.e. having another JPanel added to InventoryAppGUI instead of creating a new window. This is what I tried to
do when I was just getting started with Phase 3, and though the panel was there, the table did not show up somehow. Now
that I have become more familiar with Java Swing, that is what I would love to implement to simplify the view of my
program.



