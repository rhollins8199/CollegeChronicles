/*
 * Command Processor Class: Responsible for processing user commands and updating the game state.
 *
 * Method categories:
 * 1. Game Initialization
 * 2. Command Processing
 * 3. Command Validation Methods
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
    private final String[] VALID_HELP_COMMANDS = { "h", "help" };
    private static final String[] VALID_INVENTORY_COMMANDS = { "ba", "backpack" };
    private static final String[] VALID_EXPLORE_COMMANDS = { "ex", "explore" };
    private static final String[] VALID_INSPECT_COMMANDS = { "in", "inspect" };
    private static final String[] VALID_PICKUP_COMMANDS = { "pk", "pickup" };
    private final String[] VALID_MOVEMENT_COMMANDS = { "north", "n", "south", "s", "east", "e", "west", "w" };
    private static final String INVALID_MENU_COMMAND_MESSAGE = "\nInvalid command. Please enter 'start' or 'exit'.";
    private static final String INVALID_COMMAND_MESSAGE = "\nInvalid command. Please enter 'h' for help.";
    private static final String NO_EXIT_MESSAGE = "\nThere is no exit that way. Please enter 'h' for help.";

    // Dependencies
    private final View view;
    private final Scanner scanner;
    private final PlayersManager playersManager;
    private final RoomsManager roomsManager;
    private final ItemsManager itemsManager;

     // Constructor
     public CommandProcessor(View view, Reader reader, Scanner scanner, Player player) {
        this.view = view;
        this.scanner = scanner;
        this.playersManager = new PlayersManager(player);
        this.roomsManager = new RoomsManager(reader.loadRoomsFromFile());
        this.itemsManager = new ItemsManager(reader.loadItemsFromFile());
    }

    // User Input Tracking
    private String userInput;

    /* ========================== GAME INITIALIZATION ========================== */

    // Starts and initializes the game.
    public void gameOpening() {
        view.showGameIntro();
        itemsManager.startRandomPlacementTask();
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
        getStartingRoom();
        userInput = getUserInput();
        processPlayerCommand(userInput);
    }

    /* ========================== COMMAND PROCESSING ========================== */

    /**
     * Processes the command entered by the player.
     * @param userInput
     */
    private void processPlayerCommand(String userInput) {

        if (userInput.equals(EXIT_COMMAND)) { 
            handleExitGame(); 
        } else if (java.util.Arrays.asList(VALID_HELP_COMMANDS).contains(userInput)) {
            handleHelpCommand();
        } else if (java.util.Arrays.asList(VALID_INVENTORY_COMMANDS).contains(userInput)) {
            playersManager.displayInventory();
            playersManager.displayReportCard();
        } else if (java.util.Arrays.asList(VALID_MOVEMENT_COMMANDS).contains(userInput)) {
            handleMovementCommand(userInput);
        } else if (java.util.Arrays.asList(VALID_EXPLORE_COMMANDS).contains(userInput)) {
            handleExploreCommand(userInput);
        } else if (isPickupCommand(userInput)) {
            handlePickupCommand(userInput);
        } else if (isInspectCommand(userInput)) {
            handleInspectCommand(userInput);
        } else {
            view.printError(INVALID_COMMAND_MESSAGE);
        }

        promptForPlayerCommand();
    }

    // Prompt for player command
    private void promptForPlayerCommand() {
        userInput = getUserInput();
        processPlayerCommand(userInput);
    }

    /* ========================== COMMAND VALIDATION METHODS ========================== */

    /**
     * Checks if the given user input is a valid main menu command (start or exit).
     * @param userInput
     * @return true if the command is valid, false otherwise
     */
    private boolean isValidMenuCommand(String userInput) {
        return java.util.Arrays.asList(VALID_MAIN_MENU_COMMANDS).contains(userInput);
    }

     /**
     * Checks if the user input is an inspect command.
     * @param userInput
     * @return
     */
    private boolean isInspectCommand(String userInput) {
        for (String command : VALID_INSPECT_COMMANDS) {
            if (userInput.startsWith(command + " ")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the user input is a pickup command.
     * @param userInput
     * @return
     */
    private boolean isPickupCommand(String userInput) {
        for (String command : VALID_PICKUP_COMMANDS) {
            if (userInput.startsWith(command + " ")) {
                return true;
            }
        }
        return false;
    }

    /* ========================== HANDLER METHODS ========================== */

     // Displays the full list of commands and instructions.
     private void handleHelpCommand() {
        view.showFullCommandsAndInstructions();
        enterToContinue();
        view.showEventLine();
        view.println("Location: " + view.YELLOW + playersManager.getPlayer().getCurrentRoom().getRoomName() + view.RESET + "\n");
        view.println(playersManager.getPlayer().getCurrentRoom().getRoomDescription());
        view.showCommandOptions();
    }

     /**
     * Handles the player's movement command.
     * @param userInput
     * @return
     */
    private boolean handleMovementCommand(String userInput) {
        Room currentRoom = playersManager.getPlayer().getCurrentRoom();
        movePlayer(roomsManager.getRoomExitId(currentRoom.getRoomId(), userInput), currentRoom);
        return true;
    }

    /**
     * Handles the player's explore command.
     * @param userInput
     */
    private void handleExploreCommand(String userInput) {
        int roomItemCount = itemsManager.getItemCountInRoom(playersManager.getPlayer().getCurrentRoom().getRoomId());

        if (roomItemCount > 0) {
            if (roomItemCount == 1) {
                view.println("\nThis room contains " + view.YELLOW + roomItemCount + view.RESET + " scantron.");
            } else {
                view.println("\nThis room contains " + view.YELLOW + roomItemCount + view.RESET + " scantrons.");
            }
        } else {
            view.println("\nThis room does not contain any scantrons.");
        }
    }

    /**
     * Handles the player's inspect command.
     * @param userInput
     */
    private void handleInspectCommand(String userInput) {
        if (isInspectCommand(userInput)) {
            String itemName = null;
    
            for (String command : VALID_INSPECT_COMMANDS) {
                if (userInput.startsWith(command + " ")) {
                    itemName = userInput.substring(command.length()).trim();
                    break;
                }
            }
    
            boolean itemFound = false;
    
            for (Item item : itemsManager.getItems()) {
                if (item.getItemName().equalsIgnoreCase(itemName) &&
                    item.getItemId() == playersManager.getPlayer().getCurrentRoom().getRoomId() &&
                    item.getItemCount() > 0) {
    
                    view.println("\n" + item.getItemDescription());
                    itemFound = true;
                    break;
                }
            }
    
            if (!itemFound) {
                view.println("\nThere are no " + itemName + "s in this room to inspect.");
            }
        } else {
            view.println("\nInvalid inspect command. Use 'inspect [item name]'.");
        }
    }

    /** 
     * Handles the player's pickup command.
     * @param userInput
    */
    private void handlePickupCommand(String userInput) {
        String itemName = null;

        for (String command : VALID_PICKUP_COMMANDS) {
            if (userInput.startsWith(command + " ")) {
                itemName = userInput.substring(command.length()).trim();
                break;
            }
        }
    
        boolean itemPickedUp = false;
    
        for (Item item : itemsManager.getItems()) {
            if (item.getItemName().equalsIgnoreCase(itemName)
                    && item.getItemId() == playersManager.getPlayer().getCurrentRoom().getRoomId() 
                    && item.getItemCount() > 0) {
    
                playersManager.addItemToInventory(item);
                item.setItemCount(item.getItemCount() - 1);
                view.println("\nYou picked up the " + itemName + "!");
                itemPickedUp = true;
                break;
            }
        }
    
        if (!itemPickedUp) {
            view.println("\nThere is no " + itemName + " in this room to pick up.");
        }
    
    }

     // Exits the game.
     private void handleExitGame() {
        itemsManager.stopRandomPlacementTask();
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

     // Displays the starting room and its description.
     private void getStartingRoom() {
        view.showEventLine();
        playersManager.setCurrentRoom(roomsManager.getRooms(0));
        playersManager.getPlayer().getCurrentRoom().setHasVisited(true);
        displayStartingRoomDetails();
    }

    // Displays the room details and command options.
    private void displayStartingRoomDetails() {
        Room startingRoom = playersManager.getPlayer().getCurrentRoom();
        view.println("Location: " + view.YELLOW + startingRoom.getRoomName() + view.RESET + "\n");
        view.println(startingRoom.getRoomDescription());
        view.showCommandOptions();
    }

    /* ========================== MOVEMENT METHOD ========================== */

     /**
     * Moves the player to the next room based on the given exit id.
     * @param nextRoomId
     * @param currentRoom
     */
    private void movePlayer(int nextRoomId, Room currentRoom) {
        if (nextRoomId == 0) {
            view.printError(NO_EXIT_MESSAGE);
            processPlayerCommand(getUserInput());
            return;
        }

        Room nextRoom = roomsManager.findRoomById(nextRoomId);

        if (nextRoom != null) {
            playersManager.getPlayer().getPreviousRoomsList().add(playersManager.getPlayer().getCurrentRoom());
            playersManager.setCurrentRoom(nextRoom);
            currentRoom.setHasVisited(true);
            view.showEventLine();
            if (playersManager.indicateNewRoom(nextRoom) == true) {
                view.println("Location: " + view.YELLOW + nextRoom.getRoomName() + view.RESET + "\n");
                view.println(nextRoom.getRoomDescription());
            } else {
                view.println(view.YELLOW + "New Location: " + nextRoom.getRoomName() + view.RESET + "\n");
                view.println(nextRoom.getRoomDescription());
            }
            view.showCommandOptions();
            processPlayerCommand(getUserInput());
        }

        else {
            view.printError(NO_EXIT_MESSAGE);
        }
    }

    /* ========================== PUZZLE METHODS ========================== */

    
}
