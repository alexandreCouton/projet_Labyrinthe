package model;

import java.awt.image.BufferedImage;

public class TilesLinear extends Tiles {

    private String pathTileLinear = "src/img/TileLinear.png";


    /**
     * YOU HAVE TO USE THE FACTORY
     */
    public TilesLinear(){
/*        possibilite.put(Direction.HAUT, true);
        possibilite.put(Direction.DROITE, false);
        possibilite.put(Direction.BAS, false);
        possibilite.put(Direction.GAUCHE, true);*/
        super(false,true,false,true);
    }

    /**
     * YOU HAVE TO USE THE FACTORY
     * @param objective : create the tile
     */
    public TilesLinear(Objective objective){
        super(objective,false,true,false,true);
    }

    /**
     * @return get the tile's path
     */
    public String getPath(){
        return pathTileLinear;
    };
}
