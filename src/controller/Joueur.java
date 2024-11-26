package controller;

import model.Objectif;
import model.Pion;
import model.Position;
import model.TuileOuverture;

import java.util.ArrayList;

import static model.TuileOuverture.*;


public class Joueur {
    private String m_nom;
    private ArrayList<Objectif> m_lstObjectif;
    private ArrayList<Objectif> m_objectifCapture;
    private Pion m_pion;

    public Joueur(String nom){
        this.m_nom = nom;
        m_pion = new Pion("src/img/pion.png", new Position(0,0));
    }
    public void jouer(){

    }

    private void piocher(){

    }

    private void deplacer(TuileOuverture deplacement){
        switch (deplacement) {
            case HAUT:
                m_pion.enHaut();
                if(m_pion.getM_position().getPositionY() < 0){
                    m_pion.setPositionY(6);
                }
                break;
            case BAS:
                m_pion.enBas();
                if(m_pion.getM_position().getPositionY() > 6){
                    m_pion.setPositionY(0);
                }
                break;
            case GAUCHE:
                m_pion.aGauche();
                if(m_pion.getM_position().getPositionX() < 0){
                    m_pion.setPositionX(6);
                }
                break;
            case DROITE:
                m_pion.aDroite();
                if(m_pion.getM_position().getPositionX() > 6){
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
}
