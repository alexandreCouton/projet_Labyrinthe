package model;

public class Pawn {
    private String m_path;
    private Position m_position;
    private Position m_startPosition;

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

    public void toStr(){
        System.out.println("Pawn position : "+ m_position.getPositionX() +" "+ m_position.getPositionY());
    }

    public void setStartPosition(Position pos){
        this.m_startPosition = pos;
        this.m_position = pos;
    }

    public Position getStartPosition(){
        return m_startPosition;
    }

}

