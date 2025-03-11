/*
* View Class (aka Utility Class): Responsible for displaying game-related outputs and formatting, adhering to the Single Responsibility Principle.
*/

package View;

public class View {

    // ASCII Codes for printed text colors
    public final String PINK = "\u001B[1;95m";
    public final String CYAN = "\033[38;2;0;255;255m";
    public final String YELLOW = "\033[38;2;255;255;0m";
    public final String BRIGHT_YELLOW = "\033[38;2;255;255;102m";
    public final String BRIGHT_ORANGE = "\033[38;2;255;179;71m";
    public final String LIGHT_GREY = "\033[38;2;200;200;200m";
    public final String GREEN = "\033[38;2;0;255;0m";
    public final String RED = "\033[38;2;255;0;0m";

    // ASCII Codes for printed background colors
    public final String LIGHT_GREY_BG = "\033[48;2;211;211;211m";
    public final String GREEN_BG_BLACK_TEXT = "\033[48;2;50;180;50m\033[37m";
    public final String YELLOW_BG_BLACK_TEXT = "\033[48;2;200;200;50m\033[37m";
    public final String RED_BG_WHITE_TEXT = "\033[48;2;180;50;50m\033[30m";
    
    // ASCII Codes for printed text styles
    public final String BOLD = "\033[1m";
    public final String ITALIC = "\033[3m";
    public final String RESET = "\u001B[0m";

    // Utility Methods
    public void print(String p) { System.out.print(p); }
    public void println(String p) { System.out.println(p); }
    public void showInputIndicator() { System.out.print(BOLD + "\n> " + RESET); }
    public void showEventLine() { println("\n============================================================================================================\n"); }
    public void printError(String p) { System.out.println(RED + p + RESET); }

    public void printMessages(String[] messages) {
        for (String message : messages) {
            println(message);
        }
    }
    
    /* ========================== GAME INTRODUCTION ========================== */

    public void showGameIntro() {
        String title = BOLD + GREEN +
                 " ___     _ _               ___ _               _     _        \n" +
                 "|  _|___| | |___ ___ ___  |  _| |_ _ _ ___._ _<_>___| |___ ___\n" +
                 "| <_/ . | | / ._/ . / ._> | <_| . | '_/ . | ' | / _ | / ._<_-<\n" +
                 "`___\\___|_|_\\___\\_. \\___. `___|_|_|_| \\___|_|_|_\\___|_\\___/__/\n" +
                 "                |___/\n\n" + RESET +
                 // Alternating pink and green for the ASCII text
                 GREEN + "         C" + RESET + PINK + " O" + RESET + GREEN + " L" + RESET + PINK + " L" + RESET + GREEN + " E" + RESET + PINK + " G" + RESET +
                 GREEN + " E" + RESET + "  ·  " + GREEN + "C" + RESET + PINK + " H" + RESET + GREEN + " R" + RESET + PINK + " O" + RESET + GREEN + " N" + RESET +
                 PINK + " I" + RESET + GREEN + " C" + RESET + PINK + " L" + RESET + GREEN + " E" + RESET + PINK + " S" + RESET + "\n";
        String[] message = {
                "Embark on the ultimate academic adventure.",
                "Will you ace every exam and overcome life's curveballs?",
                "The journey awaits, and the goal is clear, finish with a perfect " + BOLD + YELLOW + "4.0" + RESET
                        + "!",
                "\nEnter " + GREEN + "start" + RESET + " to begin or " + PINK + "exit" + RESET + " to end."
        };

        showEventLine();
        println(title);
        printMessages(message);
    }

    /* ========================== WELCOME MESSAGE & MAP ========================== */

    public void showWelcomeIntro() {
        String[] message = {
                "My name is Whitney. \nI'm your advisor here at Not-So Ivy League College.",
                "My job is to give you the important information you need to achieve here at NSI.",
                "Below is a map and some basic rules that you will need to in order to succeed this semester."
        };

        printMessages(message);
        showGameMap();
        showBasicCommands();
        print(BOLD + "\nPRESS ENTER TO CONTINUE" + RESET);
    }

    public void showGameMap() {
        String[] message = {
                BOLD + "\nCAMPUS MAP" + RESET,
                "\nStarting Room = " + GREEN + "Green Dot" + RESET,
                "Course Rooms = " + YELLOW + "Yellow Text" + RESET,
                "                                   *----------*       *--------*      *-----------*                   ",
                "                                   |  " + YELLOW + "Art" + RESET
                        + "     |       | Book   |      |  Course   |                    ",
                "                                   |  " + YELLOW + "Class" + RESET
                        + "   |       | Store  |      |  Tutoring |                    ",
                "  *--------------*-----------*----------------------*-----------*---------------------*-------------*  ",
                "  |  Admissions  |  Dinning  |       Hallway        |   Study   |       Hallway       | " + YELLOW
                        + "Programming" + RESET + " |  ",
                "  |  Office " + GREEN + "*" + RESET
                        + "    |  Hall     |          A           |   Lounge  |          B          | " + YELLOW
                        + "Class" + RESET + "       |  ",
                "  *--------------*-----------*----------------------*-----------*---------------------*-------------*  ",
                "                                   | Bathroom |       | " + YELLOW + "Math" + RESET
                        + "   |      | Bathroom  |                    ",
                "                                   |    A     |       | " + YELLOW + "Class" + RESET
                        + "  |      |     B     |                    ",
                "                                   *----------*       *--------*      *-----------*                   "
        };

        printMessages(message);
    }

    /* ========================== IN-GAME COMMANDS ========================== */

    public void showBasicCommands() {
        String[] message = {
                BOLD + "RULES:" + RESET,
                "- Move " + GREEN + "(n)" + RESET + "orth",
                "- Move " + GREEN + "(e)" + RESET + "ast",
                "- Move " + GREEN + "(s)" + RESET + "outh",
                "- Move " + GREEN + "(w)" + RESET + "est",
                "- For " + CYAN + " (h)" + RESET + "elp",
                "- Exit Game " + PINK + "(exit)" + RESET
        };

        printMessages(message);
    }

    public void showCommandOptions() {
        println(
                "\nEnter "
                        + GREEN + "(n)" + RESET + "orth, "
                        + GREEN + "(e)" + RESET + "ast, "
                        + GREEN + "(s)" + RESET + "outh, "
                        + GREEN + "(w)" + RESET + "est, "
                        + CYAN + "(h)" + RESET + "elp, "
                        + "or " + PINK + "(exit)" + RESET + ".");
    }

    /* ========================== COMPLETE GAME COMMANDS & INSTRUCTIONS ========================== */

    public void showFullCommandsAndInstructions() {
        String title = BOLD + ITALIC + "COMMAND LIST:\n" + RESET;
        String[] message = {
                BOLD + "Movement Actions:" + RESET,
                "- " + GREEN + "(n)" + RESET + " or " + GREEN + "(north)" + RESET + "  -> Move North",
                "- " + GREEN + "(e)" + RESET + " or " + GREEN + "(east)" + RESET + "   -> Move East",
                "- " + GREEN + "(s)" + RESET + " or " + GREEN + "(south)" + RESET + "  -> Move South",
                "- " + GREEN + "(w)" + RESET + " or " + GREEN + "(west)" + RESET + "   -> Move West\n",

                BOLD + "Room Actions:" + RESET,
                "- " + YELLOW + "(ex)" + RESET + " or " + YELLOW + "(explore)" + RESET + "  -> Explore room\n",

                BOLD + "Item Actions:" + RESET,
                "- " + BRIGHT_ORANGE + "(in)" + RESET + " or " + BRIGHT_ORANGE + "(inspect) [itemName]" + RESET + "  -> Inspect an item",
                "- " + BRIGHT_ORANGE + "(pk)" + RESET + " or " + BRIGHT_ORANGE + "(pickup) [itemName]" + RESET + "  -> Use an item",
                "- " + BRIGHT_ORANGE + "(ba)" + RESET + " or " + BRIGHT_ORANGE + "(backpack)" + RESET + "  -> View backpack and grades\n",

                BOLD + "Others:" + RESET,
                "- " + CYAN + "(h)" + RESET + " or " + CYAN + "(help)" + RESET + "  -> View commands and instructions",
                "- " + PINK + "(exit)" + RESET + "  -> Exit Game",
        };

        String title2 = BOLD + ITALIC + "GAME INSTRUCTIONS:\n" + RESET;
        String[] message2 = {
                "1. Explore the campus by navigating between rooms.",
                "2. Discover new locations and uncover their resources.",
                "3. Find and collect scantrons needed for quizzes and exams.",
                "4. Use scantrons to take quizzes (1 ea.) and exams (2 ea.).",
                "5. Aim for a perfect 4.0 GPA to win the game!",
        };

        String title3 = BOLD + ITALIC + "CAMPUS MAP:\n" + RESET;
        String[] message3 = {
                "Course Rooms = " + YELLOW + "Yellow Text" + RESET,
                "                                   *----------*       *--------*      *-----------*                   ",
                "                                   |  " + YELLOW + "Art" + RESET
                        + "     |       | Book   |      |  Course   |                    ",
                "                                   |  " + YELLOW + "Class" + RESET
                        + "   |       | Store  |      |  Tutoring |                    ",
                "  *--------------*-----------*----------------------*-----------*---------------------*-------------*  ",
                "  |  Admissions  |  Dinning  |       Hallway        |   Study   |       Hallway       | " + YELLOW
                        + "Programming" + RESET + " |  ",
                "  |  Office      |  Hall     |          A           |   Lounge  |          B          | " + YELLOW
                        + "Class" + RESET + "       |  ",
                "  *--------------*-----------*----------------------*-----------*---------------------*-------------*  ",
                "                                   | Bathroom |       | " + YELLOW + "Math" + RESET
                        + "   |      | Bathroom  |                    ",
                "                                   |    A     |       | " + YELLOW + "Class" + RESET
                        + "  |      |     B     |                    ",
                "                                   *----------*       *--------*      *-----------*                   "
        };

        showEventLine();
        println(title);
        printMessages(message);
        showEventLine();
        println(title3);
        printMessages(message3);
        println("");
        println(title2);
        printMessages(message2);
        println(BOLD + "\nPRESS ENTER TO CONTINUE" + RESET);
    }

    /* ========================== EXIT GAME PRINTS ========================== */

    public void showDropoutExit() {
        println(BOLD + YELLOW + "\nSTATUS: COLLEGE DROPOUT\n" + RESET);
    }

    public void showGoodStandingExit() {
        println(BOLD + LIGHT_GREY + "\nSTATUS: GOOD STANDING\n" + RESET);
    }

    public void showGraduatingExit() {
        println(BOLD + GREEN + "\nSTATUS: COLLEGE GRADUATE\n" + RESET);
    }
}
