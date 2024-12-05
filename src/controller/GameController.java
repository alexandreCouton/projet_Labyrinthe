package controller;

import model.*;
import view.PlateauObserver;

import java.util.ArrayList;

public class GameController {
    private final Plateau m_plateau;
    private final ArrayList<PlateauObserver> m_lstObservers;
    private int m_joueurCourant;
    private Game m_game;

    public GameController(Plateau plateau) {
        m_lstObservers = new ArrayList<>();
        m_plateau = plateau;
        //initPartie();
        m_joueurCourant = 0;
    }

    public void addObserver(PlateauObserver observer) {
        m_lstObservers.add(observer);
    }

    public void removeObserver(PlateauObserver observer) {
        m_lstObservers.remove(observer);
    }



    private void notifyObservers(String action){
        for(PlateauObserver observer : m_lstObservers) {
            switch (action) {
                case "debutPartie":
                    observer.debutPartie();
                    break;
                case "deplacementTuile":
                        observer.deplacementTuile();
                    break;
                case "deplacementJoueur":
                        observer.deplacementJoueur();
                    break;
                case "captureObjectif":
                        observer.captureObjectif();
                    break;
                case "finPartie":
                        observer.finPartie();
                    break;
            }
        }
    }


    public void initPartie() {
        m_plateau.initPartie();
        notifyObservers("debutPartie");
    }

    public void prochainTour() {
        m_joueurCourant = (m_joueurCourant + 1) % 4;
        notifyObservers("deplacementJoueur");
    }

    public void placerTuileVolante(Position pos) {
        m_plateau.placerTuileSurPlateau(pos, m_plateau.getTuileVolante());
        notifyObservers("deplacementTuile");
    }

    public void deplacer(Direction direction) {
        m_game.movePlayer(direction);
        notifyObservers("deplacementJoueur");
    }

    public void captureObjectif(Joueur joueur, Objectif objectif) {
        m_plateau.captureObjectif(joueur, objectif);
        notifyObservers("captureObjectif");
    }

    public void finPartie() {
        notifyObservers("finPartie");
    }

}
