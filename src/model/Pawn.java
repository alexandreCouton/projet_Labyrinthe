package model;

public class Pawn {
    private String m_path;
    private Position m_position;

    public Pawn(String path, Position position){
        this.m_path = path;
    }

    /**
     * @param path : path of the image
     */
    public void setPath(String path){
        this.m_path = path;
    }

    /**
     * @return the path of the image
     */
    public String getPath() {
        return m_path;
    }

    /**
     * @param m_position : the position where the Pawn should be
     */
    public void setPosition(Position m_position) {
        this.m_position = m_position;
    }

    /**
     * @return get the pawn's position
     */
    public Position getPosition() {
        return m_position;
    }

    /**
     * Move the pawn to the right
     */
    public void goRight(){
        m_position.setPositionX(m_position.getPositionX() + 1);
    }
    /**
     * Move the pawn to the left
     */
    public void goLeft(){
        m_position.setPositionX(m_position.getPositionX() - 1);
    }
    /**
     * Move the pawn to the top
     */
    public void goUp(){
        m_position.setPositionY(m_position.getPositionY() - 1);
    }
    /**
     * Move the pawn to the bottom
     */
    public void goDown(){
        m_position.setPositionY(m_position.getPositionY() + 1);
    }

    /**
     * @param x : set the X position
     */
    public void setPositionX(int x){
        this.m_position.setPositionX(x);
    }

    /**
     * @param y : set the Y position
     */
    public void setPositionY(int y){
        this.m_position.setPositionY(y);
    }

    public void toStr(){
        System.out.println("Pawn position : "+ m_position.getPositionX() +" "+ m_position.getPositionY());
    }

}

