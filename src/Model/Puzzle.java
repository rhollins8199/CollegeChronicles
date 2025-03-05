/*
 * Puzzle Class: Responsible for initializing and managing puzzle attributes. 
 */

 package Model;

 public class Puzzle {
 
    // Puzzle attributes
     private final int roomId;
     private final String puzzleType;
     private final String puzzleQuestion;
     private final String optionA;
     private final String optionB;
     private final String optionC;
     private final String optionD;
     private final String correctAnswer;
     private boolean isSolved;
 
    // Puzzle constructor
     public Puzzle(int roomId, String puzzleType, String puzzleQuestion, String optionA, String optionB, String optionC, String optionD, String correctAnswer, boolean isSolved) {
         this.roomId = roomId;
         this.puzzleType = puzzleType;
         this.puzzleQuestion = puzzleQuestion;
         this.optionA = optionA;
         this.optionB = optionB;
         this.optionC = optionC;
         this.optionD = optionD;
         this.correctAnswer = correctAnswer;
         this.isSolved = isSolved;
     }
 
     // Getters
     public int getRoomId() { return roomId; }
     public String getPuzzleType() { return puzzleType; }
     public String getPuzzleQuestion() { return puzzleQuestion; }
     public String getOptionA() { return optionA;}
     public String getOptionB() { return optionB; }
     public String getOptionC() { return optionC; }
     public String getOptionD() { return optionD; }
     public String getCorrectAnswer() { return correctAnswer; }
     public boolean getIsSolved() { return isSolved; }
 
     // Setters
     public void setIsSolved(boolean solved) { isSolved = solved; }
 
     @Override
     public String toString() {
         return "\n\n---------------------PUZZLE DATA----------------------" +
                 "\nRoom ID: " + roomId +
                 "\nPuzzle Type: " + puzzleType +
                 "\nPuzzle Question: " + puzzleQuestion +
                 "\nOption A) " + optionA +
                 "\nOption B) " + optionB +
                 "\nOption C) " + optionC +
                 "\nOption D) " + optionD +
                 "\nPuzzle Answer: " + correctAnswer +
                 "\nIs Soved? " + isSolved;
     }

}