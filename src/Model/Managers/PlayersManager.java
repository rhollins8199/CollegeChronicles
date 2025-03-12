/*
 * PlayerManager Class: Manages player behaviors. 
 */

 package Model.Managers;

 import Model.Player;
 import Model.Room;
 import Model.Item;
 import View.View;

import java.util.HashMap;
import java.util.Map;
 public class PlayersManager {
 
     // Dependencies
     private final Player player;
     private final View view;
     
     // Constructor
     public PlayersManager(Player player) {
         this.player = player;
         this.view = new View();
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

    /**
     * Add an item to the player's inventory
     * @param itemName
     */
    public void addItemToInventory(Item itemName) {
        player.getInventory().add(itemName);
    }

    /**
     * Remove an item from the player's inventory
     * @param itemName
     */
    public void removeItemFromInventory(Item itemName) {
        player.getInventory().remove(itemName);
    }

    /**
     * Check if the player has an item in their inventory
     * @param itemName
     * @return true if the player has the item, false otherwise
     */
    public void deleteItemFromInventory(String itemName) {
        for (Item item : getPlayer().getInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                getPlayer().getInventory().remove(item);
                break;
            }
        }
    }

    // Display the player's inventory
    public void displayInventory() {
        if (player.getInventory().isEmpty()) {
            view.println("\nYour backpack is empty.");
        } else {
            Map<String, Integer> itemCount = new HashMap<>();

            for (Item item : player.getInventory()) {
                itemCount.put(item.getItemName(), itemCount.getOrDefault(item.getItemName(), 0) + 1);
            }

            view.println("\n┌───────────────────────────┐");
            view.println(view.BOLD + "│       BACKPACK ITEMS      │" + view.RESET);
            view.println("├───────────────────────────┤");

            for (Map.Entry<String, Integer> entry : itemCount.entrySet()) {
                System.out.printf("│ %-20s %2dx  │\n", entry.getKey(), entry.getValue());
            }

            view.println("└───────────────────────────┘");
        }
    }

    // Display the player's grades
    public void displayReportCard() {
        String gpaColor;

        if (player.getGpa() >= 3.7) {
            gpaColor = view.GREEN_BG_BLACK_TEXT + view.BOLD; 
        } else if (player.getGpa() >= 2.7) {
            gpaColor = view.YELLOW_BG_BLACK_TEXT + view.BOLD; 
        }
         else {
            gpaColor = view.RED_BG_WHITE_TEXT + view.BOLD;
        }

        view.println("\n┌───────────────────────────┐");
        view.println(view.BOLD + "│        REPORT CARD        │" + view.RESET);
        view.println("├───────────────────────────┤");

        if (player.getGrades().isEmpty()) {
            view.println("│  No grades recorded yet.  │");
        } else {
            for (Map.Entry<String, String> entry : player.getGrades().entrySet()) {
                System.out.printf("│ %-15s : %-3s     │\n\n", entry.getKey(), entry.getValue());
            }
        }

        view.println("├───────────────────────────┤");

        System.out.printf("│ %-15s : %s%-6.2f%s  │\n", "GPA", gpaColor, player.getGpa(), view.RESET);

        view.println("└───────────────────────────┘");
    }

    /**
     * Record a grade for a class
     * @param className
     * @param grade
     */
    public void recordGrade(String className, String grade) {
        if (!player.getGrades().containsKey(className)) {
            player.getGrades().put(className, grade);
        } else {
            // Convert letter to GPA points and average multiple quizzes
            double currentGpaPoints = convertLetterGradeToGpa(player.getGrades().get(className));
            double newGpaPoints = convertLetterGradeToGpa(grade);
            double averagedGpaPoints = (currentGpaPoints + newGpaPoints) / 2;
            player.getGrades().put(className, convertGpaToLetterGrade(averagedGpaPoints));
        }
    }

    /**
     * Convert a letter grade to GPA points
     * @param letterGrade
     * @return
     */
    private double convertLetterGradeToGpa(String letterGrade) {
        switch (letterGrade) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0;
        }
    }

    /**
     * Convert GPA points to a letter grade
     * @param gpaPoints
     * @return
     */
    private String convertGpaToLetterGrade(double gpaPoints) {
        if (gpaPoints >= 3.7)
            return "A";
        if (gpaPoints >= 2.7)
            return "B";
        if (gpaPoints >= 1.7)
            return "C";
        if (gpaPoints >= 0.7)
            return "D";
        return "F";
    }

    public String getLetterGrade(double gradePercentage) {
        if (gradePercentage >= 90) {
            return "A";
        } else if (gradePercentage >= 80) {
            return "B";
        } else if (gradePercentage >= 70) {
            return "C";
        } else if (gradePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Calculate the player's GPA
    public void calculateGpa() {
        double totalGpa = player.getGpa();
        int classCount = 3;

        for (Map.Entry<String, String> entry : player.getGrades().entrySet()) {
            totalGpa += convertLetterGradeToGpa(entry.getValue());
        }

        if (classCount > 0) {
            player.setGpa(totalGpa / classCount);
        }
    }
 
 }
 