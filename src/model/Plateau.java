package model;

import java.util.ArrayList;
import java.util.Random;

public class Plateau {
    private final Joueur[] m_joueur;
    private final Tuile[][] m_lstTuilesPlateau;
    private final ArrayList<TuileAngle> m_lstAngle;
    private final ArrayList<TuileT> m_lstT;
    private final ArrayList<TuileDroite> m_lstDroite;


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
        TuileFactory m_factory = new TuileFactory();
        m_lstAngle = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            m_lstAngle.add(m_factory.createTuileAngle());
        }
        m_lstT = new ArrayList<>();
        for(int i = 0; i < 18; i++){
            m_lstT.add(m_factory.createTuileT());
        }
        m_lstDroite = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            m_lstDroite.add(m_factory.createTuileDroite());
        }
        initPlaceTuileAng();
        initPlaceTuileT();
        placerTuile();
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

    private void initPlaceTuileT(){
        this.placerTuileSurPlateau(new Position(2,0), m_lstT.getLast());
        m_lstT.removeLast();
        this.placerTuileSurPlateau(new Position(4,0), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate();
        this.placerTuileSurPlateau(new Position(6,2), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate();
        this.placerTuileSurPlateau(new Position(6,4), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate(2);
        this.placerTuileSurPlateau(new Position(4,6), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate(2);
        this.placerTuileSurPlateau(new Position(2,6), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate(3);
        this.placerTuileSurPlateau(new Position(0,4), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate(3);
        this.placerTuileSurPlateau(new Position(0,2), m_lstT.getLast());
        m_lstT.removeLast();
    }

    private void initPlaceTuileAng(){
        this.placerTuileSurPlateau(new Position(0,0), m_lstAngle.getLast());
        m_lstAngle.removeLast();
        m_lstAngle.getLast().rotate();
        this.placerTuileSurPlateau(new Position(6,0), m_lstAngle.getLast());
        m_lstAngle.removeLast();
        m_lstAngle.getLast().rotate(3);
        this.placerTuileSurPlateau(new Position(0,6), m_lstAngle.getLast());
        m_lstAngle.removeLast();
        m_lstAngle.getLast().rotate(2);
        this.placerTuileSurPlateau(new Position(6,6), m_lstAngle.getLast());
        m_lstAngle.removeLast();
    }

    private void placerTuile() {
        Random rand = new Random();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.getPlateau()[i][j] == null) {
                    boolean placed = false;

                    while (!placed) {
                        int n = rand.nextInt(3);
                        switch (n) {
                            case 0:
                                if (!m_lstAngle.isEmpty()) {
                                    m_lstAngle.getLast().rotate(rand.nextInt(3));
                                    this.placerTuileSurPlateau(new Position(j, i), m_lstAngle.getLast());
                                    m_lstAngle.removeLast();
                                    placed = true;
                                }
                                break;
                            case 1:
                                if (!m_lstT.isEmpty()) {
                                    m_lstT.getLast().rotate(rand.nextInt(3));
                                    this.placerTuileSurPlateau(new Position(j, i), m_lstT.getLast());
                                    m_lstT.removeLast();
                                    placed = true;
                                }
                                break;
                            case 2:
                                if (!m_lstDroite.isEmpty()) {
                                    m_lstDroite.getLast().rotate(rand.nextInt(3));
                                    this.placerTuileSurPlateau(new Position(j, i), m_lstDroite.getLast());
                                    m_lstDroite.removeLast();
                                    placed = true;
                                }
                                break;
                        }
                    }
                }
            }
        }
    }

}