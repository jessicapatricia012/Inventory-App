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
    inventory.
- You can generate the second required action related to the user story "adding multiple Xs to a Y", which is searching
    for items based on user's input, by clicking on "All Items" then enter the name of the item and press Enter or click
    on the "Search" button.
- You can locate my visual component (JTables) by clicking on "All Items" or "Low in Stock".
- You can save the state of my application by clicking "File" on the menu bar then click "Save", or Alt+F then S
- You can reload the state of my application by clicking "File" on the menu bar then click "Load", or Alt+F then L