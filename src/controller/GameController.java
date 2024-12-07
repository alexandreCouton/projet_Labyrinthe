package controller;

import model.*;

public class GameController {
    private final GameBoard m_gameBoard;
    private int m_joueurCourant;
    private Game m_game;

    public GameController(GameBoard gameBoard) {
        m_gameBoard = gameBoard;
        m_joueurCourant = 0;
    }



    public void prochainTour() {
        m_joueurCourant = (m_joueurCourant + 1) % 4;
    }

    public void placerTuileVolante(Position pos) {
        m_gameBoard.insertFlyingTile(pos);
    }

    public void deplacer(Direction direction) {
        m_game.movePlayer(direction);
    }

    public void captureObjectif(Player player, Objective objective) {
        m_gameBoard.captureObjectif(player, objective);
    }


}
