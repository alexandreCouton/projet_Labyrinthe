package model;

import java.util.Random;
/**
 * The Position class represents a coordinate in a two-dimensional grid.
 * It includes methods for accessing and modifying X and Y coordinates,
 * as well as utility methods for movement and generating random positions.
 */
public class Position {
    private int m_x;
    private int m_y;
    public Position(){

    }

    /**
     * Constructs a Position with specified X and Y coordinates.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     */
    public Position(int x, int y){
        this.m_x=x;
        this.m_y=y;
    }

    /**
     * @param x : Set the x position
     */
    public void setPositionX(int x){
        this.m_x = x;
    }
    /**
     * @param y : Set the y position
     */
    public void setPositionY(int y){
        this.m_y = y;
    }

    /**
     * @return the X position
     */
    public int getPositionX(){
        return this.m_x;
    }

    /**
     * @return the Y position
     */
    public int getPositionY(){
        return this.m_y;
    }
    /**
     * Moves the position one unit to the right.
     *
     * @return a new Position object with the X coordinate incremented by 1
     */
    public Position moveRight(){
        return new Position(this.m_x + 1, this.m_y);
    }
    /**
     * Moves the position one unit to the left.
     *
     * @return a new Position object with the X coordinate decremented by 1
     */
    public Position moveLeft(){
        return new Position(this.m_x - 1, this.m_y);
    }
    /**
     * Moves the position one unit up.
     *
     * @return a new Position object with the Y coordinate decremented by 1
     */
    public Position moveUp(){
        return new Position(this.m_x, this.m_y - 1);
    }
    /**
     * Moves the position one unit down.
     *
     * @return a new Position object with the Y coordinate incremented by 1
     */
    public Position moveDown(){
        return new Position(this.m_x, this.m_y + 1);
    }
    /**
     * Generates a random Position within the specified bounds.
     *
     * @param maxX the upper bound (exclusive) for the X coordinate
     * @param maxY the upper bound (exclusive) for the Y coordinate
     * @return a new Position object with random X and Y coordinates
     */
    public static Position generateRandomPosition(int maxX, int maxY){
        Random rand = new Random();
        int x = rand.nextInt(maxX);
        int y = rand.nextInt(maxY);
        return new Position(x, y);
    }

}
