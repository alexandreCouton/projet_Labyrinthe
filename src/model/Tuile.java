package model;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class Tuile {
    private Position m_position;
    private Objectif m_objectif;
    protected HashMap<Direction, Boolean> m_possibilite;
    protected BufferedImage m_image;

    public Tuile(boolean haut, boolean droite, boolean bas, boolean gauche){
        m_possibilite.put(Direction.HAUT, true);
        m_possibilite.put(Direction.DROITE, false);
        m_possibilite.put(Direction.BAS, false);
        m_possibilite.put(Direction.GAUCHE, true);
    }
    public Tuile(Objectif objectif, boolean haut, boolean droite, boolean bas, boolean gauche){
        this.m_objectif=objectif;
        this.m_position=null;
        m_possibilite.put(Direction.HAUT, true);
        m_possibilite.put(Direction.DROITE, false);
        m_possibilite.put(Direction.BAS, false);
        m_possibilite.put(Direction.GAUCHE, true);
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

    public HashMap<Direction, Boolean> getOuvertureTuile(){
        return m_possibilite;
    }

    public boolean getOpen(Direction direction){
        return m_possibilite.get(direction);
    }

    public void rotate() {
        HashMap<Direction, Boolean> tmp = new HashMap<>();
        tmp.put(Direction.HAUT, m_possibilite.get(Direction.GAUCHE));
        tmp.put(Direction.DROITE, m_possibilite.get(Direction.HAUT));
        tmp.put(Direction.BAS, m_possibilite.get(Direction.DROITE));
        tmp.put(Direction.GAUCHE, m_possibilite.get(Direction.BAS));
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
