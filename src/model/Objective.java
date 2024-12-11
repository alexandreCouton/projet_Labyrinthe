package model;

public class Objective {
    private String m_path;
    private String m_name;
    private Position m_position;

    public Objective(String nom){
        this.m_name = nom;
    }
    public Objective(String name, String path){
        this.m_name= name;
        this.m_path=path;
    }

    /**
     * @return the name of the objective
     */
    public String getNameObjectif(){
        return m_name;
    }

    /**
     * @return path : the path of the image
     */
    public String getPath(){
        return m_path;
    }

    public void setPosition(Position pos){
        this.m_position = pos;
    }

    public Position  getPosition(){
        return m_position;
    }


}
