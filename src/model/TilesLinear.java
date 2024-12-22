package model;

public class TilesLinear extends Tiles {

    private String m_pathTileLinear = "src/img/TileLinear.png";


    /**
     * YOU HAVE TO USE THE FACTORY
     */
    public TilesLinear(){
/*        openDirections.put(Direction.HAUT, true);
        openDirections.put(Direction.DROITE, false);
        openDirections.put(Direction.BAS, false);
        openDirections.put(Direction.GAUCHE, true);*/
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
        return m_pathTileLinear;
    };

    public void setPath(String path){
        this.m_pathTileLinear = path;
    }
}
