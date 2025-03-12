/**
 * Game Class: Responsible for running and starting the game.
 * 
 * @author ReAnn Hollins
 * @version 1.0
 * @since 2023-04-01
 * 
 **/

package Demo;

import Controller.Controller;
import Controller.Manager.CommandProcessor;
import View.View;
import Model.Player;
import Model.Reader;

import java.util.Scanner;
public class Game {
    
    private static void launchGame() {
        new Controller(
            new CommandProcessor(
                new View(),
                new Reader(),
                new Scanner(System.in),
                new Player(null, null)
            )
        ).startGameCycle(); // Start the game with a new Controller object.
    }

    public static void main(String[] args) { // Main method where the game is launched.
        try {
            launchGame();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while running the game: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
