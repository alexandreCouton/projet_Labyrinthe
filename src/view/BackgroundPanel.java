package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;
    private float darknessFactor = 0.5f; // Facteur de 0 à 1 pour contrôler l'assombrissement

    public BackgroundPanel(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        setOpaque(false); // Assurez-vous que le panneau n'est pas opaque
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Dessinez l'image de fond
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            // Appliquez un filtre d'assombrissement
            g2d.setComposite(AlphaComposite.SrcOver.derive(darknessFactor));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.dispose();
        }
    }
}
