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
    public void setPositionX(int x){
        this.m_x = x;
    }
    public void setPositionY(int y){
        this.m_y = y;
    }
    public int getPositionX(){
        return this.m_x;
    }
    public int getPositionY(){
        return this.m_y;
    }
}
