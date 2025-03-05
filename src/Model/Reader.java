/*
 * Reader Class (DAO Class): Responsible for reading and parsing text files (game data) and returning arraylists.
*/

package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Reader {

    /**
     * Logs an error message to the console.
     * @param errorType The type of error (e.g. "Error reading file").
     * @param message The error message.
    */
    private void logError(String errorType, String message) {
        System.err.println(errorType + ": " + message);
    }

    /**
     * Reads and parses rooms data from 'rooms.txt'.
     * @return ArrayList of Room objects.
     */
     public ArrayList<Room> loadRoomsFromFile() {
        Room room;
        ArrayList<Room> roomsList = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader("data/rooms.txt"))) {
    
            // Skip the first two lines (headers or non-room data).
            for (int i = 0; i < 2; i++) { reader.readLine(); }
    
            // Read the rest of the file line by line.
            String line;
            while ((line = reader.readLine()) != null) {
                String[] field = line.split("\\|"); // Split the line into fields.

                // Parse the fields.
                int roomId = Integer.parseInt(field[0]);
                boolean hasVisited = Boolean.parseBoolean(field[1]);
                String roomName = field[2];
                String roomDescription = field[3];
    
                // Parse the exit IDs.
                String[] exitIds = field[4].split("-"); // Split the exit IDs.
                int northExit = Integer.parseInt(exitIds[0]);
                int eastExit = Integer.parseInt(exitIds[1]);
                int southExit = Integer.parseInt(exitIds[2]);
                int westExit = Integer.parseInt(exitIds[3]);
    
                // Create a new Room object and add it to the list.
                room = new Room(roomId, hasVisited, roomName, roomDescription, northExit, eastExit, southExit, westExit); 
                roomsList.add(room);
            }
    
            roomsList.sort(Comparator.comparingInt(Room::getRoomId));
    
          // For debugging
        } catch (IOException e) {
            logError("Error reading 'rooms.txt'", e.getMessage());
        } catch (NumberFormatException e) {
            logError("Error parsing data", e.getMessage());
        }
    
        return roomsList;
    }

      /**
     * Reads and parses items data from 'items.txt'.
     *
     * @return ArrayList of Item objects.
     * 
     **/
    public ArrayList<Item> loadItemsFromFile() {
        Item item;
        ArrayList<Item> itemsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("data/items.txt"))) {

            // Skip the first two lines.
            for (int i = 0; i < 2; i++) { reader.readLine(); }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] field = line.split("\\|");

                int roomId = Integer.parseInt(field[0]);
                String itemName = field[1];
                String itemDescription = field[2];
                int itemCount = Integer.parseInt(field[3]);

                // Create a new Item object and add it to the list.
                item = new Item(roomId, itemName, itemDescription, itemCount);
                itemsList.add(item);
            }

            itemsList.sort(Comparator.comparingInt(Item::getItemId));

        } catch (IOException e) {
            System.err.println("Error reading 'items.txt': " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: Input should be a valid string. " + e.getMessage());
        }

        return itemsList;
    }

      /**
     * Reads and parses puzzles data from 'puzzles.txt'.
     *
     * @return ArrayList of Puzzle objects.
     * 
     **/
    public ArrayList<Puzzle> loadPuzzlesFromFile() {
        ArrayList<Puzzle> puzzlesList = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader("data/puzzles.txt"))) {
    
            // Skip the first two lines.
            for (int i = 0; i < 2; i++) { reader.readLine(); } 
    
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines.
                
                String[] field = line.split("\\|");
    
                int roomID = Integer.parseInt(field[0].trim());
                String puzzleType = field[1].trim();
                String question = field[2].trim();
                String optionA = field[3].trim();
                String optionB = field[4].trim();
                String optionC = field[5].trim();
                String optionD = field[6].trim();
                String correctAnswer = field[7].trim();
                boolean isSolved = Boolean.parseBoolean(field[8].trim());
                
                // Create a new Puzzle object and add it to the list.
                Puzzle puzzle = new Puzzle(roomID, puzzleType, question, optionA, optionB, optionC, optionD, correctAnswer, isSolved);
                puzzlesList.add(puzzle);
            }
    
            puzzlesList.sort(Comparator.comparingInt(Puzzle::getRoomId));
    
        } catch (IOException e) {
            System.err.println("Error reading 'puzzles.txt': " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: Input should be a valid string. " + e.getMessage());
        }
    
        return puzzlesList;
    }
}
