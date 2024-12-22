package model;

import java.util.ArrayList;

/**
 * The Player class represents a player in the game.
 * Each player has a name, a list of objectives, a pawn,
 * and observers to track their pawn's movements.
 */
public class Player {
    private String m_name;
    private ArrayList<Objective> m_lstObjective;
    private int m_objectiveCapture;
    private Pawn m_pawn;
    private ArrayList<PlayerObserver> m_lstObserver;
    /**
     * Constructs a Player with the specified name.
     * Initializes the player's pawn, objectives, and observers.
     *
     * @param nom the name of the player
     */
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
    /**
     * Gets the X-coordinate of the player's pawn's position.
     *
     * @return the X-coordinate of the pawn's position
     */
    public int getPositionX(){
        return m_pawn.getPosition().getPositionX();
    }
    /**
     * Gets the Y-coordinate of the player's pawn's position.
     *
     * @return the Y-coordinate of the pawn's position
     */
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

    /**
     * Removes an objective from the player's list and increments the captured count.
     *
     * @param obj the objective to capture
     */
    public void captureObjective(Objective obj){
        m_lstObjective.remove(obj);
        m_objectiveCapture++;
    }
    /**
     * Checks if the player has captured all their objectives.
     *
     * @return true if all objectives are captured, false otherwise
     */
    public Boolean allObjectiveCapture(){
        if(m_lstObjective.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Sets the starting position of the player's pawn.
     *
     * @param pos the starting position
     */
    public void setStartPos(Position pos){
        m_pawn.setStartPosition(pos);
    }
    /**
     * Checks if the specified position matches the pawn's starting position.
     *
     * @param pos the position to check
     * @return true if the position matches the starting position, false otherwise
     */
    public Boolean isStartPos(Position pos){
        return m_pawn.getStartPosition().getPositionY() == pos.getPositionY() && m_pawn.getStartPosition().getPositionX() == pos.getPositionX();
    }
    /**
     * Gets the image path of the player's pawn.
     *
     * @return the path to the pawn's image
     */
    public String getPath(){
        return m_pawn.getPath();
    }
    /**
     * Returns the player's name.
     *
     * @return the player's name
     */
    public String toString(){
        return m_name;
    }
    /**
     * Gets the starting position of the player's pawn.
     *
     * @return the starting position of the pawn
     */
    public Position getStartPos(){
        return m_pawn.getStartPosition();
    }
}
