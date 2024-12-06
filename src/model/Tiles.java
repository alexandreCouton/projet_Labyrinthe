package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Tiles {
    private Position m_position; // non utilisé
    private Objective m_objective;
    protected HashMap<Direction, Boolean> m_possibilite;
    protected BufferedImage m_image;
    private ArrayList<TilesObserver> m_observers;

    public Tiles(boolean haut, boolean droite, boolean bas, boolean gauche){
        m_possibilite.put(Direction.UP, true);
        m_possibilite.put(Direction.RIGHT, false);
        m_possibilite.put(Direction.DOWN, false);
        m_possibilite.put(Direction.LEFT, true);
        m_observers = new ArrayList<>();
    }
    public Tiles(Objective objective, boolean haut, boolean droite, boolean bas, boolean gauche){
        this.m_objective = objective;
        this.m_position=null;
        m_possibilite.put(Direction.UP, true);
        m_possibilite.put(Direction.RIGHT, false);
        m_possibilite.put(Direction.DOWN, false);
        m_possibilite.put(Direction.LEFT, true);
        m_observers = new ArrayList<>();

    }

    public void addObserver(TilesObserver observer) {
        m_observers.add(observer);
    }

    public void removeObserver(TilesObserver observer) {
        m_observers.remove(observer);
    }

    public void notifyObserver() {
        for (TilesObserver obs : m_observers) {
            obs.updateRotateTile(this);
        }
    }


    /*public Tiles(Position pos,BufferedImage image){
        this.m_position=pos;
        this.m_image=image;
    }
    public Tiles(Objective objectif,Position pos,BufferedImage image){
        this.m_objective=objectif;
        this.m_position=pos;
        this.m_image=image;
    }*/
    public Position getPositionTuile(){
        return this.m_position;
    }
    public void setPositionTuile(Position position){
        this.m_position=position;
    }
    public Objective getObjectifTuile(){
        return this.m_objective;
    }

    public HashMap<Direction, Boolean> getOuvertureTuile(){
        return m_possibilite;
    }

    public boolean getOpen(Direction direction){
        return m_possibilite.get(direction);
    }

    public void rotate() {
        HashMap<Direction, Boolean> tmp = new HashMap<>();
        tmp.put(Direction.UP, m_possibilite.get(Direction.LEFT));
        tmp.put(Direction.RIGHT, m_possibilite.get(Direction.UP));
        tmp.put(Direction.DOWN, m_possibilite.get(Direction.RIGHT));
        tmp.put(Direction.LEFT, m_possibilite.get(Direction.DOWN));
        m_possibilite = tmp;
        m_image = ImageHelper.rotateClockwise(m_image);
        notifyObserver();
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
