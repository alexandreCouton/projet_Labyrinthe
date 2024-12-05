package model;

import java.util.HashMap;

public class TuileFactory {


    public TuileFactory(){
    }



    public TuileDroite createTuileDroite(){
        // forme de base ouverture bas , haut |

        return new TuileDroite();
    }
    public TuileDroite createTuileDroite(Objectif objectif){
        // forme de base ouverture bas , haut |

        return new TuileDroite(objectif);
    }

    public TuileAngle createTuileAngle(){
        // forme de base ouverture droite et bas -> et |

        return new TuileAngle();
    }
    public TuileAngle createTuileAngle(Objectif objectif){
        return new TuileAngle(objectif);
    }
    public TuileT createTuileT(){
        return new TuileT();
    }

    public TuileT createTuileT(Objectif objectif){
        return new TuileT(objectif);
    }

}
