/*
 * Room Class: Responsible for initializing and managing room attributes. 
 */

 package Model;

 public class Room {
     
     // Room attributes
     private final int roomId;
     private boolean hasVisited;
     private final String roomName;
     private final String roomDescription;
     private final int northExit;
     private final int eastExit;
     private final int southExit;
     private final int westExit;
 
 
     // Room constructor
     public Room(int roomId, boolean hasVisited, String roomName, String roomDescription, int northExit, int eastExit, int southExit, int westExit) {
         this.roomId = roomId;
         this.hasVisited = hasVisited;
         this.roomName = roomName;
         this.roomDescription = roomDescription;
         this.northExit = northExit;
         this.eastExit = eastExit;
         this.southExit = southExit;
         this.westExit = westExit;
     }
 
     // Getters
     public int getRoomId() { return roomId; }    
     public boolean getHasVisited() { return hasVisited; }
     public String getRoomName() { return roomName; }
     public String getRoomDescription() { return roomDescription; }
     public int getNorthExit() { return northExit; }
     public int getEastExit() { return eastExit; }
     public int getSouthExit() { return southExit; }
     public int getWestExit() { return westExit; }
 
     // Setters
     public void setHasVisited(boolean hasVisited) { this.hasVisited = hasVisited; }
 
     // toString method (for debugging)
     @Override
     public String toString() {
         return "\n\n---------------------ROOM DATA----------------------" +
                 "\nId: " + roomId +
                 "\nName: " + roomName +
                 "\nHas Visited:? " + hasVisited +
                 "\nDescription: " + roomDescription +
                 "\nNorth Exit= " + northExit +
                 "\nEast Exit= " + eastExit +
                 "\nSouth Exit= " + southExit +
                 "\nWest Exit=" + westExit +
                 '}';
     }
 }
 