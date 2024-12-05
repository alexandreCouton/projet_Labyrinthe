package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TuileT extends Tuile{

    private String pathTuileT = "src/img/TuileT.png";


    private BufferedImage m_image;
    public TuileT(){
        super(false, true, true, true);
    }
    public TuileT(Objectif objectif){
        super(objectif, false, true, true, true);
    }

}
