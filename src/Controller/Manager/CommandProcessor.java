/*
 * Command Processor Class: Responsible for processing user commands and updating the game state.
 *
 * Method categories:
 * 1. Game Initialization
 * 2. Command Processing
 * 3. Validation Methods
 * 4. Handler Methods
 * 5. Utility Methods
 * 6. Movement Method
 * 7. Puzzle methods
 */

package Controller.Manager;

import View.*;
import Model.*;
import Model.Managers.*;

import java.util.Scanner;
public class CommandProcessor {

    // Constants
    private static final String START_COMMAND = "start";
    private static final String EXIT_COMMAND = "exit";
    private final String[] VALID_MAIN_MENU_COMMANDS = { START_COMMAND, EXIT_COMMAND };
    private static final String INVALID_MENU_COMMAND_MESSAGE = "\nInvalid command. Please enter 'start' or 'exit'.";

    // Dependencies
    private final View view;
    private final Scanner scanner;
    private final PlayersManager playersManager;

     // Constructor
     public CommandProcessor(View view, Scanner scanner, Player player) {
        this.view = view;
        this.scanner = scanner;
        this.playersManager = new PlayersManager(player);
    }

    // User Input Tracking
    private String userInput;

    /* ========================== GAME INITIALIZATION ========================== */

    // Starts and initializes the game.
    public void gameOpening() {
        view.showGameIntro();
    }

    // Processes the initial command entered by the user.
    public void processInitialCommand() {
        userInput = getUserInput();

        if (isValidMenuCommand(userInput)) {
            handleInitialCommand(userInput);
        } else {
            view.printError(INVALID_MENU_COMMAND_MESSAGE);
            processInitialCommand();
        }
    }

     /**
     * Handles the initial command entered by the user.
     * @param userInput
     */
    private void handleInitialCommand(String userInput) {
        switch (userInput) {
            case START_COMMAND:
                startGameSequence();
                break;
            case EXIT_COMMAND:
                handleExitGame();
                break;
            default:
                throw new IllegalStateException(INVALID_MENU_COMMAND_MESSAGE);
        }
    }

    // Starts the game sequence.
    private void startGameSequence() {
        loadingGameIndicator();
        promptForPlayerName();
        view.showWelcomeIntro();
        enterToContinue();
    }

    /* ========================== COMMAND PROCESSING ========================== */

    /* ========================== VALIDATION METHODS ========================== */

    /**
     * Checks if the given user input is a valid main menu command (start or exit).
     * @param userInput
     * @return true if the command is valid, false otherwise
     */
    private boolean isValidMenuCommand(String userInput) {
        return java.util.Arrays.asList(VALID_MAIN_MENU_COMMANDS).contains(userInput);
    }

    /* ========================== HANDLER METHODS ========================== */

     // Exits the game.
     private void handleExitGame() {
        scanner.close();
        System.exit(0);
    }

    /* ========================== UTILITY METHODS ========================== */

    /**
     * Prompts the player for input and returns it as a lowercase string.
     * Ensures consistency in user command processing.
     * @return userInput
     */
    private String getUserInput() {
        view.showInputIndicator();
        return scanner.nextLine().toLowerCase();
    }

    // Waits for 3 seconds before continuing the game.
    private void wait3seconds() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Displays a loading indicator before starting the game.
    private void loadingGameIndicator() {
        view.print("\nStarting Game");
        for (int i = 0; i < 3; i++) {
            wait3seconds();
            view.print(".");
        }
        wait3seconds();
        view.print("\n");
    }

    /**
     * Prompts the player to press enter to continue the game.
     * @return (empty string)
     */
    private String enterToContinue() {
        return scanner.nextLine().toLowerCase();
    }

    // Prompts the player for their name.
    private void promptForPlayerName() {
        view.showEventLine();
        view.print("Enter your " + view.YELLOW + "name" + view.RESET + " below:\n");
        userInput = getUserInput();
        setPlayerName(userInput);
    }

    /**
     * Sets the player's name.
     * @param userInput
     */
    private void setPlayerName(String userInput) {
        if (userInput.equals(EXIT_COMMAND)) {
            handleExitGame();
        } else if (userInput.isEmpty()) {
            playersManager.setPlayerName("student");
        } else {
            playersManager.setPlayerName(userInput);
        }
        view.showEventLine();
        view.println("Welcome, " + playersManager.getPlayer().getPlayerName().toUpperCase() + view.RESET + "!\n");
    }

    /* ========================== MOVEMENT METHOD ========================== */

    /* ========================== PUZZLE METHODS ========================== */

    
}
