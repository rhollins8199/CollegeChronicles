/*
 * Player Class: Responsible for initializing and managing player attributes, and behaviors. 
 */

 package Model;
 
 public class Player {
 
     // Player attributes
     private String playerName;
     private Room currentRoom;
     
     // Room constructor
     public Player(String playerName, Room currentRoom) {
         this.playerName = playerName;
         this.currentRoom = currentRoom;
     }
 
     // Getters
     public String getPlayerName() { return playerName; }
     public Room getCurrentRoom() { return currentRoom; }
 
     // Setters
     public void setPlayerName(String playerName) { this.playerName = playerName; }
     public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom;}
 
     // toString method (for debugging)
     @Override
     public String toString() {
         return "PLAYER{" +
                 "\nName: " + playerName + 
                 "\nCurrent Room: " + currentRoom.getRoomName() +
                 '}';
     }
 
 }
 