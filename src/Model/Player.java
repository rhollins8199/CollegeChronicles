/*
 * Player Class: Responsible for initializing and managing player attributes, and behaviors. 
 */

 package Model;

import java.util.ArrayList;

public class Player {
 
     // Player attributes
     private String playerName;
     private Room currentRoom;
     private final ArrayList<Room> previousRoomsList = new ArrayList<>();
     
     // Room constructor
     public Player(String playerName, Room currentRoom) {
         this.playerName = playerName;
         this.currentRoom = currentRoom;
     }
 
     // Getters
     public String getPlayerName() { return playerName; }
     public Room getCurrentRoom() { return currentRoom; }
     public ArrayList<Room> getPreviousRoomsList() { return previousRoomsList; }
 
     // Setters
     public void setPlayerName(String playerName) { this.playerName = playerName; }
     public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom;}
 
     // toString method (for debugging)
     @Override
     public String toString() {
         return "PLAYER{" +
                 "\nName: " + playerName + 
                 "\nCurrent Room: " + currentRoom.getRoomName() +
                 "\nPrevious Rooms: " + previousRoomsList +
                 '}';
     }
 
 }
 