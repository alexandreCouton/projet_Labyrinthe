package view;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import model.Tiles;
import model.TilesObserver;

import model.ImageHelper;

public class TuileComponent extends JComponent implements TilesObserver {
    private Tiles m_tiles;
    private BufferedImage m_image;

    public TuileComponent(Tiles tiles) {

        setLayout(new BorderLayout());
        setVisible(true);
        m_image = ImageHelper.loadImage(tiles.getPath());
        for(int i=0;i<tiles.getRotateIndex()%4;i++){
            m_image=ImageHelper.rotateClockwise(m_image);
        }
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
        m_image = ImageHelper.loadImage(tiles.getPath());
        repaint();
    }

    public void setImage(BufferedImage image){
        this.m_image = image;
    }

    public BufferedImage getImage(){
        return this.m_image;
    }


    public void updateRotateTile(Tiles tile) {
        m_image = ImageHelper.rotateClockwise(m_image);
        revalidate();
        repaint();
    }
}
