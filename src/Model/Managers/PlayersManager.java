/*
 * PlayerManager Class: Manages player behaviors. 
 */

 package Model.Managers;

 import Model.Player;

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
 
 }
 