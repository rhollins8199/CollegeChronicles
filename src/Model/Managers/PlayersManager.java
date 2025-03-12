/*
 * PlayerManager Class: Manages player behaviors. 
 */

 package Model.Managers;

 import Model.Player;
 import Model.Room;
 public class PlayersManager {
 
     // Dependencies
     private final Player player;
     
     // Constructor
     public PlayersManager(Player player) {
         this.player = player;
     }
 
     // To Access Getters
     public Player getPlayer() {
         return player;
     }
 
     /**
      * Set the player's name
      * @param name
      */
     public void setPlayerName(String playerName) {
         player.setPlayerName(playerName);
     }

     /**
     * Set the player's current room
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        if (player.getCurrentRoom() != null) {
            player.getPreviousRoomsList().add(player.getCurrentRoom());
            player.getCurrentRoom().setHasVisited(true);
        }

        player.setCurrentRoom(currentRoom);
    }

    /**
     * Indicate if the player has visited a room
     * @param nextRoom
     * @return true if the player has visited the room, false otherwise
     */
    public boolean indicateNewRoom(Room nextRoom) {
        boolean printedMessage = false;

        for (Room room : getPlayer().getPreviousRoomsList()) {
            if (room.getRoomName().equals(nextRoom.getRoomName())) {
                if (!printedMessage) {
                    printedMessage = true;
                }
                break;
            }
        }

        if (!printedMessage) {
            printedMessage = false;
        }

        return printedMessage;

    }
 
 }
 