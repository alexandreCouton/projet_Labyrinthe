package controller;

import model.*;

/**
 * The GameController class serves as the controller in the MVC architecture for the Labyrinth game.
 * It handles the communication between the view (user interface) and the model (game logic),
 * ensuring that user inputs are processed and the game state is updated accordingly.
 *
 * Responsibilities of this class include:
 * - Processing user inputs (e.g., keyboard or mouse events).
 * - Invoking appropriate methods in the model to modify the game state.
 * - Updating the view to reflect changes in the game state.
 * - Managing the overall flow and rules of the game.
 *
 * This class acts as the central mediator, ensuring a clear separation of concerns
 * between the view and model layers.
 */


public class GameController {
    private Game m_game;

    /**
     * @param game : The Game from the Model
     */
    public GameController(Game game) {
        m_game = game;
    }


    /**
     * @param pos : The position of the flyingTile (get after pressing "Inserer la tuile")
     */
    public void placeFlyingTile(Position pos) {
        m_game.insertFlyingTile(pos);
    }

    public void rotateFlyingTile() {
        m_game.getGameBoard().getFlyTile().rotate();
    }

    /**
     * @param direction : The directions "Haut, Bas, Gauche, Droite" indicating where the user wants to go.
     */
    public void move(Direction direction) {
        m_game.movePlayer(direction);
    }

    public void nextTurn()  {
        m_game.prochainTour();
    }

    /**
     * Capture the objective
     */
    public void captureObjectif() {
        m_game.captureObjectif();
    }

    public void finishGame() {
        m_game.finishGame();
    }


}
