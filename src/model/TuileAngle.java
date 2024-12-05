package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TuileAngle extends Tuile{

    private String pathTuileAngle = "src/img/TuileAngle.png";

    private BufferedImage m_image;
    public TuileAngle(){
        /*possibilite.put(Direction.HAUT, false);
        possibilite.put(Direction.DROITE, true);
        possibilite.put(Direction.BAS, true);
        possibilite.put(Direction.GAUCHE, false);*/
        super(false,true,true,false);
    }

    public TuileAngle(Objectif objectif){
        super(objectif,false,true,true,false);
    }

}
