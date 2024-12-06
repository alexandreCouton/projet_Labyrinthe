package model;

import java.awt.image.BufferedImage;

public class TilesLinear extends Tiles {

    private String pathTileLinear = "src/img/TilesLinear.png";
    private BufferedImage m_image;
    public TilesLinear(){
/*        possibilite.put(Direction.HAUT, true);
        possibilite.put(Direction.DROITE, false);
        possibilite.put(Direction.BAS, false);
        possibilite.put(Direction.GAUCHE, true);*/
        super(true,false,false,true);
    }
    public TilesLinear(Objective objective){
        super(objective,true,false,false,true);
    }


}
