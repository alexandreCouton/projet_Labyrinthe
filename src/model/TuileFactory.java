package model;


/**
 * The TuileFactory class is responsible for creating instances of different tile types
 * in the Labyrinth game. It provides methods for creating tiles of various shapes,
 * including linear tiles, corner tiles, and T-shaped tiles, with or without objectives.
 *<br>
 * Key responsibilities include:<br>
 * - Creating and returning new instances of tiles with specific configurations.<br>
 * - Allowing the creation of tiles with or without associated objectives.<br>
 *<br>
 * The factory methods include:<br>
 * - `createTileLinear()`: Creates a linear tile with basic openings (up and down).<br>
 * - `createTileLinear(Objective objective)`: Creates a linear tile with a specified objective.<br>
 * - `createTileCorner()`: Creates a corner tile with openings to the right and down.<br>
 * - `createTileCorner(Objective objective)`: Creates a corner tile with a specified objective.<br>
 * - `createTileT()`: Creates a T-shaped tile with specific openings.<br>
 * - `createTileT(Objective objective)`: Creates a T-shaped tile with a specified objective.<br>
 *<br>
 * This class helps abstract the creation process of different tile types and ensures that<br>
 * the correct configurations are applied when creating new tiles for the game.<br>
 */

public class TuileFactory {


    public TuileFactory(){
    }


    /**
     * @return a linear tile instance
     */
    public TilesLinear createTileLinear(){
        // forme de base ouverture bas , haut |

        return new TilesLinear();
    }

    /**
     * @param objective : the objective on the tile
     * @return a linear tile instance with an objective in it
     */
    public TilesLinear createTileLinear(Objective objective){
        // forme de base ouverture bas , haut |

        return new TilesLinear(objective);
    }

    /**
     * @return a corner tile instance
     */
    public TilesCorner createTileCorner(){
        // forme de base ouverture droite et bas -> et |

        return new TilesCorner();
    }

    /**
     * @param objective : the objective in the tile
     * @return a corner tile instance with an objective in it
     */
    public TilesCorner createTileCorner(Objective objective){
        return new TilesCorner(objective);
    }

    /**
     * @return a T tile instance
     */
    public TilesT createTileT(){
        return new TilesT();
    }

    /**
     * @param objective  : the objective in the tile
     * @return a T tile instance
     */
    public TilesT createTileT(Objective objective){
        return new TilesT(objective);
    }

}
