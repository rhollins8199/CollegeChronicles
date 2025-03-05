/*
 * Item Class: Responsible for initializing and managing item attributes. 
 */

 package Model;

 public class Item {
     
     // Item attributes
     private int itemId;
     private final String itemName;
     private final String itemDescription;
     private int itemCount;
 
     // Item constructor
     public Item(int newItemId, String newItemName, String newItemDescription, int newItemCount){
         this.itemId = newItemId;
         this.itemName = newItemName;
         this.itemDescription = newItemDescription;
         this.itemCount = newItemCount;
     }
 
     // Getters
     public int getItemId() { return itemId; }
     public String getItemName() { return itemName; }
     public String getItemDescription() { return itemDescription; }
     public int getItemCount() { return itemCount; }
 
     // Setters
     public void setId(int itemId) { this.itemId = itemId; }
     public void setItemCount(int itemCount) { this.itemCount = itemCount;}
 
     // toString method (for debugging)
     @Override
     public String toString() {
         return "ITEM{" +
                 "\nRoom Location: " + itemId +
                 "\nItem Name: " + itemName +
                 "\nItem Description: " + itemDescription +
                 "\nItem Count: " + itemCount +
                 '}';
     }
   
 }