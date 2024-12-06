package model;

public class TuileFactory {


    public TuileFactory(){
    }



    public TilesLinear createTileLinear(){
        // forme de base ouverture bas , haut |

        return new TilesLinear();
    }
    public TilesLinear createTileLinear(Objective objective){
        // forme de base ouverture bas , haut |

        return new TilesLinear(objective);
    }

    public TilesCorner createTileCorner(){
        // forme de base ouverture droite et bas -> et |

        return new TilesCorner();
    }
    public TilesCorner createTileCorner(Objective objective){
        return new TilesCorner(objective);
    }
    public TilesT createTileT(){
        return new TilesT();
    }

    public TilesT createTileT(Objective objective){
        return new TilesT(objective);
    }

}
