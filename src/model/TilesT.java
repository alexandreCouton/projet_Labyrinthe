package model;

import java.awt.image.BufferedImage;

public class TilesT extends Tiles {

    private String pathTileT = "src/img/TileT.png";


    /**
     * YOU HAVE TO USE THE FACTORY
     */
    public TilesT(){
        super(false, true, true, true);
    }
    /**
     * YOU HAVE TO USE THE FACTORY
     * @param objective : create the tile
     */
    public TilesT(Objective objective){
        super(objective, false, true, true, true);
    }

    public String getPath(){
        return pathTileT;
    };

}
