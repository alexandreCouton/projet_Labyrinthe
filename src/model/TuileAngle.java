package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TuileAngle extends Tuile{
    private BufferedImage m_image;
    public TuileAngle(String imagePath, HashMap<TuileOuverture,Boolean> possibilite){
        super(loadImage(imagePath), possibilite);
    }

    public TuileAngle(String imagePath,Objectif objectif,HashMap<TuileOuverture,Boolean> possibilite){
        super(loadImage(imagePath), possibilite,objectif);
    }
    // On est obligé de faire une static étant donnée qu'il faut le passer dans le super avant meme la creation
    // de l'objet lui meme
    private static BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace(); // au cas ou l'image existe pas
            return null;
        }
    }
}
