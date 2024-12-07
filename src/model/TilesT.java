package model;

import java.awt.image.BufferedImage;

public class TilesT extends Tiles {

    private String pathTileT = "src/img/TileT.png";


    public TilesT(){
        super(false, true, true, true);
    }
    public TilesT(Objective objective){
        super(objective, false, true, true, true);
    }

    public String getPath(){
        return pathTileT;
    };

}
