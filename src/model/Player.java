package model;

import java.util.ArrayList;


public class Player {
    private String m_name;
    private ArrayList<Objective> m_lstObjective;
    //En int car on s'en fiche de connaitre ceux qu'il a deja eu vu qu'ils disparaiteront de la liste au m_lstobjective
    private int m_objectiveCapture;
    private Pawn m_pawn;
    private ArrayList<PlayerObserver> m_lstObserver;

    public Player(String nom){
        this.m_name = nom;
        m_pawn = new Pawn("src/img/pawn.png", new Position(0,0));
        m_lstObjective = new ArrayList<>();
        m_lstObserver = new ArrayList<>();
    }

    /**
     * @param obs : add a PlayerObserver to watch the player's pawn
     */
    public void addObserver(PlayerObserver obs){
        m_lstObserver.add(obs);
    }

    /**
     * @param obs : the PlayerObserver to remove
     */
    public void removeObserver(PlayerObserver obs){
        m_lstObserver.remove(obs);
    }


    /**
     * @param oldPos : the old position of the player's pawn
     * @param newPos : the new position of the player's pawn
     */
    public void notifyObserver(Position oldPos, Position newPos){
        for(PlayerObserver obs : m_lstObserver){
            obs.movePlayer(oldPos, newPos, m_pawn.getPath());
        }
    }


    /**
     * @param lstObjective : Set the objectives for the player
     */
    protected void setLstObjective(ArrayList<Objective> lstObjective){
        this.m_lstObjective = lstObjective;
    }

    public ArrayList<Objective> getLstObjective(){
        return m_lstObjective;
    }

    /**
     * @return the position of the player's pawn
     */
    public Position getPosition(){
        return m_pawn.getPosition();
    }

    public int getPositionX(){
        return m_pawn.getPosition().getPositionX();
    }

    public int getPositionY(){
        return m_pawn.getPosition().getPositionY();
    }


    /**
     * @param pos
     * Move the player's pawn to the position pos
     */
    public void move(Position pos){
        notifyObserver(m_pawn.getPosition(), pos);
        m_pawn.setPosition(pos);
    }

    /**
     * @param path : the pawn's image path
     */
    public void setImgPawn(String path){
        m_pawn.setPath(path);
    }

    /**
     * @return the pawn's path
     */
    public String getImgPawn(){
        return m_pawn.getPath();
    }

    /**
     * @param m_position : the position of where the player's pawn has to be
     */
    public void setPawnPosition(Position m_position) {
        notifyObserver(m_pawn.getPosition(), m_position);
        this.m_pawn.setPosition(m_position);
    }

    public void captureObjective(Objective obj){
        m_lstObjective.remove(obj);
        m_objectiveCapture++;
    }

    public Boolean allObjectiveCapture(){
        if(m_lstObjective.isEmpty()){
            return true;
        }
        return false;
    }

    public void setStartPos(Position pos){
        m_pawn.setStartPosition(pos);
    }

    public Boolean isStartPos(Position pos){
        return m_pawn.getStartPosition().getPositionY() == pos.getPositionY() && m_pawn.getStartPosition().getPositionX() == pos.getPositionX();
    }

    public String getPath(){
        return m_pawn.getPath();
    }

    public String toString(){
        return m_name;
    }

    public Position getStartPos(){
        return m_pawn.getStartPosition();
    }
}
