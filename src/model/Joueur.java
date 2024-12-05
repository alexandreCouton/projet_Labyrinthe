package model;

import java.util.ArrayList;


public class Joueur {
    private String m_nom;
    private ArrayList<Objectif> m_lstObjectif;
    //En int car on s'en fiche de connaitre ceux qu'il a deja eu vu qu'ils disparaiteront de la liste au m_lstobjectif
    private int m_objectifCapture;
    private Pion m_pion;

    public Joueur(String nom){
        this.m_nom = nom;
        m_pion = new Pion("src/img/pion.png", new Position(0,0));
    }

    private void setLstObjectif(ArrayList<Objectif> lstObjectif){
        m_lstObjectif = lstObjectif;
    }
    public Position getPosition(){
        return m_pion.getPosition();
    }
    public void deplacer(Direction deplacement){
        switch (deplacement) {
            case HAUT:
                m_pion.enHaut();
                if(m_pion.getPosition().getPositionY() < 0){
                    m_pion.setPositionY(6);
                }
                break;
            case BAS:
                m_pion.enBas();
                if(m_pion.getPosition().getPositionY() > 6){
                    m_pion.setPositionY(0);
                }
                break;
            case GAUCHE:
                m_pion.aGauche();
                if(m_pion.getPosition().getPositionX() < 0){
                    m_pion.setPositionX(6);
                }
                break;
            case DROITE:
                m_pion.aDroite();
                if(m_pion.getPosition().getPositionX() > 6){
                    m_pion.setPositionX(0);
                }
                break;
        }
    }

    public void setImgPion(String path){
        m_pion.setPath(path);
    }

    public void setPionPosition(Position m_position) {
        this.m_pion.setPosition(m_position);
    }

    public void captureObjectif(Objectif objectif){
        if(m_lstObjectif.contains(objectif)){
            m_lstObjectif.remove(objectif);
            m_objectifCapture++;
        }
    }
}
