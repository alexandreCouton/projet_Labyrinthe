package controller;

import model.*;

public class GameController {
    private Game m_game;

    public GameController(Game game) {
        m_game = game;
    }




    public void placeFlyingTile(Position pos) {
        m_game.insertFlyingTile(pos);

    }

    public void deplacer(Direction direction) {
        if(m_game.movePlayer(direction)){
            m_game.prochainTour();
        }
    }

    public void captureObjectif(Player player, Objective objective) {
        m_game.captureObjectif(player, objective);
    }


}
