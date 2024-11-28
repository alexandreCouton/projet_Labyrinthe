package controller;

import model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class GameController {
    private final Plateau m_plateau;

    private Joueur[] m_lstJoueur;
    private ArrayList<Objectif> m_lstObjectif;
    public GameController(){
        initJoueurs();
        m_plateau = new Plateau(m_lstJoueur);
        initPartie();
    }



    private void initJoueurs() {
        m_lstJoueur = new Joueur[4];
        for (int i = 0; i < 4; i++) {
            m_lstJoueur[i] = new Joueur("Joueur " + (i + 1));
        }
        placerJoueur();
    }



    public void initPartie(){
        initObjectif();

        Collections.shuffle(m_lstObjectif);

    }

    private void distribuer(Joueur joueur){
        ArrayList<Objectif> designe = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            designe.add(m_lstObjectif.getLast());
            m_lstObjectif.removeLast();
        }
    }

    private void initObjectif(){
        List<String> lstPath = getPathImg("../../img/imgObjectif");
        m_lstObjectif = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            m_lstObjectif.add(new Objectif(lstPath.getLast()));
            lstPath.removeLast();
        }
    }

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

    private void placerJoueur(){
        m_lstJoueur[0].setPionPosition(new Position(0,0));
        m_lstJoueur[1].setPionPosition(new Position(6,0));
        m_lstJoueur[2].setPionPosition(new Position(0,6));
        m_lstJoueur[3].setPionPosition(new Position(6,6));
    }
}
