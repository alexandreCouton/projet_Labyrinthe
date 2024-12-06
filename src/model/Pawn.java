package model;

public class Pawn {
    private String m_path;
    private Position m_position;

    public Pawn(String path, Position position){
        this.m_path = path;
    }

    public void setPath(String path){
        this.m_path = path;
    }

    public String getPath() {
        return m_path;
    }

    public void setPosition(Position m_position) {
        this.m_position = m_position;
    }

    public Position getPosition() {
        return m_position;
    }

    public void goRight(){
        m_position.setPositionX(m_position.getPositionX() + 1);
    }
    public void goLeft(){
        m_position.setPositionX(m_position.getPositionX() - 1);
    }
    public void goUp(){
        m_position.setPositionY(m_position.getPositionY() - 1);
    }
    public void goDown(){
        m_position.setPositionY(m_position.getPositionY() + 1);
    }
    public void setPositionX(int x){
        this.m_position.setPositionX(x);
    }
    public void setPositionY(int y){
        this.m_position.setPositionY(y);
    }

}

