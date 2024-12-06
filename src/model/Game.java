package model;

public class Game {

    private final Player[] lstPlayer;
    private final GameBoard m_gameBoard;
    private int m_currentPlayer;

    public Game() {
        this.lstPlayer = new Player[4];
        m_gameBoard = new GameBoard();

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
        lstPlayer[1].setPionPosition(new Position(6,0));
        lstPlayer[2].setPionPosition(new Position(0,6));
        lstPlayer[3].setPionPosition(new Position(6,6));
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



}
