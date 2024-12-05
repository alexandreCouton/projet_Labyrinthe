package model;

import java.util.ArrayList;

public class Game {

    private final Joueur[] lstJoueur;
    private final Plateau m_plateau;
    private int m_joueurCourant;

    public Game() {
        this.lstJoueur = new Joueur[4];
        m_plateau = new Plateau();

        initJoueurs();
    }

    public Joueur getJoueur(int joueur){ return lstJoueur[joueur]; }

    private void initJoueurs() {
        for (int i = 0; i < 4; i++) {
            lstJoueur[i] = new Joueur("Joueur " + (i + 1));
        }
        placerJoueur();
    }

    private void placerJoueur(){
        lstJoueur[0].setPionPosition(new Position(0,0));
        lstJoueur[1].setPionPosition(new Position(6,0));
        lstJoueur[2].setPionPosition(new Position(0,6));
        lstJoueur[3].setPionPosition(new Position(6,6));
    }


    public boolean movePlayer( Direction direction){
        switch (direction) {
            case Direction.DROITE:
                if (lstJoueur[m_joueurCourant].getPosition().getPositionX() + 1 <= 6) {
                    if (m_plateau.getPlateau()[lstJoueur[m_joueurCourant].getPosition().getPositionX()][lstJoueur[m_joueurCourant].getPosition().getPositionY()].getOpen(Direction.DROITE)) {
                        if (m_plateau.getPlateau()[lstJoueur[m_joueurCourant].getPosition().getPositionX() + 1][lstJoueur[m_joueurCourant].getPosition().getPositionY()].getOpen(Direction.GAUCHE)) {
                            lstJoueur[m_joueurCourant].deplacer(Direction.DROITE);
                            return true;
                        }
                    }
                }
                return false;
            case Direction.GAUCHE:
                if (lstJoueur[m_joueurCourant].getPosition().getPositionX() - 1 >= 0) {
                    if (m_plateau.getPlateau()[lstJoueur[m_joueurCourant].getPosition().getPositionX()][lstJoueur[m_joueurCourant].getPosition().getPositionY()].getOpen(Direction.GAUCHE)) {
                        if (m_plateau.getPlateau()[lstJoueur[m_joueurCourant].getPosition().getPositionX() - 1][lstJoueur[m_joueurCourant].getPosition().getPositionY()].getOpen(Direction.DROITE)) {
                            lstJoueur[m_joueurCourant].deplacer(Direction.GAUCHE);
                            return true;
                        }
                    }
                }
                return false;

            case Direction.HAUT:
                if (lstJoueur[m_joueurCourant].getPosition().getPositionY() - 1 >= 0) {
                    if (m_plateau.getPlateau()[lstJoueur[m_joueurCourant].getPosition().getPositionX()][lstJoueur[m_joueurCourant].getPosition().getPositionY()].getOpen(Direction.HAUT)) {
                        if (m_plateau.getPlateau()[lstJoueur[m_joueurCourant].getPosition().getPositionX()][lstJoueur[m_joueurCourant].getPosition().getPositionY() - 1].getOpen(Direction.BAS)) {
                            lstJoueur[m_joueurCourant].deplacer(Direction.HAUT);
                            return true;
                        }
                    }
                }
                return false;
            case Direction.BAS:
                if (lstJoueur[m_joueurCourant].getPosition().getPositionY() + 1 <= 6) {
                    if (m_plateau.getPlateau()[lstJoueur[m_joueurCourant].getPosition().getPositionX()][lstJoueur[m_joueurCourant].getPosition().getPositionY()].getOpen(Direction.BAS)) {
                        if (m_plateau.getPlateau()[lstJoueur[m_joueurCourant].getPosition().getPositionX()][lstJoueur[m_joueurCourant].getPosition().getPositionY() + 1].getOpen(Direction.HAUT)) {
                            lstJoueur[m_joueurCourant].deplacer(Direction.BAS);
                            return true;
                        }
                    }
                }
                return false;
        }

        return false;
    }



}
