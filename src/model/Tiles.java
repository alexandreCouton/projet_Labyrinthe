package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Tiles class represents a tile in the Labyrinth game. It is an abstract class
 * that serves as a base for all specific tile types (e.g., corner tiles, T-shaped tiles).
 * Each tile has certain possible connections (openings) in the four directions: up, right, down, and left.
 * It also handles the rotation of tiles and the notification of observers when a tile is rotated.<br>
 *<br>
 * Key responsibilities include:<br>
 * - Storing the possible openings for each tile in all four directions (up, right, down, left).<br>
 * - Managing the tile's objective (if any).<br>
 * - Handling tile rotation by updating the direction of openings.<br>
 * - Notifying observers when the tile is rotated.<br>
 *<br>
 * The class also provides methods for interacting with the tile's state, such as:<br>
 * - Getting and setting the tile's position.<br>
 * - Checking the openness of the tile in specific directions.<br>
 * - Rotating the tile and notifying observers of the change.<br>
 *<br>
 * This class is the foundation for more specialized tile types, which define the exact layout<br>
 * and behavior of the tiles in the Labyrinth game.<br>
 */

public abstract class Tiles {
    private Position m_position;
    private Objective m_objective;
    protected HashMap<Direction, Boolean> m_openDirections = new HashMap<>();
    private ArrayList<TilesObserver> m_observers;

    private int m_rotateIndex = 0;


    /**
     * Constructs a tile with specified open directions.
     *
     * @param up    true if the tile is open at the top
     * @param right true if the tile is open on the right
     * @param bottom true if the tile is open at the bottom
     * @param left  true if the tile is open on the left
     */
    public Tiles(boolean up, boolean right, boolean bottom, boolean left){
        m_openDirections.put(Direction.UP, up);
        m_openDirections.put(Direction.RIGHT, right);
        m_openDirections.put(Direction.DOWN, bottom);
        m_openDirections.put(Direction.LEFT, left);
        m_observers = new ArrayList<>();
    }
    /**
     * Constructs a tile with an associated objective and specified open directions.
     *
     * @param objective the objective associated with the tile
     * @param up        true if the tile is open at the top
     * @param right     true if the tile is open on the right
     * @param bottom    true if the tile is open at the bottom
     * @param left      true if the tile is open on the left
     */
    public Tiles(Objective objective, boolean up, boolean right, boolean bottom, boolean left){
        this.m_objective = objective;
        this.m_position=null;
        m_openDirections.put(Direction.UP, up);
        m_openDirections.put(Direction.RIGHT, right);
        m_openDirections.put(Direction.DOWN, bottom);
        m_openDirections.put(Direction.LEFT, left);
        m_observers = new ArrayList<>();

    }

    /**
     * @param observer : TilesObserver to observe the Tile
     */
    public void addObserver(TilesObserver observer) {
        m_observers.add(observer);
    }

    /**
     * @param observer : TilesObserver you want to remove
     */
    public void removeObserver(TilesObserver observer) {
        m_observers.remove(observer);
    }

    /**
     * Notify the observer
     */
    public void notifyObserver() {
        for (TilesObserver obs : m_observers) {
            obs.updateRotateTile(this);
        }
    }

    /**
     * @param direction : the direction of the Tile
     * @return if the tile is opened on this direction or note
     */
    public boolean getOpen(Direction direction){
        return m_openDirections.get(direction);
    }

    /**
     * Rotates the tile and changes the possibilities
     */
    public void rotate() {
        HashMap<Direction, Boolean> tmp = new HashMap<>();
        tmp.put(Direction.UP, m_openDirections.get(Direction.LEFT));
        tmp.put(Direction.RIGHT, m_openDirections.get(Direction.UP));
        tmp.put(Direction.DOWN, m_openDirections.get(Direction.RIGHT));
        tmp.put(Direction.LEFT, m_openDirections.get(Direction.DOWN));
        m_openDirections = tmp;
        m_rotateIndex++;
        m_rotateIndex = m_rotateIndex % 4;
        notifyObserver();
    }

    /**
     * Rotates the tile and changes the possibilities
     * @param n : the number of clockwise you want
     */
    public void rotate(int n) {
        for (int i = 0; i < n; i++) {
            rotate();
        }
    }

    /**
     * @return get the path of the Tile's image
     */
    public abstract String getPath();

    /**
     * @return get the rotateIndex
     */
    public int getRotateIndex(){
        return m_rotateIndex;
    }
    /**
     * Checks if a move from this tile to another tile in the specified direction is possible.
     *
     * @param direction the direction of the move
     * @param tile      the tile to which the move is made
     * @return true if the move is possible, false otherwise
     */
    public boolean moveTile(Direction direction, Tiles tile) {
        if (!this.getOpen(direction)) {
            return false;
        }
        return switch (direction) {
            case UP -> {
                if (tile.getOpen(Direction.DOWN)) {
                    yield true;
                }
                yield false;
            }
            case RIGHT -> {
                if (tile.getOpen(Direction.LEFT)) {
                    yield true;
                }
                yield false;
            }
            case DOWN -> {
                if (tile.getOpen(Direction.UP)) {
                    yield true;
                }
                yield false;
            }
            case LEFT -> {
                if (tile.getOpen(Direction.RIGHT)) {
                    yield true;
                }
                yield false;
            }
        };
    }



    public void setObjective(Objective obj){
        this.m_objective=obj;
    }

    public Objective getObjective(){
        return this.m_objective;
    }

    public abstract void setPath(String path);

}
