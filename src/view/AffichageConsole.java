package view;
/**
 * The AffichageConsole class is responsible for displaying game events in the console.
 * It provides methods to output messages related to various game actions, such as
 * starting the game, moving tiles, moving players, capturing objectives, and ending the game.
 *
 * Key responsibilities include:
 * - Displaying a message when the game starts.
 * - Displaying a message when a tile is moved.
 * - Displaying a message when the player moves.
 * - Displaying a message when an objective is captured.
 * - Displaying a message when the game ends.
 *
 * This class serves as a simple way to provide feedback to the user through the console,
 * helping to track the progress of the game and the actions performed.
 */

public class AffichageConsole {
    public void debutPartie(){
        System.out.println("Debut de la partie");

    }
    public void deplacementTuile(){
        System.out.println("Deplacement d'une tuile");

    }
    public void deplacementJoueur(){
        System.out.println("Deplacement d'un joueur");
    }
    public void captureObjectif(){
        System.out.println("Capture d'un objectif");
    }
    public void finPartie(){
        System.out.println("Fin de la partie");
    }
}
