package model;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class Tuile {
    private Position m_position;
    private Objectif m_objectif;
    protected HashMap<TuileOuverture, Boolean> m_possibilite;
    protected BufferedImage m_image;

    public Tuile(BufferedImage image, HashMap<TuileOuverture, Boolean> possibilite){
        this.m_objectif=null;
        this.m_position=null;
        this.m_image=image;
        this.m_possibilite = possibilite;
    }
    public Tuile(Position pos,BufferedImage image){
        this.m_position=pos;
        this.m_image=image;
    }
    public Tuile(Objectif objectif,Position pos,BufferedImage image){
        this.m_objectif=objectif;
        this.m_position=pos;
        this.m_image=image;
    }
    public Position getPositionTuile(){
        return this.m_position;
    }
    public Objectif getObjectifTuile(){
        return this.m_objectif;
    }

    public HashMap<TuileOuverture, Boolean> getOuvertureTuile(){
        return m_possibilite;
    }
    public abstract void rotate();
}
