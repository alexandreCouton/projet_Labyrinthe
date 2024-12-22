import controller.GameController;
import model.Game;
import view.GameDisplay;
import view.GameBoardPanel;

/**
 * LabyrintheApp is the main entry point of the Labyrinthe game application.
 * This class sets up the game, the game controller, and links the observer pattern
 * to update the game display. It initializes the game board, players, and links
 * observers for the board and players to reflect the changes in the game state.
 *
 * <p>It wires up the following components:
 * <ul>
 *   <li>Game: The game model that contains the state and logic of the game.</li>
 *   <li>GameController: The controller that processes user input and modifies the game state.</li>
 *   <li>GameBoardPanel: The graphical user interface (GUI) panel that observes and displays the game board.</li>
 *   <li>GameDisplay: A view observer that updates the display based on the game's state.</li>
 * </ul>
 *
 * <p>This class also adds observers to update the graphical representation of the game,
 * ensuring the changes are reflected on the screen as the game progresses.
 *
 * <p>The following observers are added to ensure real-time updates:
 * <ul>
 *   <li>GameBoard observer for updating the board display when the game state changes.</li>
 *   <li>Player observers for updating the player movements on the game board.</li>
 * </ul>
 *
 * @author Antoine.C <br> Alexandre.C
 * @version 0.5
 * @since 2024-12-09
 * 
 */
public class LabyrintheApp {
    public static void main(String[] args) {
        Game game = new Game();
        GameController gameController = new GameController(game);
        GameBoardPanel obsPlayer = new GameBoardPanel(game.getGameBoard());
        GameDisplay observer = new GameDisplay(game, obsPlayer);
        game.getGameBoard().addObserver(observer);
        for (int i = 0; i < 4; i++) {
            game.getPlayer(i).addObserver(obsPlayer);
        }

    }
}
