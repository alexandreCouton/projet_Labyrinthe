package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import model.ImageHelper;


public class Plateau {
    private final Tuile[][] m_lstTuilesPlateau;
    private final ArrayList<TuileAngle> m_lstAngle;
    private final ArrayList<TuileT> m_lstT;
    private final ArrayList<TuileDroite> m_lstDroite;
    private ArrayList<Objectif> m_lstObjectif;
    private Tuile m_tuileVolante;

    public Plateau() {
        m_lstTuilesPlateau = new Tuile[7][7];
        /*[y],[x]
        [[1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7],
        [ 1,2,3,4,5,6,7]]*/

        m_lstAngle = new ArrayList<>();
        m_lstT = new ArrayList<>();
        m_lstDroite = new ArrayList<>();
        m_lstObjectif = new ArrayList<>();
        m_tuileVolante = null;

        initObjectif();
        initPlateau();
        initObjectif();
        initTuiles();
        placerTuile();

    }

    public Tuile getTuileVolante(){
        return m_tuileVolante;
    }

    public Tuile[][] getPlateau(){
        return this.m_lstTuilesPlateau;
    }



    private void initObjectif(){
        ImageHelper imgHelper = new ImageHelper();
        List<String> lstPath = imgHelper.getPathImg("../../img/imgObjectif");
        for(int i = 0; i < 24; i++){
            if(lstPath.isEmpty()){
                lstPath = imgHelper.getPathImg("../../img/imgObjectif/argent.png");
            }else {
                m_lstObjectif.add(new Objectif(lstPath.getLast()));
                lstPath.removeLast();
            }
        }
        Collections.shuffle(m_lstObjectif);
    }

    private void initTuiles() {
        TuileFactory m_factory = new TuileFactory();
        for(int i = 0; i < 20; i++){
            m_lstAngle.add(m_factory.createTuileAngle());
        }
        for(int i = 0; i < 18; i++){
            m_lstT.add(m_factory.createTuileT());
        }
        for(int i = 0; i < 12; i++){
            m_lstDroite.add(m_factory.createTuileDroite());
        }
    }

    private void initPlateau(){
        for (int y = 0; y < m_lstTuilesPlateau.length; y++) {
            for(int x=0;x<m_lstTuilesPlateau[y].length;x++){
                m_lstTuilesPlateau[y][x]=null;
            }
        }
    }



    public void placerTuileSurPlateau(Position pos,Tuile tuile){
        int x = pos.getPositionX();
        int y = pos.getPositionY();

        m_lstTuilesPlateau[y][x] = tuile;
    }

    private void initPlaceTuileT(){
        m_lstT.getLast().rotate(2);
        this.placerTuileSurPlateau(new Position(2,0), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate(2);
        this.placerTuileSurPlateau(new Position(4,0), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate(3);
        this.placerTuileSurPlateau(new Position(6,2), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate(3);
        this.placerTuileSurPlateau(new Position(6,4), m_lstT.getLast());
        m_lstT.removeLast();
        this.placerTuileSurPlateau(new Position(4,6), m_lstT.getLast());
        m_lstT.removeLast();
        this.placerTuileSurPlateau(new Position(2,6), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate();
        this.placerTuileSurPlateau(new Position(0,4), m_lstT.getLast());
        m_lstT.removeLast();
        m_lstT.getLast().rotate();
        this.placerTuileSurPlateau(new Position(0,2), m_lstT.getLast());
        m_lstT.removeLast();
    }

    private void initPlaceTuileAng(){
        try {
            this.placerTuileSurPlateau(new Position(0, 0), m_lstAngle.getLast());
            m_lstAngle.getLast().setImage(ImageHelper.merge(m_lstAngle.getLast().getImage(), "src/img/imgDepart/departBleu.png"));
            m_lstAngle.removeLast();
            m_lstAngle.getLast().rotate();
            this.placerTuileSurPlateau(new Position(6, 0), m_lstAngle.getLast());
            m_lstAngle.getLast().setImage(ImageHelper.merge(m_lstAngle.getLast().getImage(), "src/img/imgDepart/departJaune.png"));
            m_lstAngle.removeLast();
            m_lstAngle.getLast().rotate(3);
            this.placerTuileSurPlateau(new Position(0, 6), m_lstAngle.getLast());
            m_lstAngle.getLast().setImage(ImageHelper.merge(m_lstAngle.getLast().getImage(), "src/img/imgDepart/departRouge.png"));
            m_lstAngle.removeLast();
            m_lstAngle.getLast().rotate(2);
            this.placerTuileSurPlateau(new Position(6, 6), m_lstAngle.getLast());
            m_lstAngle.getLast().setImage(ImageHelper.merge(m_lstAngle.getLast().getImage(), "src/img/imgDepart/departVert.png"));
            m_lstAngle.removeLast();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void placerTuile() {
        initPlaceTuileAng();
        initPlaceTuileT();

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
        if(!m_lstAngle.isEmpty()){
            m_tuileVolante = m_lstAngle.getLast();
            m_lstAngle.removeLast();
        }else if(!m_lstT.isEmpty()){
            m_tuileVolante = m_lstT.getLast();
            m_lstT.removeLast();
        }else {
            m_tuileVolante = m_lstDroite.getLast();
            m_lstDroite.removeLast();
        }
    }


    public void captureObjectif(Joueur j, Objectif objectif){
        j.captureObjectif(objectif);
    }

}