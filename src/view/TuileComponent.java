package view;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import model.Tiles;
import model.TilesObserver;

import model.ImageHelper;

/**
 * TuileComponent is a JComponent that represents a tile on the game board in the GUI.
 * It displays the graphical representation of a tile, including handling its rotation
 * and updating its image based on the current state of the tile.
 *
 * Key responsibilities:
 * - Displaying the image of the tile in the GUI.
 * - Rotating the tile when notified by the observer pattern.
 * - Updating the tile image whenever it changes.
 */

public class TuileComponent extends JComponent implements TilesObserver {
    private Tiles m_tiles;
    private BufferedImage m_image;

    public TuileComponent(Tiles tiles) {
        m_tiles = tiles;
        setLayout(new BorderLayout());
        setVisible(true);
        setTile(tiles);
        for(int i=0;i<tiles.getRotateIndex()%4;i++){
            m_image=ImageHelper.rotateClockwise(m_image);
        }
    }

// has already his documentation
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (m_image != null) {
            g.drawImage(m_image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * @param tiles : The tiles you want to set with an image for the view
     */
    public void setTile(Tiles tiles) {
        try {
            m_tiles = tiles;
            BufferedImage baseImage = ImageHelper.loadImage(tiles.getPath());
            if (tiles.getObjective() != null && tiles.getObjective().getPath() != null) {
                BufferedImage objectiveImage = ImageHelper.loadImage(tiles.getObjective().getPath());
                objectiveImage = ImageHelper.resizeImage(objectiveImage, 100, 100);
                Graphics g = baseImage.getGraphics();
                // Positionner l'image de l'objectif sur l'image principale
                int x = (baseImage.getWidth() - objectiveImage.getWidth()) / 2; // Centré horizontalement
                int y = (baseImage.getHeight() - objectiveImage.getHeight()) / 2; // Centré verticalement
                g.drawImage(objectiveImage, x, y, null);
                g.dispose();
                m_image = baseImage;
            } else {
                m_image = baseImage;
            }
            repaint();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param image : set the image in the tile (inside the class)
     */
    public void setImage(BufferedImage image){
        this.m_image = image;
    }

    /**
     * @return the image of the tile
     */
    public BufferedImage getImage(){
        return this.m_image;
    }


    /**
     * @param tile : Current tile you want to rotate
     */
    public void updateRotateTile(Tiles tile) {
        m_image = ImageHelper.rotateClockwise(m_image);
        revalidate();
        repaint();
    }


}
