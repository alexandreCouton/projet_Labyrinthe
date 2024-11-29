package controller;

import model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class GameController {
    private final Plateau m_plateau;
// A supprimer
    private Joueur[] m_lstJoueur;
    //A descendre dans Plateau
    private ArrayList<Objectif> m_lstObjectif;
    public GameController(){
        //A descendre dans Plateau

        initJoueurs();
        //A supprimer dans le constructeur ici et dans plateau le joueur
        m_plateau = new Plateau(m_lstJoueur);
        //A descendre dans Plateau

        initPartie();
    }


    // A descndre dans Plateau
    private void initJoueurs() {
        m_lstJoueur = new Joueur[4];
        for (int i = 0; i < 4; i++) {
            m_lstJoueur[i] = new Joueur("Joueur " + (i + 1));
        }
        placerJoueur();
    }


    // A descndre dans Plateau

    public void initPartie(){
        initObjectif();

        Collections.shuffle(m_lstObjectif);

    }
    // A descndre dans Plateau

    private void distribuer(Joueur joueur){
        ArrayList<Objectif> designe = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            designe.add(m_lstObjectif.getLast());
            m_lstObjectif.removeLast();
        }
    }
    // A descndre dans Plateau

    private void initObjectif(){
        List<String> lstPath = getPathImg("../../img/imgObjectif");
        m_lstObjectif = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            m_lstObjectif.add(new Objectif(lstPath.getLast()));
            lstPath.removeLast();
        }
    }
    // A descndre dans Plateau

    public List<String> getPathImg(String path){
        List<String> lstPath = new ArrayList<>();
        File folder = new File(path);
        if (folder.exists() && folder.isDirectory()) {
            File[] imageFile = folder.listFiles();
            if(imageFile != null){
                for (File file : imageFile) {
                    lstPath.add(file.getAbsolutePath());
                }
            }

        }
        return lstPath;
    }
    // A descndre dans Plateau

    private void placerJoueur(){
        m_lstJoueur[0].setPionPosition(new Position(0,0));
        m_lstJoueur[1].setPionPosition(new Position(6,0));
        m_lstJoueur[2].setPionPosition(new Position(0,6));
        m_lstJoueur[3].setPionPosition(new Position(6,6));
    }
}
