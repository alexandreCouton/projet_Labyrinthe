package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Tiles {
    private Position m_position; // non utilisé
    private Objective m_objective;
    protected HashMap<Direction, Boolean> m_possibilite = new HashMap<>();
    private ArrayList<TilesObserver> m_observers;

    private int m_rotateIndex = 0;



    public Tiles(boolean up, boolean right, boolean bottom, boolean left){
        m_possibilite.put(Direction.UP, up);
        m_possibilite.put(Direction.RIGHT, right);
        m_possibilite.put(Direction.DOWN, bottom);
        m_possibilite.put(Direction.LEFT, left);
        m_observers = new ArrayList<>();
    }
    public Tiles(Objective objective, boolean up, boolean right, boolean bottom, boolean left){
        this.m_objective = objective;
        this.m_position=null;
        m_possibilite.put(Direction.UP, up);
        m_possibilite.put(Direction.RIGHT, right);
        m_possibilite.put(Direction.DOWN, bottom);
        m_possibilite.put(Direction.LEFT, left);
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
        m_rotateIndex++;
        notifyObserver();
    }

    public void rotate(int n) {
        for (int i = 0; i < n; i++) {
            rotate();
        }
    }

    public abstract String getPath();

    public int getRotateIndex(){
        return m_rotateIndex;
    }
}
