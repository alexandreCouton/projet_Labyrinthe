package model;

import java.awt.image.BufferedImage;

public class TilesT extends Tiles {

    private String pathTileT = "src/img/TileT.png";


    /**
     * YOU HAVE TO USE THE FACTORY
     */
    public TilesT(){
        super(true, true, false, true);
    }
    /**
     * YOU HAVE TO USE THE FACTORY
     * @param objective : create the tile
     */
    public TilesT(Objective objective){
        super(objective, true, true, false, true);
    }

    public String getPath(){
        return pathTileT;
    };

    public void setPath(String path){
        this.pathTileT = path;
    }

}
