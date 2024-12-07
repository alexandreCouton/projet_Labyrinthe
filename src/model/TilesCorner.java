package model;

import java.awt.image.BufferedImage;

public class TilesCorner extends Tiles {

    private String pathTileCorner = "src/img/TileCorner.png";

    public TilesCorner(){
        /*possibilite.put(Direction.HAUT, false);
        possibilite.put(Direction.DROITE, true);
        possibilite.put(Direction.BAS, true);
        possibilite.put(Direction.GAUCHE, false);*/
        super(false,true,true,false);
    }

    public TilesCorner(Objective objective){
        super(objective,false,true,true,false);
    }

    public String getPath(){
        return pathTileCorner;
    };

}
