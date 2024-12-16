package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Tiles class represents a tile in the Labyrinth game. It is an abstract class
 * that serves as a base for all specific tile types (e.g., corner tiles, T-shaped tiles).
 * Each tile has certain possible connections (openings) in the four directions: up, right, down, and left.
 * It also handles the rotation of tiles and the notification of observers when a tile is rotated.
 *
 * Key responsibilities include:
 * - Storing the possible openings for each tile in all four directions (up, right, down, left).
 * - Managing the tile's objective (if any).
 * - Handling tile rotation by updating the direction of openings.
 * - Notifying observers when the tile is rotated.
 *
 * The class also provides methods for interacting with the tile's state, such as:
 * - Getting and setting the tile's position.
 * - Checking the openness of the tile in specific directions.
 * - Rotating the tile and notifying observers of the change.
 *
 * This class is the foundation for more specialized tile types, which define the exact layout
 * and behavior of the tiles in the Labyrinth game.
 */

public abstract class Tiles {
    private Position m_position; // non utilisé
    private Objective m_objective;
    protected HashMap<Direction, Boolean> m_possibilite = new HashMap<>();
    private ArrayList<TilesObserver> m_observers;

    private int m_rotateIndex = 0;



    public Tiles(boolean up, boolean right, boolean bottom, boolean left){
        m_possibilite.put(Direction.UP, up);
        m_possibilite.put(Direction.RIGHT, right);
        m_possibilite.put(Direction.DOWN, bottom);
        m_possibilite.put(Direction.LEFT, left);
        m_observers = new ArrayList<>();
    }
    public Tiles(Objective objective, boolean up, boolean right, boolean bottom, boolean left){
        this.m_objective = objective;
        this.m_position=null;
        m_possibilite.put(Direction.UP, up);
        m_possibilite.put(Direction.RIGHT, right);
        m_possibilite.put(Direction.DOWN, bottom);
        m_possibilite.put(Direction.LEFT, left);
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
     * @return the position of the Tile
     */
    public Position getPositionTuile(){
        return this.m_position;
    }

    /**
     * Set the position of the tile
     * @param position : The position of the Tile
     */
    public void setPositionTuile(Position position){
        this.m_position=position;
    }

    /**
     * @return the tile's objective if it has one
     */
    public Objective getObjectifTuile(){
        return this.m_objective;
    }


    /**
     * @param direction : the direction of the Tile
     * @return if the tile is opened on this direction or note
     */
    public boolean getOpen(Direction direction){
        return m_possibilite.get(direction);
    }

    /**
     * Rotates the tile and changes the possibilities
     */
    public void rotate() {
        HashMap<Direction, Boolean> tmp = new HashMap<>();
        tmp.put(Direction.UP, m_possibilite.get(Direction.LEFT));
        tmp.put(Direction.RIGHT, m_possibilite.get(Direction.UP));
        tmp.put(Direction.DOWN, m_possibilite.get(Direction.RIGHT));
        tmp.put(Direction.LEFT, m_possibilite.get(Direction.DOWN));
        m_possibilite = tmp;
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

    public void toStrPossibilite(){
        System.out.println("Up : "+m_possibilite.get(Direction.UP));
        System.out.println("Right : "+m_possibilite.get(Direction.RIGHT));
        System.out.println("Down : "+m_possibilite.get(Direction.DOWN));
        System.out.println("Left : "+m_possibilite.get(Direction.LEFT));
    }

    public void setObjective(Objective obj){
        this.m_objective=obj;
    }

    public Objective getObjective(){
        return this.m_objective;
    }

    public abstract void setPath(String path);

}
