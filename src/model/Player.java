package model;

import java.util.ArrayList;


public class Player {
    private String m_nom;
    private ArrayList<Objective> m_lstObjective;
    //En int car on s'en fiche de connaitre ceux qu'il a deja eu vu qu'ils disparaiteront de la liste au m_lstobjective
    private int m_objectifCapture;
    private Pawn m_pawn;

    public Player(String nom){
        this.m_nom = nom;
        m_pawn = new Pawn("src/img/pion.png", new Position(0,0));
    }

    private void setLstObjectif(ArrayList<Objective> lstObjective){
        m_lstObjective = lstObjective;
    }
    public Position getPosition(){
        return m_pawn.getPosition();
    }
    public void deplacer(Direction deplacement){
        switch (deplacement) {
            case UP:
                m_pawn.goUp();
                if(m_pawn.getPosition().getPositionY() < 0){
                    m_pawn.setPositionY(6);
                }
                break;
            case DOWN:
                m_pawn.enBas();
                if(m_pawn.getPosition().getPositionY() > 6){
                    m_pawn.setPositionY(0);
                }
                break;
            case LEFT:
                m_pawn.goLeft();
                if(m_pawn.getPosition().getPositionX() < 0){
                    m_pawn.setPositionX(6);
                }
                break;
            case RIGHT:
                m_pawn.goRight();
                if(m_pawn.getPosition().getPositionX() > 6){
                    m_pawn.setPositionX(0);
                }
                break;
        }
    }

    public void setImgPion(String path){
        m_pawn.setPath(path);
    }

    public void setPionPosition(Position m_position) {
        this.m_pawn.setPosition(m_position);
    }

    public void captureObjectif(Objective objective){
        if(m_lstObjective.contains(objective)){
            m_lstObjective.remove(objective);
            m_objectifCapture++;
        }
    }
}
