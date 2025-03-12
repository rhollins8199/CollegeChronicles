/*
 * RoomsManager Class: Manages room behaviors. 
 */

 package Model.Managers;

 import Model.Room;
 
 import java.util.List;
 
 public class RoomsManager {
 
     // Dependencies
     private final List<Room> rooms;
 
     // Constructor
     public RoomsManager(List<Room> rooms) {
         this.rooms = rooms;
     }
 
     // To Access Getters
     public Room getRooms(int roomId) {
         return rooms.get(roomId);
     }
 
     /**
      * Get the next room id based on user input
      * @param roomId
      * @param userInput
      * @return nextRoomId
      */
     public int getRoomExitId(int roomId, String direction) {
         int nextRoomId = 0;
         Room currentRoom = findRoomById(roomId);
 
         switch (direction) {
             case "n":
             case "north":
                 nextRoomId = currentRoom.getNorthExit();
                 break;
             case "e":
             case "east":
                 nextRoomId = currentRoom.getEastExit();
                 break;
             case "s":
             case "south":
                 nextRoomId = currentRoom.getSouthExit();
                 break;
             case "w":
             case "west":
                 nextRoomId = currentRoom.getWestExit();
                 break;
         }
                 return nextRoomId;
     }
 
     /**
      * Find a room by its id in the rooms list
      * @param roomId
      * @return room
      */
     public Room findRoomById(int roomId) {
         return rooms.stream()
                 .filter(room -> room.getRoomId() == roomId)
                 .findFirst()
                 .orElse(null);
     }
 
 }
 