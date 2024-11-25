package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TuileDroite extends Tuile{

    private BufferedImage m_image;
    public TuileDroite(String imagePath){
        HashMap<TuileOuverture, Boolean> possibilite = new HashMap<>();
        // forme de base ouverture bas , haut |
        possibilite.put(TuileOuverture.HAUT, true);
        possibilite.put(TuileOuverture.DROITE, false);
        possibilite.put(TuileOuverture.BAS, false);
        possibilite.put(TuileOuverture.GAUCHE, true);
        super(loadImage(imagePath), possibilite);
    }

    private static BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void rotate() {
        HashMap<TuileOuverture, Boolean> tmp = new HashMap<>();
        tmp.put(TuileOuverture.HAUT, m_possibilite.get(TuileOuverture.GAUCHE));
        tmp.put(TuileOuverture.DROITE, m_possibilite.get(TuileOuverture.HAUT));
        tmp.put(TuileOuverture.BAS, m_possibilite.get(TuileOuverture.DROITE));
        tmp.put(TuileOuverture.GAUCHE, m_possibilite.get(TuileOuverture.BAS));
        m_possibilite = tmp;
    }
}
