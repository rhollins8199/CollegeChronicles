/*
* Controller Class: The main controller of the application. It interacts with the CommandProcessor to start the game cycle.
*/

package Controller;

import Controller.Manager.CommandProcessor;

public class Controller {

    private final CommandProcessor commandProcessor; // Dependency

    public Controller(CommandProcessor commandProcessor) { this.commandProcessor = commandProcessor; } // Constructor

    // Method to start the game cycle
    public void startGameCycle() {
        commandProcessor.gameOpening();
    }  
}
