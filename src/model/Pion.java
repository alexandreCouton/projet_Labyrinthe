package model;

public class Pion {
    private String m_path;
    private Position m_position;

    public Pion(String path, Position position){
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

    public Position getM_position() {
        return m_position;
    }

    public void aDroite(){
        m_position.setPositionX(m_position.getPositionX() + 1);
    }
    public void aGauche(){
        m_position.setPositionX(m_position.getPositionX() - 1);
    }
    public void enHaut(){
        m_position.setPositionY(m_position.getPositionY() - 1);
    }
    public void enBas(){
        m_position.setPositionY(m_position.getPositionY() + 1);
    }
    public void setPositionX(int x){
        this.m_position.setPositionX(x);
    }
    public void setPositionY(int y){
        this.m_position.setPositionY(y);
    }

}
