package model;

import view.ImageHelper;

import java.util.*;

/**
 * The GameBoard class represents the playing field in the Labyrinth game.
 * It is responsible for managing the tiles that make up the game grid,
 * initializing the board, and handling the placement and movement of tiles.
 *
 * Key responsibilities include:
 * - Initializing the game board with a 7x7 grid of tiles.
 * - Managing different types of tiles, including corner, T-shaped, and linear tiles.
 * - Placing the player’s tiles and objectives on the board.
 * - Handling the movement of the flying tile, which can shift tiles on the board.
 * - Notifying observers when tiles are updated.
 *
 * The class maintains multiple stacks of tiles, such as `m_StackAngle`, `m_stackT`, and `m_stackDroite`,
 * to store and manage different tile types. It also includes mechanisms to rotate tiles and
 * place them on the board based on random selection or specific game rules.
 */

public class GameBoard {
    private final Tiles[][] m_lstTuilesGameBoard;
    private final Stack<TilesCorner> m_StackCorner;
    private final Stack<TilesT> m_stackT;
    private final Stack<TilesLinear> m_stackLinear;
    private final ArrayList<Objective> m_lstObjective;
    private Tiles m_flyingTile;
    private final ArrayList<GameBoardObserver> m_lstObserver;

    public GameBoard() {
        m_lstTuilesGameBoard = new Tiles[7][7];
        m_StackCorner = new Stack<>();
        m_stackT = new Stack<>();
        m_stackLinear = new Stack<>();
        m_lstObjective = new ArrayList<>();
        m_flyingTile = null;
        m_lstObserver = new ArrayList<>();

        initObjective();
        initTuiles();
        initGameBoard();
        placeTile();
        placeObjective();
    }

    /**
     * @param obs : a BoardObserver instance
     */
    public void addObserver(GameBoardObserver obs) {
        m_lstObserver.add(obs);
    }

    /**
     * @param obs : a BoardObserver instance
     */
    public void removeObserver(GameBoardObserver obs) {
        m_lstObserver.remove(obs);
    }

    /**
     * @param pos : Position of the tile
     */
    public void notifyObserverTiles(Position pos) {
        for (GameBoardObserver obs : m_lstObserver) {
            obs.updateTile(pos);
        }
    }

    public void notifyObserverEndGame() {
        for (GameBoardObserver obs : m_lstObserver) {
            obs.endGame();
        }
    }

    /**
     * @return the current flying tile
     */
    public Tiles getFlyTile() {
        return m_flyingTile;
    }

    /**
     * @return the gameboard (the 7x7 list)
     */
    public Tiles[][] getGameBoard() {
        return this.m_lstTuilesGameBoard;
    }

    /**
     * Initialize the game objectives
     */
    private void initObjective() {
        ImageHelper imgHelper = new ImageHelper();
        List<String> lstPath = imgHelper.getPathImg("./img/imgObjectif");
        for (int i = 0; i < 24; i++) {
            if (lstPath.isEmpty()) {
                m_lstObjective.add(new Objective("Objective"+i,"./img/imgObjectif/argent.png"));
            } else {
                m_lstObjective.add(new Objective("Objective"+i, lstPath.getLast()));
                lstPath.removeLast();
            }
        }
        Collections.shuffle(m_lstObjective);
    }

    /**
     * Initialize the tiles
     */
    private void initTuiles() {
        TuileFactory m_factory = new TuileFactory();
        for (int i = 0; i < 20; i++) {
            m_StackCorner.push(m_factory.createTileCorner());
        }
        for (int i = 0; i < 18; i++) {
            m_stackT.push(m_factory.createTileT());
        }
        for (int i = 0; i < 12; i++) {
            m_stackLinear.push(m_factory.createTileLinear());
        }
    }


    /**
     * Initialize the GameBoard
     */
    private void initGameBoard() {
        for (Tiles[] mLstTuilesPlateau : m_lstTuilesGameBoard) {
            Arrays.fill(mLstTuilesPlateau, null);
        }
    }

    /**
     * @param pos : the position of where the current player wants to insert the flying tile
     */
    public void insertFlyingTile(Position pos) {
        int x = pos.getPositionX();
        int y = pos.getPositionY();
        Tiles replacedTile = null;
        if (x == 0) {
            replacedTile = m_lstTuilesGameBoard[y][6];
            for (int i = 6; i > 0; i--) {
                m_lstTuilesGameBoard[y][i] = m_lstTuilesGameBoard[y][i - 1];
            }
            m_lstTuilesGameBoard[y][0] = m_flyingTile;
            m_flyingTile = replacedTile;
            notifyObserverTiles(new Position(0, y));
        }
        // Move tiles to the left
        else if (x == 6) {
            replacedTile = m_lstTuilesGameBoard[y][0];
            for (int i = 0; i < 6; i++) {
                m_lstTuilesGameBoard[y][i] = m_lstTuilesGameBoard[y][i + 1];
            }
            m_lstTuilesGameBoard[y][6] = m_flyingTile;
            m_flyingTile = replacedTile;
            notifyObserverTiles(new Position(6, y));
        }
        // Move tiles to the top
        else if (y == 0) {
            replacedTile = m_lstTuilesGameBoard[6][x];
            for (int i = 6; i > 0; i--) {
                m_lstTuilesGameBoard[i][x] = m_lstTuilesGameBoard[i - 1][x];
            }
            m_lstTuilesGameBoard[0][x] = m_flyingTile;
            m_flyingTile = replacedTile;
            notifyObserverTiles(new Position(x, 0));
        }
        // Move tiles to the bottom
        else if (y == 6) {
            replacedTile = m_lstTuilesGameBoard[0][x];
            for (int i = 0; i < 6; i++) {
                m_lstTuilesGameBoard[i][x] = m_lstTuilesGameBoard[i + 1][x];
            }
            m_lstTuilesGameBoard[6][x] = m_flyingTile;
            m_flyingTile = replacedTile;
            notifyObserverTiles(new Position(x, 6));
        }
    }

    /**
     * Initializes the Tiles on the board at the beginning
     * @param pos : The position of where the tile is going to be initialized
     * @param tiles : A Tile
     *
     */
    private void placeTileOnGameBoardInit(Position pos, Tiles tiles) {
        int x = pos.getPositionX();
        int y = pos.getPositionY();
        m_lstTuilesGameBoard[y][x] = tiles;
    }

    /**
     * Initializes the TilesT on the board
     */
    private void initPlaceTileT() {
        m_stackT.peek().rotate(2);
        this.placeTileOnGameBoardInit(new Position(2, 0), m_stackT.pop());
        m_stackT.peek().rotate(2);
        this.placeTileOnGameBoardInit(new Position(4, 0), m_stackT.pop());
        m_stackT.peek().rotate(3);
        this.placeTileOnGameBoardInit(new Position(6, 2), m_stackT.pop());
        m_stackT.peek().rotate(3);
        this.placeTileOnGameBoardInit(new Position(6, 4), m_stackT.pop());
        this.placeTileOnGameBoardInit(new Position(4, 6), m_stackT.pop());
        this.placeTileOnGameBoardInit(new Position(2, 6), m_stackT.pop());
        m_stackT.peek().rotate();
        this.placeTileOnGameBoardInit(new Position(0, 4), m_stackT.pop());
        m_stackT.peek().rotate();
        this.placeTileOnGameBoardInit(new Position(0, 2), m_stackT.pop());
    }


    /**
     * Initializes the TilesCorner on the board
     */
    private void initPlaceTileCorner() {
        try {
            TilesCorner tuile = m_StackCorner.pop();
            tuile.setObjective(new Objective("blueStart"));
            tuile.setPath("./img/imgDepart/blueTileCorner.png");
            this.placeTileOnGameBoardInit(new Position(0, 0), tuile);

            tuile = m_StackCorner.pop();
            tuile.rotate();
            tuile.setObjective(new Objective("yellowStart"));
            tuile.setPath("./img/imgDepart/yellowTileCorner.png");
            this.placeTileOnGameBoardInit(new Position(6, 0), tuile);

            tuile = m_StackCorner.pop();
            tuile.rotate(3);
            tuile.setObjective(new Objective("redStart"));
            tuile.setPath("./img/imgDepart/redTileCorner.png");
            this.placeTileOnGameBoardInit(new Position(0, 6), tuile);

            tuile = m_StackCorner.pop();
            tuile.rotate(2);
            tuile.setObjective(new Objective("greenStart"));
            tuile.setPath("./img/imgDepart/greenTileCorner.png");
            this.placeTileOnGameBoardInit(new Position(6, 6), tuile);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * It places all the other tiles on the board (the movable ones).
     */

    private void placeTile() {
        initPlaceTileCorner();
        initPlaceTileT();

        Random rand = new Random();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.getGameBoard()[i][j] == null) {
                    boolean placed = false;

                    while (!placed) {
                        int n = rand.nextInt(4);
                        switch (n) {
                            case 0:
                                if (!m_StackCorner.isEmpty()) {
                                    TilesCorner tuile = m_StackCorner.pop();
                                    tuile.rotate(rand.nextInt(4));
                                    this.placeTileOnGameBoardInit(new Position(j, i), tuile);
                                    placed = true;
                                }
                                break;
                            case 1:
                                if (!m_stackT.isEmpty()) {
                                    TilesT tuile = m_stackT.pop();
                                    tuile.rotate(rand.nextInt(4));
                                    this.placeTileOnGameBoardInit(new Position(j, i), tuile);
                                    placed = true;
                                }
                                break;
                            case 2:
                                if (!m_stackLinear.isEmpty()) {
                                    TilesLinear tuile = m_stackLinear.pop();
                                    tuile.rotate(rand.nextInt(4));
                                    this.placeTileOnGameBoardInit(new Position(j, i), tuile);
                                    placed = true;
                                }
                                break;
                        }
                    }
                }
            }
        }
        if (!m_StackCorner.isEmpty()) {
            m_flyingTile = m_StackCorner.pop();
        } else if (!m_stackT.isEmpty()) {
            m_flyingTile = m_stackT.pop();
        } else {
            m_flyingTile = m_stackLinear.pop();
        }
    }

    /**
     * @param pos : The position of the element
     * @return true if the position is in the board, false otherwise
     */
    public Boolean inBoard(Position pos){
        return pos.getPositionX() >= 0 && pos.getPositionX() < 7 && pos.getPositionY() >= 0 && pos.getPositionY() < 7;
    }

    public Tiles getTile(Position pos){
        return m_lstTuilesGameBoard[pos.getPositionY()][pos.getPositionX()];
    }

    public Position outSideBoard(Position pos) {
        int x = pos.getPositionX();
        int y = pos.getPositionY();

        if (x < 0) {
            x = 6;
        } else if (x > 6) {
            x = 0;
        }

        if (y < 0) {
            y = 6;
        } else if (y > 6) {
            y = 0;
        }

        return new Position(x, y);
    }

    private void placeObjective(){
        Position pos;

        for (int i = 0; i < m_lstObjective.size(); i++) {
            pos = Position.generateRandomPosition(7, 7);
            if(getTile(pos).getObjective() != null ) {
                i--;
            }else{

                getTile(pos).setObjective(m_lstObjective.get(i));
            }
        }
    }

    public ArrayList<Objective> getLstObjective(){
        return m_lstObjective;
    }
}
