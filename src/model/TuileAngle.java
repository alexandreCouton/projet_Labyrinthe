package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TuileAngle extends Tuile{
    private BufferedImage m_image;
    public TuileAngle(String imagePath){
        HashMap<TuileOuverture, Boolean> possibilite = new HashMap<>();
        // forme de base ouverture droite et bas -> et |
        possibilite.put(TuileOuverture.HAUT, false);
        possibilite.put(TuileOuverture.DROITE, true);
        possibilite.put(TuileOuverture.BAS, true);
        possibilite.put(TuileOuverture.GAUCHE, false);
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

    @Override
    public void rotate() {
        HashMap<TuileOuverture, Boolean> tmp = new HashMap<>();
        tmp.put(TuileOuverture.HAUT, m_possibilite.get(TuileOuverture.GAUCHE));
        tmp.put(TuileOuverture.DROITE, m_possibilite.get(TuileOuverture.HAUT));
        tmp.put(TuileOuverture.BAS, m_possibilite.get(TuileOuverture.DROITE));
        tmp.put(TuileOuverture.GAUCHE, m_possibilite.get(TuileOuverture.BAS));
        m_possibilite = tmp;
    }
}
