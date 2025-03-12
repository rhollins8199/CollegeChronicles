/*
 * PuzzlesManager Class: Manages puzzle behaviors. 
 */

 package Model.Managers;

 import Model.Puzzle;
 
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
  
  public class PuzzlesManager {
  
     // Dependencies
      private final List<Puzzle> puzzles;
      private static final int REQUIRED_SCANTRONS_FOR_QUIZ = 1;
      private static final int REQUIRED_SCANTRONS_FOR_EXAM = 2;
  
      // Constructor
      public PuzzlesManager(List<Puzzle> puzzles) {
          this.puzzles = puzzles;
          assignPuzzlesToRooms();
      }
  
      // To Access Getters
      public List<Puzzle> getPuzzles() { 
         return puzzles; 
      }
  
      // Assign puzzles to rooms
      public void assignPuzzlesToRooms() {
          Map<Integer, List<Puzzle>> roomToPuzzlesMap = new HashMap<>();
  
          for (Puzzle puzzle : puzzles) {
              int roomId = puzzle.getRoomId();
              
              if (!roomToPuzzlesMap.containsKey(roomId)) {
                  roomToPuzzlesMap.put(roomId, new ArrayList<>());
              }
              roomToPuzzlesMap.get(roomId).add(puzzle);
          }
      }
  
      /**
       * Get all puzzles in a room.
       * @param roomId
       * @return List of puzzles in the room.
       */
      public List<Puzzle> getPuzzlesByRoomId(int roomId) {
          List<Puzzle> puzzlesInRoom = new ArrayList<>();
          for (Puzzle puzzle : puzzles) {
              if (puzzle.getRoomId() == roomId) {
                  puzzlesInRoom.add(puzzle);
              }
          }
          return puzzlesInRoom;
      }
  
      /**
       * Get all puzzles of a specific type.
       * @param type
       * @return List of puzzles of the specified type.
       */
      public List<Puzzle> getPuzzlesByType(String type) {
          List<Puzzle> filteredPuzzles = new ArrayList<>();
          for (Puzzle puzzle : puzzles) {
              if (puzzle.getClass().getSimpleName().equalsIgnoreCase(type)) {
                  filteredPuzzles.add(puzzle);
              }
          }
          return filteredPuzzles;
      }
  
     /**
      * Get the number of scantrons required to take a quiz or exam.
      * @param puzzleType
      * @param playerScantrons
      * @return requiredScantrons
      */ 
     public int getScantronRequirements(String puzzleType, int playerScantrons) {
          int requiredScantrons = 0;
      
          switch (puzzleType.toLowerCase()) {
              case "quiz":
                  requiredScantrons = REQUIRED_SCANTRONS_FOR_QUIZ;
                  break;
              case "exam":
                  requiredScantrons = REQUIRED_SCANTRONS_FOR_EXAM;
                  break;
              default:
                  return 0;
          }
      
          return requiredScantrons;
      }
 
      /**
       * Check if the player has enough scantrons to take a quiz or exam.
       * @param playerScantrons
       */
      public boolean checkScantronRequirements(int playerScantrons, String puzzleType) {
         int requiredScantrons = getScantronRequirements(puzzleType, playerScantrons);
         boolean scantronMessagePrinted = false;
 
         if (!scantronMessagePrinted && playerScantrons < requiredScantrons) {
             System.out.println("\nYou need " + requiredScantrons + " scantron(s) to take the " + puzzleType + ".");
             scantronMessagePrinted = true;
         }
     
         return playerScantrons >= requiredScantrons;
     }
 
  }
  