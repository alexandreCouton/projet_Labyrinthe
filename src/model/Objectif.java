package model;

public class Objectif {
    private String m_path;
    private String m_nom;

    public Objectif(String nom){
        this.m_nom = nom;
    }
    public Objectif(String nom, String path){
        this.m_nom=nom;
        this.m_path=path;
    }
    public String getNomObjectif(){
        return m_nom;
    }


}
