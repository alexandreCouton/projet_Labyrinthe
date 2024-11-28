package model;

import java.util.HashMap;

public class TuileFactory {

    private String pathTuileDroite = "../../img/TuileDroite.png";
    private String pathTuileAngle = "../../img/TuileAngle.png";
    private String pathTuileT = "../../img/TuileT.png";

    public TuileFactory(){
    }



    public TuileDroite createTuileDroite(){
        HashMap<TuileOuverture, Boolean> possibilite = new HashMap<>();
        // forme de base ouverture bas , haut |
        possibilite.put(TuileOuverture.HAUT, true);
        possibilite.put(TuileOuverture.DROITE, false);
        possibilite.put(TuileOuverture.BAS, false);
        possibilite.put(TuileOuverture.GAUCHE, true);
        return new TuileDroite(pathTuileDroite,possibilite);
    }
    public TuileDroite createTuileDroite(Objectif objectif){
        HashMap<TuileOuverture, Boolean> possibilite = new HashMap<>();
        // forme de base ouverture bas , haut |
        possibilite.put(TuileOuverture.HAUT, true);
        possibilite.put(TuileOuverture.DROITE, false);
        possibilite.put(TuileOuverture.BAS, false);
        possibilite.put(TuileOuverture.GAUCHE, true);
        return new TuileDroite(pathTuileDroite,objectif,possibilite);
    }

    public TuileAngle createTuileAngle(){
        HashMap<TuileOuverture, Boolean> possibilite = new HashMap<>();
        // forme de base ouverture droite et bas -> et |
        possibilite.put(TuileOuverture.HAUT, false);
        possibilite.put(TuileOuverture.DROITE, true);
        possibilite.put(TuileOuverture.BAS, true);
        possibilite.put(TuileOuverture.GAUCHE, false);
        return new TuileAngle(pathTuileAngle,possibilite);
    }
    public TuileAngle createTuileAngle(Objectif objectif){
        HashMap<TuileOuverture, Boolean> possibilite = new HashMap<>();
        // forme de base ouverture droite et bas -> et |
        possibilite.put(TuileOuverture.HAUT, false);
        possibilite.put(TuileOuverture.DROITE, true);
        possibilite.put(TuileOuverture.BAS, true);
        possibilite.put(TuileOuverture.GAUCHE, false);
        return new TuileAngle(pathTuileAngle,objectif, possibilite);
    }
    public TuileT createTuileT(){
        HashMap<TuileOuverture, Boolean> possibilite = new HashMap<>();
        // forme de base ouverture droite , gauche et bas <- -> et |
        possibilite.put(TuileOuverture.HAUT, false);
        possibilite.put(TuileOuverture.DROITE, true);
        possibilite.put(TuileOuverture.BAS, true);
        possibilite.put(TuileOuverture.GAUCHE, true);
        return new TuileT(pathTuileT,possibilite);
    }

    public TuileT createTuileT(Objectif objectif){
        HashMap<TuileOuverture, Boolean> possibilite = new HashMap<>();
        // forme de base ouverture droite , gauche et bas <- -> et |
        possibilite.put(TuileOuverture.HAUT, false);
        possibilite.put(TuileOuverture.DROITE, true);
        possibilite.put(TuileOuverture.BAS, true);
        possibilite.put(TuileOuverture.GAUCHE, true);
        return new TuileT(pathTuileT,objectif,possibilite);
    }

}
