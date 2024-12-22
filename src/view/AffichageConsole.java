package view;
/**
 * The AffichageConsole class is responsible for displaying game events in the console.
 * It provides methods to output messages related to various game actions, such as
 * starting the game, moving tiles, moving players, capturing objectives, and ending the game.
 *<br>
 * Key responsibilities include:<br>
 * - Displaying a message when the game starts.<br>
 * - Displaying a message when a tile is moved.<br>
 * - Displaying a message when the player moves.<br>
 * - Displaying a message when an objective is captured.<br>
 * - Displaying a message when the game ends.<br>
 *<br>
 * This class serves as a simple way to provide feedback to the user through the console,<br>
 * helping to track the progress of the game and the actions performed.<br>
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
