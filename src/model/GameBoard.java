package model;

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
    private final Tiles[][] m_lstTuilesPlateaus;
    private final Stack<TilesCorner> m_StackAngle;
    private final Stack<TilesT> m_stackT;
    private final Stack<TilesLinear> m_stackDroite;
    private Stack<Objective> m_stackObjective;
    private Tiles m_flyingTile;
    private ArrayList<PlateauObserver> m_lstObserver;

    public GameBoard() {
        m_lstTuilesPlateaus = new Tiles[7][7];
        m_StackAngle = new Stack<>();
        m_stackT = new Stack<>();
        m_stackDroite = new Stack<>();
        m_stackObjective = new Stack<>();
        m_flyingTile = null;
        m_lstObserver = new ArrayList<>();

        initObjective();
        initTuiles();
        initPlateau();
        placerTuile();
    }

    /**
     * @param obs : a BoardObserver instance
     */
    public void addObserver(PlateauObserver obs) {
        m_lstObserver.add(obs);
    }

    /**
     * @param obs : a BoardObserver instance
     */
    public void removeObserver(PlateauObserver obs) {
        m_lstObserver.remove(obs);
    }

    /**
     * @param pos : Position of the tile
     */
    public void notifyObserverTiles(Position pos) {
        for (PlateauObserver obs : m_lstObserver) {
            obs.updateTile(pos);
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
        return this.m_lstTuilesPlateaus;
    }

    /**
     * Initialize the game objectives
     */
    private void initObjective() {
        ImageHelper imgHelper = new ImageHelper();
        List<String> lstPath = imgHelper.getPathImg("../../img/imgObjectif");
        for (int i = 0; i < 24; i++) {
            if (lstPath.isEmpty()) {
                lstPath = imgHelper.getPathImg("../../img/imgObjectif/argent.png");
            } else {
                m_stackObjective.push(new Objective(lstPath.remove(lstPath.size() - 1)));
            }
        }
        Collections.shuffle(m_stackObjective);
    }

    /**
     * Initialize the tiles
     */
    private void initTuiles() {
        TuileFactory m_factory = new TuileFactory();
        for (int i = 0; i < 20; i++) {
            m_StackAngle.push(m_factory.createTileCorner());
        }
        for (int i = 0; i < 18; i++) {
            m_stackT.push(m_factory.createTileT());
        }
        for (int i = 0; i < 12; i++) {
            m_stackDroite.push(m_factory.createTileLinear());
        }
    }


    /**
     * Initialize the GameBoard
     */
    private void initPlateau() {
        for (int y = 0; y < m_lstTuilesPlateaus.length; y++) {
            for (int x = 0; x < m_lstTuilesPlateaus[y].length; x++) {
                m_lstTuilesPlateaus[y][x] = null;
            }
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
            replacedTile = m_lstTuilesPlateaus[y][6];
            for (int i = 6; i > 0; i--) {
                m_lstTuilesPlateaus[y][i] = m_lstTuilesPlateaus[y][i - 1];
            }
            m_lstTuilesPlateaus[y][0] = m_flyingTile;

            notifyObserverTiles(new Position(0, y));
        }
        // Move tiles to the left
        else if (x == 6) {
            replacedTile = m_lstTuilesPlateaus[y][0];
            for (int i = 0; i < 6; i++) {
                m_lstTuilesPlateaus[y][i] = m_lstTuilesPlateaus[y][i + 1];
                notifyObserverTiles(new Position(i, y));
            }
            m_lstTuilesPlateaus[y][6] = m_flyingTile;
            notifyObserverTiles(new Position(6, y));
        }
        // Move tiles to the top
        else if (y == 0) {
            replacedTile = m_lstTuilesPlateaus[6][x];
            for (int i = 6; i > 0; i--) {
                m_lstTuilesPlateaus[i][x] = m_lstTuilesPlateaus[i - 1][x];
                notifyObserverTiles(new Position(x, i));
            }
            m_lstTuilesPlateaus[0][x] = m_flyingTile;
            notifyObserverTiles(new Position(x, 0));
        }
        // Move tiles to the bottom
        else if (y == 6) {
            replacedTile = m_lstTuilesPlateaus[0][x];
            for (int i = 0; i < 6; i++) {
                m_lstTuilesPlateaus[i][x] = m_lstTuilesPlateaus[i + 1][x];
                notifyObserverTiles(new Position(x, i));
            }
            m_lstTuilesPlateaus[6][x] = m_flyingTile;
            notifyObserverTiles(new Position(x, 6));
        }

        m_flyingTile = replacedTile;
    }

    /**
     * Initializes the Tiles on the board at the beginning
     * @param pos : The position of where the tile is going to be initialized
     * @param tiles : A Tile
     *
     */
    private void placerTuileSurPlateauInit(Position pos, Tiles tiles) {
        int x = pos.getPositionX();
        int y = pos.getPositionY();
        m_lstTuilesPlateaus[y][x] = tiles;
    }

    /**
     * Initializes the TilesT on the board
     */
    private void initPlaceTuileT() {
        m_stackT.peek().rotate(2);
        this.placerTuileSurPlateauInit(new Position(2, 0), m_stackT.pop());
        m_stackT.peek().rotate(2);
        this.placerTuileSurPlateauInit(new Position(4, 0), m_stackT.pop());
        m_stackT.peek().rotate(3);
        this.placerTuileSurPlateauInit(new Position(6, 2), m_stackT.pop());
        m_stackT.peek().rotate(3);
        this.placerTuileSurPlateauInit(new Position(6, 4), m_stackT.pop());
        this.placerTuileSurPlateauInit(new Position(4, 6), m_stackT.pop());
        this.placerTuileSurPlateauInit(new Position(2, 6), m_stackT.pop());
        m_stackT.peek().rotate();
        this.placerTuileSurPlateauInit(new Position(0, 4), m_stackT.pop());
        m_stackT.peek().rotate();
        this.placerTuileSurPlateauInit(new Position(0, 2), m_stackT.pop());
    }


    /**
     * Initializes the TilesCorner on the board
     */
    private void initPlaceTuileAng() {
        try {
            TilesCorner tuile = m_StackAngle.pop();

            this.placerTuileSurPlateauInit(new Position(0, 0), tuile);

            tuile = m_StackAngle.pop();
            tuile.rotate();
            this.placerTuileSurPlateauInit(new Position(6, 0), tuile);

            tuile = m_StackAngle.pop();
            tuile.rotate(3);
            this.placerTuileSurPlateauInit(new Position(0, 6), tuile);

            tuile = m_StackAngle.pop();
            tuile.rotate(2);
            this.placerTuileSurPlateauInit(new Position(6, 6), tuile);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * It places all the other tiles on the board (the movable ones).
     */

    private void placerTuile() {
        initPlaceTuileAng();
        initPlaceTuileT();

        Random rand = new Random();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.getGameBoard()[i][j] == null) {
                    boolean placed = false;

                    while (!placed) {
                        int n = rand.nextInt(3);
                        switch (n) {
                            case 0:
                                if (!m_StackAngle.isEmpty()) {
                                    TilesCorner tuile = m_StackAngle.pop();
                                    tuile.rotate(rand.nextInt(3));
                                    this.placerTuileSurPlateauInit(new Position(j, i), tuile);
                                    placed = true;
                                }
                                break;
                            case 1:
                                if (!m_stackT.isEmpty()) {
                                    TilesT tuile = m_stackT.pop();
                                    tuile.rotate(rand.nextInt(3));
                                    this.placerTuileSurPlateauInit(new Position(j, i), tuile);
                                    placed = true;
                                }
                                break;
                            case 2:
                                if (!m_stackDroite.isEmpty()) {
                                    TilesLinear tuile = m_stackDroite.pop();
                                    tuile.rotate(rand.nextInt(3));
                                    this.placerTuileSurPlateauInit(new Position(j, i), tuile);
                                    placed = true;
                                }
                                break;
                        }
                    }
                }
            }
        }
        if (!m_StackAngle.isEmpty()) {
            m_flyingTile = m_StackAngle.pop();
        } else if (!m_stackT.isEmpty()) {
            m_flyingTile = m_stackT.pop();
        } else {
            m_flyingTile = m_stackDroite.pop();
        }
    }



    public void placerTuileSurPlateau(Position pos, Tiles tile){

    }
}
