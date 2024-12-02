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
    public Tuile(BufferedImage image, HashMap<TuileOuverture, Boolean> possibilite,Objectif objectif){
        this.m_objectif=objectif;
        this.m_position=null;
        this.m_image=image;
        this.m_possibilite = possibilite;
    }


    /*public Tuile(Position pos,BufferedImage image){
        this.m_position=pos;
        this.m_image=image;
    }
    public Tuile(Objectif objectif,Position pos,BufferedImage image){
        this.m_objectif=objectif;
        this.m_position=pos;
        this.m_image=image;
    }*/
    public Position getPositionTuile(){
        return this.m_position;
    }
    public void setPositionTuile(Position position){
        this.m_position=position;
    }
    public Objectif getObjectifTuile(){
        return this.m_objectif;
    }

    public HashMap<TuileOuverture, Boolean> getOuvertureTuile(){
        return m_possibilite;
    }
    public void rotate() {
        HashMap<TuileOuverture, Boolean> tmp = new HashMap<>();
        tmp.put(TuileOuverture.HAUT, m_possibilite.get(TuileOuverture.GAUCHE));
        tmp.put(TuileOuverture.DROITE, m_possibilite.get(TuileOuverture.HAUT));
        tmp.put(TuileOuverture.BAS, m_possibilite.get(TuileOuverture.DROITE));
        tmp.put(TuileOuverture.GAUCHE, m_possibilite.get(TuileOuverture.BAS));
        m_possibilite = tmp;
        m_image = ImageHelper.rotateClockwise(m_image);
    }

    public BufferedImage getImage(){
        return m_image;
    }
    public BufferedImage setImage(BufferedImage image){
        return m_image=image;
    }
    public void rotate(int n) {
        for (int i = 0; i < n; i++) {
            rotate();
        }
    }
}
