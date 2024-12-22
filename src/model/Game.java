package model;

import java.util.ArrayList;

/**
 * The Game class encapsulates the core logic of the Labyrinth game.
 * It is part of the model in the MVC architecture and is responsible for managing
 * the game's state, rules, and mechanics. <br>
 *
 * <ul>Key responsibilities include:<br>
 * - Initializing the game board and placing the player and objectives.<br>
 * - Managing player movements and ensuring they follow the game rules.<br>
 * - Detecting and handling events, such as reaching objectives or invalid moves.<br>
 * - Tracking the game state, including the player's position, objectives, and score.<br>
 *</ul>
 * This class provides methods that allow the controller to interact with and update<br>
 * the game state while maintaining the integrity of the rules and logic.
 */


public class Game {

    /**
     * The list of current players in the game.
     */
    private final Player[] lstPlayer;
    private final GameBoard m_gameBoard;
    private int m_currentPlayer;
    private boolean m_insertedFlyingTile;

    public Game() {
        this.lstPlayer = new Player[4];
        m_gameBoard = new GameBoard();
        m_currentPlayer = 0;
        initPlayers();
        distributeObjectivesToPlayer(m_gameBoard.getLstObjective());
        m_insertedFlyingTile = false;
    }

    /**
     * @param lstObj : list of objectives to distribute to the players
     */
    private void distributeObjectivesToPlayer(ArrayList<Objective> lstObj){
        ArrayList<Objective> lstObjTemp = new ArrayList<>();
        for(Player p : lstPlayer){
            for(int i = 0; i<6; i++){
                lstObjTemp.add(lstObj.get(0));
                lstObj.remove(0);
            }
            p.setLstObjectif(lstObjTemp);
            lstObjTemp = new ArrayList<>();
        }
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
        lstPlayer[0].setStartPos(new Position(0,0));
        lstPlayer[0].setImgPawn("src/img/pawn/bluePawn.png");
        lstPlayer[1].setStartPos(new Position(6,0));
        lstPlayer[1].setImgPawn("src/img/pawn/yellowPawn.png");
        lstPlayer[2].setStartPos(new Position(0,6));
        lstPlayer[2].setImgPawn("src/img/pawn/redPawn.png");
        lstPlayer[3].setStartPos(new Position(6,6));
        lstPlayer[3].setImgPawn("src/img/pawn/greenPawn.png");
    }


    /**
     * @return the gameBoard
     */
    public GameBoard getGameBoard() {
        return m_gameBoard;
    }

    /**
     * @return the current player
     */
    public Player getCurrentPlayer(){
        return lstPlayer[m_currentPlayer];
    }

    /**
     * @param pos : Position where the current player wants to insert the flying tile
     */
    public void insertFlyingTile(Position pos){
        if(!m_insertedFlyingTile){
            m_gameBoard.insertFlyingTile(pos);
            m_insertedFlyingTile = true;
            for(Player j : lstPlayer){
                if(j.getPositionY() == pos.getPositionY()){
                    if(pos.getPositionX() == 0) {
                        j.setPawnPosition(m_gameBoard.outSideBoard(j.getPosition().moveRight()));

                    } else if (pos.getPositionX() == 6) {
                        j.setPawnPosition(m_gameBoard.outSideBoard(j.getPosition().moveLeft()));

                    }
                }
                if(j.getPositionX() == pos.getPositionX()){
                    if (pos.getPositionY() == 0) {
                        j.setPawnPosition(m_gameBoard.outSideBoard(j.getPosition().moveDown()));
                    } else if (pos.getPositionY() == 6) {
                        j.setPawnPosition(m_gameBoard.outSideBoard(j.getPosition().moveUp()));
                    }
                }
            }
        }
    }

    /**
     * @return the current player's position
     */
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
    public void nextTurn(){
        m_currentPlayer++;
        if(m_currentPlayer == 4){
            m_currentPlayer = 0;
        }
        m_insertedFlyingTile = false;
    }

    /**
     */
    public void captureObjectif() {
        Objective obj = m_gameBoard.getTile(getCurrentPlayer().getPosition()).getObjective();
        if(getCurrentPlayer().getLstObjective().contains(obj)){
            getCurrentPlayer().captureObjective(obj);
        }
    }

    /**
     * Check if the game is finished
     */
    public void finishGame(){
        Player currentPlayer = getCurrentPlayer();
        Position currentPosition = currentPlayer.getPosition();
        Position startPosition = currentPlayer.getStartPos();

        System.out.println("Checking end game conditions...");
        System.out.println("Current Player: " + currentPlayer.toString());
        System.out.println("All Objectives Captured: " + currentPlayer.allObjectiveCapture());
        System.out.println("Current Position: " + currentPosition);
        System.out.println("Start Position: " + startPosition);

        if(getCurrentPlayer().allObjectiveCapture() && getCurrentPlayer().isStartPos(getCurrentPlayerPosition())){
            m_gameBoard.notifyObserverEndGame();
        } else {
            System.out.println("Finish game conditions not met"); // Ajout d'un message de débogage
        }
    }



}
