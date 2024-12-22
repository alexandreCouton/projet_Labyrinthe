package model;

public class TilesCorner extends Tiles {

    private String m_pathTileCorner = "./img/imgTile/TileCorner.png";

    /**
     * YOU HAVE TO USE THE FACTORY
     */
    public TilesCorner(){
        /*openDirections.put(Direction.HAUT, false);
        openDirections.put(Direction.DROITE, true);
        openDirections.put(Direction.BAS, true);
        openDirections.put(Direction.GAUCHE, false);*/
        super(false,true,true,false);
    }

    /**
     * YOU HAVE TO USE THE FACTORY
     * @param objective : create the tile
     */
    public TilesCorner(Objective objective){
        super(objective,false,true,true,false);
    }

    /**
     * @return get the tile's path
     */
    public String getPath(){
        return m_pathTileCorner;
    };

    public void setPath(String path){
        this.m_pathTileCorner = path;
    }

}
