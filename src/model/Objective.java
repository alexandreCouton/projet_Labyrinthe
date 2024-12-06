package model;

public class Objective {
    private String m_path;
    private String m_name;

    public Objective(String nom){
        this.m_name = nom;
    }
    public Objective(String name, String path){
        this.m_name= name;
        this.m_path=path;
    }
    public String getNameObjectif(){
        return m_name;
    }


}
