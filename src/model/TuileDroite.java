package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TuileDroite extends Tuile{

    private BufferedImage m_image;
    public TuileDroite(String imagePath,HashMap<TuileOuverture,Boolean> possibilite){
        super(loadImage(imagePath), possibilite);
    }
    public TuileDroite(String imagePath,Objectif objectif,HashMap<TuileOuverture,Boolean> possibilite){
        super(loadImage(imagePath), possibilite,objectif);
    }

    private static BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
