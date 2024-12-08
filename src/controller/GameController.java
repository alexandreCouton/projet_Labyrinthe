package controller;

import model.*;

public class GameController {
    private int m_joueurCourant;
    private Game m_game;

    public GameController(Game game) {
        m_game = game;
        m_joueurCourant = 0;
    }



    public void prochainTour() {
        m_joueurCourant = (m_joueurCourant + 1) % 4;
    }

    public void placeFlyingTile(Position pos) {
        m_game.insertFlyingTile(pos);

    }

    public void deplacer(Direction direction) {
        m_game.movePlayer(direction);
    }

    public void captureObjectif(Player player, Objective objective) {
        m_game.captureObjectif(player, objective);
    }


}
