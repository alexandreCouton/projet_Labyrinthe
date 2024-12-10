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

    /**
     * @param direction : The directions "Haut, Bas, Gauche, Droite" indicating where the user wants to go.
     */
    public void deplacer(Direction direction) {
        System.out.println("Deplacement d'un joueur");
        if(m_game.movePlayer(direction)){
            System.out.println("Deplacement d'un joueur reussi");
            m_game.prochainTour();
        }
    }

    /**
     * @param player : The player currently playing.
     * @param objective : The objective at the player's current position.
     */
    public void captureObjectif(Player player, Objective objective) {
        m_game.captureObjectif(player, objective);
    }


}
