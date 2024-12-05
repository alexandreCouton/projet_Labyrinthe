package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TuileDroite extends Tuile{

    private String pathTuileDroite = "src/img/TuileDroite.png";
    private BufferedImage m_image;
    public TuileDroite(){
/*        possibilite.put(Direction.HAUT, true);
        possibilite.put(Direction.DROITE, false);
        possibilite.put(Direction.BAS, false);
        possibilite.put(Direction.GAUCHE, true);*/
        super(true,false,false,true);
    }
    public TuileDroite(Objectif objectif){
        super(objectif,true,false,false,true);
    }


}
