package controller;

import model.*;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private final TuileFactory m_factory;
    private final Plateau m_plateau;
    ArrayList<TuileAngle> ang;
    ArrayList<TuileT> t;
    ArrayList<TuileDroite> droite;
    private Joueur[] lstJoueur;
    private ArrayList<Objectif> lstObjectif;
    public GameController(){
        Joueur[] lstJoueur = new Joueur[4];
        lstJoueur[0] = new Joueur("Joueur 1");
        lstJoueur[1] = new Joueur("Joueur 2");
        lstJoueur[2] = new Joueur("Joueur 3");
        lstJoueur[3] = new Joueur("Joueur 4");
        m_factory = new TuileFactory();
        m_plateau = new Plateau(lstJoueur);
        initPartie();
    }

    public void initPartie(){
        ang = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            ang.add(m_factory.createTuileAngle());
        }
        t = new ArrayList<>();
        for(int i = 0; i < 18; i++){
            t.add(m_factory.createTuileT());
        }
        droite = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            droite.add(m_factory.createTuileDroite());
        }
        initPlaceTuileAng();
        initPlaceTuileT();
        placerTuile();
    }

    private void initObjectif(){
        lstObjectif = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            lstObjectif.add(new Objectif());
        }
    }

    private void placerJoueur(){
        lstJoueur[0].setPionPosition(new Position(0,0));
        lstJoueur[1].setPionPosition(new Position(6,0));
        lstJoueur[2].setPionPosition(new Position(0,6));
        lstJoueur[3].setPionPosition(new Position(6,6));
    }

    private void initPlaceTuileT(){
        m_plateau.placerTuileSurPlateau(new Position(2,0), t.getLast());
        t.removeLast();
        m_plateau.placerTuileSurPlateau(new Position(4,0), t.getLast());
        t.removeLast();
        t.getLast().rotate();
        m_plateau.placerTuileSurPlateau(new Position(6,2), t.getLast());
        t.removeLast();
        t.getLast().rotate();
        m_plateau.placerTuileSurPlateau(new Position(6,4), t.getLast());
        t.removeLast();
        t.getLast().rotate(2);
        m_plateau.placerTuileSurPlateau(new Position(4,6), t.getLast());
        t.removeLast();
        t.getLast().rotate(2);
        m_plateau.placerTuileSurPlateau(new Position(2,6), t.getLast());
        t.removeLast();
        t.getLast().rotate(3);
        m_plateau.placerTuileSurPlateau(new Position(0,4), t.getLast());
        t.removeLast();
        t.getLast().rotate(3);
        m_plateau.placerTuileSurPlateau(new Position(0,2), t.getLast());
        t.removeLast();
    }

    private void initPlaceTuileAng(){
        m_plateau.placerTuileSurPlateau(new Position(0,0), ang.getLast());
        ang.removeLast();
        ang.getLast().rotate();
        m_plateau.placerTuileSurPlateau(new Position(6,0), ang.getLast());
        ang.removeLast();
        ang.getLast().rotate(3);
        m_plateau.placerTuileSurPlateau(new Position(0,6), ang.getLast());
        ang.removeLast();
        ang.getLast().rotate(2);
        m_plateau.placerTuileSurPlateau(new Position(6,6), ang.getLast());
        ang.removeLast();
    }

    private void placerTuile() {
        Random rand = new Random();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (m_plateau.getPlateau()[i][j] == null) {
                    boolean placed = false;

                    while (!placed) {
                        int n = rand.nextInt(3);
                        switch (n) {
                            case 0:
                                if (!ang.isEmpty()) {
                                    ang.getLast().rotate(rand.nextInt(3));
                                    m_plateau.placerTuileSurPlateau(new Position(j, i), ang.getLast());
                                    ang.removeLast();
                                    placed = true;
                                }
                                break;
                            case 1:
                                if (!t.isEmpty()) {
                                    t.getLast().rotate(rand.nextInt(3));
                                    m_plateau.placerTuileSurPlateau(new Position(j, i), t.getLast());
                                    t.removeLast();
                                    placed = true;
                                }
                                break;
                            case 2:
                                if (!droite.isEmpty()) {
                                    droite.getLast().rotate(rand.nextInt(3));
                                    m_plateau.placerTuileSurPlateau(new Position(j, i), droite.getLast());
                                    droite.removeLast();
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
