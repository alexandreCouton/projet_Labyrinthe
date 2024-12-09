package model;

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
}
