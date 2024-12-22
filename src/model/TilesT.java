package model;

public class TilesT extends Tiles {

    private String m_pathTileT = "src/img/TileT.png";


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
        return m_pathTileT;
    };

    public void setPath(String path){
        this.m_pathTileT = path;
    }

}
