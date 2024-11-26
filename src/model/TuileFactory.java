package model;

public class TuileFactory {

    private String pathTuileDroite = "../../img/TuileDroite.png";
    private String pathTuileAngle = "../../img/TuileAngle.png";
    private String pathTuileT = "../../img/TuileT.png";

    public TuileFactory(){
    }



    public TuileDroite createTuileDroite(){
        return new TuileDroite(pathTuileDroite);
    }
    public TuileDroite createTuileDroite(Objectif objectif){
        return new TuileDroite(pathTuileDroite,objectif);
    }

    public TuileAngle createTuileAngle(){
        return new TuileAngle(pathTuileAngle);
    }
    public TuileAngle createTuileAngle(Objectif objectif){
        return new TuileAngle(pathTuileAngle,objectif);
    }
    public TuileT createTuileT(){
        return new TuileT(pathTuileT);
    }

    public TuileT createTuileT(Objectif objectif){
        return new TuileT(pathTuileT,objectif);
    }

}
