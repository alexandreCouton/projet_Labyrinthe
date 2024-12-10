package model;

import view.GameDisplay;
import view.PlateauPanel;

/**
 * The Game class encapsulates the core logic of the Labyrinth game.
 * It is part of the model in the MVC architecture and is responsible for managing
 * the game's state, rules, and mechanics.
 *
 * Key responsibilities include:
 * - Initializing the game board and placing the player and objectives.
 * - Managing player movements and ensuring they follow the game rules.
 * - Detecting and handling events, such as reaching objectives or invalid moves.
 * - Tracking the game state, including the player's position, objectives, and score.
 *
 * This class provides methods that allow the controller to interact with and update
 * the game state while maintaining the integrity of the rules and logic.
 */


public class Game {

    /**
     * The list of current players in the game.
     */
    private final Player[] lstPlayer;
    private final GameBoard m_gameBoard;
    private int m_currentPlayer;

    public Game() {
        this.lstPlayer = new Player[4];
        m_gameBoard = new GameBoard();
        m_currentPlayer = 0;
        initPlayers();
    }

    /**
     * @param player : get the player instance from an int in the lstPlayer
     * @return an instance of player in the lstPlayer
     */
    public Player getPlayer(int player){ return lstPlayer[player]; }

    /**
     * Initialize the players
     */
    private void initPlayers() {
        for (int i = 0; i < 4; i++) {
            lstPlayer[i] = new Player("Player " + (i + 1));

        }
        placePlayer();
    }


    /**
     * place the players on the map
     */
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


    /**
     * @return the gameBoard
     */
    public GameBoard getGameBoard() {
        return m_gameBoard;
    }

    /**
     * @param pos : Position where the current player wants to insert the flying tile
     */
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

    private Position getCurrentPlayerPosition(){
        return lstPlayer[m_currentPlayer].getPosition();
    }


    /**
     * @param direction : the direction of where the player wants to go
     * @return a boolean (true if he can, false if he can't)
     */
    public boolean movePlayer( Direction direction){
        Position nextMove;
        switch (direction) {
            case Direction.RIGHT:
                nextMove = getCurrentPlayerPosition().moveRight();
                if (m_gameBoard.inBoard(nextMove)) {
                    if (m_gameBoard.getTile(getCurrentPlayerPosition()).moveTile(direction, m_gameBoard.getTile(nextMove))) {
                            lstPlayer[m_currentPlayer].move(nextMove);
                            return true;
                        }
                    }

                return false;
            case Direction.LEFT:
                nextMove = getCurrentPlayerPosition().moveLeft();
                if (m_gameBoard.inBoard(nextMove)) {
                    if (m_gameBoard.getTile(getCurrentPlayerPosition()).moveTile(direction, m_gameBoard.getTile(nextMove))) {
                        lstPlayer[m_currentPlayer].move(nextMove);
                        return true;
                    }

                }

                return false;

            case Direction.UP:
                nextMove = getCurrentPlayerPosition().moveUp();
                if (m_gameBoard.inBoard(nextMove)) {
                    if (m_gameBoard.getTile(getCurrentPlayerPosition()).moveTile(direction, m_gameBoard.getTile(nextMove))) {
                            lstPlayer[m_currentPlayer].move(nextMove);
                            return true;
                    }
                }
                return false;
            case Direction.DOWN:
                nextMove = getCurrentPlayerPosition().moveDown();
                if (m_gameBoard.inBoard(nextMove)) {
                    if (m_gameBoard.getTile(getCurrentPlayerPosition()).moveTile(direction, m_gameBoard.getTile(nextMove))) {
                            lstPlayer[m_currentPlayer].move(nextMove);
                            return true;
                    }
                }
                return false;
        }

        return false;
    }

    /**
     * next player
     */
    public void prochainTour(){
        System.out.println("Player " + m_currentPlayer);

        m_currentPlayer++;
        System.out.println("Player " + m_currentPlayer);
        if(m_currentPlayer == 4){
            m_currentPlayer = 0;
        }
    }

    /**
     */
    public void captureObjectif() {
        //j.captureObjectif(objective);
    }


}
