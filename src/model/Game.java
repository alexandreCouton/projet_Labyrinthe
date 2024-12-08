package model;

import view.GameDisplay;
import view.PlateauPanel;

public class Game {

    private final Player[] lstPlayer;
    private final GameBoard m_gameBoard;
    private int m_currentPlayer;

    public Game() {
        this.lstPlayer = new Player[4];
        m_gameBoard = new GameBoard();
        m_currentPlayer = 0;
        initPlayers();
    }

    public Player getPlayer(int player){ return lstPlayer[player]; }

    private void initPlayers() {
        for (int i = 0; i < 4; i++) {
            lstPlayer[i] = new Player("Player " + (i + 1));

        }
        placePlayer();
    }

    private void placePlayer(){
        lstPlayer[0].setPionPosition(new Position(0,0));
        lstPlayer[0].setImgPion("src/img/pawn/bluePawn.png");
        lstPlayer[1].setPionPosition(new Position(6,0));
        lstPlayer[1].setImgPion("src/img/pawn/yellowPawn.png");
        lstPlayer[2].setPionPosition(new Position(0,6));
        lstPlayer[2].setImgPion("src/img/pawn/redPawn.png");
        lstPlayer[3].setPionPosition(new Position(6,6));
        lstPlayer[3].setImgPion("src/img/pawn/greenPawn.png");
    }

    public GameBoard getGameBoard() {
        return m_gameBoard;
    }

    public void insertFlyingTile(Position pos){
        m_gameBoard.insertFlyingTile(pos);
        for(Player j : lstPlayer){
            if(j.getPosition().getPositionY() == pos.getPositionY()){
                if(pos.getPositionX() == 0) {
                    j.deplacer(Direction.RIGHT);
                } else if (pos.getPositionX() == 6) {
                        j.deplacer(Direction.LEFT);
                }
            }
            else if(j.getPosition().getPositionX() == pos.getPositionX() ){

                if (pos.getPositionY() == 0) {
                    j.deplacer(Direction.DOWN);
                } else if (pos.getPositionY() == 6) {
                    j.deplacer(Direction.UP);
                }
            }
        }
    }


    public boolean movePlayer( Direction direction){
        switch (direction) {
            case Direction.RIGHT:
                if (lstPlayer[m_currentPlayer].getPosition().getPositionX() + 1 <= 6) {
                    if (m_gameBoard.getGameBoard()[lstPlayer[m_currentPlayer].getPosition().getPositionX()][lstPlayer[m_currentPlayer].getPosition().getPositionY()].getOpen(Direction.RIGHT)) {
                        if (m_gameBoard.getGameBoard()[lstPlayer[m_currentPlayer].getPosition().getPositionX() + 1][lstPlayer[m_currentPlayer].getPosition().getPositionY()].getOpen(Direction.LEFT)) {
                            lstPlayer[m_currentPlayer].deplacer(Direction.RIGHT);
                            return true;
                        }
                    }
                }
                return false;
            case Direction.LEFT:
                if (lstPlayer[m_currentPlayer].getPosition().getPositionX() - 1 >= 0) {
                    if (m_gameBoard.getGameBoard()[lstPlayer[m_currentPlayer].getPosition().getPositionX()][lstPlayer[m_currentPlayer].getPosition().getPositionY()].getOpen(Direction.LEFT)) {
                        if (m_gameBoard.getGameBoard()[lstPlayer[m_currentPlayer].getPosition().getPositionX() - 1][lstPlayer[m_currentPlayer].getPosition().getPositionY()].getOpen(Direction.RIGHT)) {
                            lstPlayer[m_currentPlayer].deplacer(Direction.LEFT);
                            return true;
                        }
                    }
                }
                return false;

            case Direction.UP:
                if (lstPlayer[m_currentPlayer].getPosition().getPositionY() - 1 >= 0) {
                    if (m_gameBoard.getGameBoard()[lstPlayer[m_currentPlayer].getPosition().getPositionX()][lstPlayer[m_currentPlayer].getPosition().getPositionY()].getOpen(Direction.UP)) {
                        if (m_gameBoard.getGameBoard()[lstPlayer[m_currentPlayer].getPosition().getPositionX()][lstPlayer[m_currentPlayer].getPosition().getPositionY() - 1].getOpen(Direction.DOWN)) {
                            lstPlayer[m_currentPlayer].deplacer(Direction.UP);
                            return true;
                        }
                    }
                }
                return false;
            case Direction.DOWN:
                if (lstPlayer[m_currentPlayer].getPosition().getPositionY() + 1 <= 6) {
                    if (m_gameBoard.getGameBoard()[lstPlayer[m_currentPlayer].getPosition().getPositionX()][lstPlayer[m_currentPlayer].getPosition().getPositionY()].getOpen(Direction.DOWN)) {
                        if (m_gameBoard.getGameBoard()[lstPlayer[m_currentPlayer].getPosition().getPositionX()][lstPlayer[m_currentPlayer].getPosition().getPositionY() + 1].getOpen(Direction.UP)) {
                            lstPlayer[m_currentPlayer].deplacer(Direction.DOWN);
                            return true;
                        }
                    }
                }
                return false;
        }

        return false;
    }

    public void prochainTour(){
        m_currentPlayer++;
        if(m_currentPlayer == 4){
            m_currentPlayer = 0;
        }
    }

    public void captureObjectif(Player j, Objective objective) {
        j.captureObjectif(objective);
    }


}
