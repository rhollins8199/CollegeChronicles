/*
 * Player Class: Responsible for initializing and managing player attributes, and behaviors. 
 */

 package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
 
     // Player attributes
     private String playerName;
     private Room currentRoom;
     private final ArrayList<Room> previousRoomsList = new ArrayList<>();
     private final List<Item> inventory;
    private Map<String, String> grades;
    private double gpa;
     
     // Room constructor
     public Player(String playerName, Room currentRoom) {
         this.playerName = playerName;
         this.currentRoom = currentRoom;
         this.inventory = new ArrayList<>();
        this.grades = new HashMap<>();
        this.gpa = 1.0;
     }
 
     // Getters
     public String getPlayerName() { return playerName; }
     public Room getCurrentRoom() { return currentRoom; }
     public ArrayList<Room> getPreviousRoomsList() { return previousRoomsList; }
     public List<Item> getInventory() { return inventory; }
     public Map<String, String> getGrades() { return grades; }
     public double getGpa() { return gpa; }
 
     // Setters
     public void setPlayerName(String playerName) { this.playerName = playerName; }
     public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom;}
     public void setGpa(double gpa) { this.gpa = gpa; }
 
     // toString method (for debugging)
     @Override
     public String toString() {
         return "PLAYER{" +
                 "\nName: " + playerName + 
                 "\nCurrent Room: " + currentRoom.getRoomName() +
                 "\nPrevious Rooms: " + previousRoomsList +
                 "\nInventory: " + inventory +
                 "\nGrades: " + grades +
                 "\nGPA: " + gpa +
                 '}';
     }
 
 }
 