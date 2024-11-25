package model;

import controller.Joueur;

public class Plateau {
    private final Joueur[] m_joueur;
    private final Tuile[][] m_lstTuilesPlateau;

    public Plateau(Joueur[] lstJoueur) {
        this.m_joueur = lstJoueur;
        m_lstTuilesPlateau = new Tuile[7][7];
        /*[y],[x]
        [[1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7]]*/
        for (int y = 0; y < m_lstTuilesPlateau.length; y++) {
            for(int x=0;x<m_lstTuilesPlateau[y].length;x++){
                m_lstTuilesPlateau[y][x]=null;
            }
        }
    }

    public Tuile[][] getPlateau(){
        return this.m_lstTuilesPlateau;
    }

    public Joueur[] getJoueur() {
        return m_joueur;
    }

    public void placerTuileSurPlateau(Position pos,Tuile tuile){
        int x = pos.getPositionX();
        int y = pos.getPositionY();

        m_lstTuilesPlateau[y][x] = tuile;
    }

}