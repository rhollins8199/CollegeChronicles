/*
* Command Processor Class: Responsible for processing user commands and updating the game state.
*/

package Controller.Manager;

import Model.*;
public class CommandProcessor {

    // Dependency
    private Reader reader = new Reader();

    public void gameOpening() {
        System.out.println("\nGame is opening...\n");

        String roomsList = reader.loadRoomsFromFile().toString();
        System.out.println(roomsList);

        String itemsList = reader.loadItemsFromFile().toString();
        System.out.println(itemsList);

        String puzzlesList = reader.loadPuzzlesFromFile().toString();
        System.out.println(puzzlesList);
    }
    
}
