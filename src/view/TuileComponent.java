package view;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import model.Tiles;

public class TuileComponent extends JComponent {
    private Tiles m_tiles;
    private BufferedImage m_image;

    public TuileComponent(Tiles tiles) {

        setLayout(new BorderLayout());
        setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (m_image != null) {
            g.drawImage(m_image, 0, 0, getWidth(), getHeight(), this);
        }
    }
    public void setTuile(Tiles tiles) {
        m_tiles = tiles;
        m_image = tiles.getImage();
        repaint();
    }




}
