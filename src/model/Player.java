package model;

import java.util.ArrayList;


public class Player {
    private String m_nom;
    private ArrayList<Objective> m_lstObjective;
    //En int car on s'en fiche de connaitre ceux qu'il a deja eu vu qu'ils disparaiteront de la liste au m_lstobjective
    private int m_objectifCapture;
    private Pawn m_pawn;
    private ArrayList<PlayerObserver> m_lstObserver;

    public Player(String nom){
        this.m_nom = nom;
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
     * @param pos : Pos of the player's pawn (for the view)
     */
    public void notifyObserver(Position pos){
        for(PlayerObserver obs : m_lstObserver){
            obs.movePlayer(pos, m_pawn.getPath());
        }
    }

    /**
     * @param lstObjective : Set the objectives for the player
     */
    private void setLstObjectif(ArrayList<Objective> lstObjective){
        m_lstObjective = lstObjective;
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
     * @param deplacement : the direction of where the player's pawn has to move on
     */
    public void deplacer(Direction deplacement){
        switch (deplacement) {
            case UP:
                m_pawn.goUp();
                if(m_pawn.getPosition().getPositionY() < 0){
                    m_pawn.setPositionY(6);
                }
                notifyObserver(m_pawn.getPosition());
                break;
            case DOWN:
                m_pawn.goDown();
                if(m_pawn.getPosition().getPositionY() > 6){
                    m_pawn.setPositionY(0);
                }
                notifyObserver(m_pawn.getPosition());

                break;
            case LEFT:
                m_pawn.goLeft();
                if(m_pawn.getPosition().getPositionX() < 0){
                    m_pawn.setPositionX(6);
                }
                notifyObserver(m_pawn.getPosition());

                break;
            case RIGHT:
                m_pawn.goRight();
                if(m_pawn.getPosition().getPositionX() > 6){
                    m_pawn.setPositionX(0);
                }
                notifyObserver(m_pawn.getPosition());

                break;
        }
    }

    public void move(Position pos){
        m_pawn.setPosition(pos);
        notifyObserver(m_pawn.getPosition());
    }

    /**
     * @param path : the pawn's image path
     */
    public void setImgPion(String path){
        m_pawn.setPath(path);
    }

    /**
     * @return the pawn's path
     */
    public String getImgPion(){
        return m_pawn.getPath();
    }

    /**
     * @param m_position : the position of where the player's pawn has to be
     */
    public void setPionPosition(Position m_position) {
        this.m_pawn.setPosition(m_position);
    }


    /**
     *
     */
    public void captureObjectif(){
        for(Objective objective : m_lstObjective){
            if(objective.getPosition() == this.getPosition()){
                m_lstObjective.remove(objective);
                m_objectifCapture++;
            }
        }
    }
}
