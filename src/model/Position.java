package model;

import java.util.Random;

public class Position {
    private int m_x;
    private int m_y;
    public Position(){

    }
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

    public Position moveRight(){
        System.out.println("moveRight");
        return new Position(this.m_x + 1, this.m_y);
    }
    public Position moveLeft(){
        System.out.println("moveLeft");
        return new Position(this.m_x - 1, this.m_y);
    }
    public Position moveUp(){
        System.out.println("moveUp");
        return new Position(this.m_x, this.m_y - 1);
    }
    public Position moveDown(){
        System.out.println("moveDown");
        return new Position(this.m_x, this.m_y + 1);
    }

    public static Position generateRandomPosition(int maxX, int maxY){
        Random rand = new Random();
        int x = rand.nextInt(maxX);
        int y = rand.nextInt(maxY);
        return new Position(x, y);
    }

}
