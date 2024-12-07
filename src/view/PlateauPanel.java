package view;

import model.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import model.ImageHelper;
import model.Tiles;

public class PlateauPanel extends JPanel {
    private GameBoard m_gameBoard;
    private TuileComponent[][] m_tiles;
    private JPanel m_panel;

    public PlateauPanel(GameBoard gameBoard) {
        m_gameBoard = gameBoard;
        setLayout(new GridLayout(7, 7));
        initTuilesComponents();

    }



    private void initTuilesComponents() {
        m_tiles = new TuileComponent[7][7];
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                TuileComponent tuileComponent = new TuileComponent(m_gameBoard.getGameBoard()[y][x]);
                m_tiles[y][x] = tuileComponent;
                add(tuileComponent);
            }
        }
        try {
            m_tiles[0][0].setImage(ImageHelper.merge(m_tiles[0][0].getImage(), "src/img/imgDepart/departBleu.png"));
            m_tiles[0][6].setImage(ImageHelper.merge(m_tiles[0][0].getImage(), "src/img/imgDepart/departJaune.png"));
            m_tiles[6][0].setImage(ImageHelper.merge(m_tiles[0][0].getImage(), "src/img/imgDepart/departRouge.png"));
            m_tiles[6][6].setImage(ImageHelper.merge(m_tiles[0][0].getImage(), "src/img/imgDepart/departVert.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        revalidate();
        repaint();
    }

    public void updatePlateau() {
        Tiles[][] tiles = m_gameBoard.getGameBoard();
        for (int y = 0; y < tiles.length ; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                Tiles tilestemp = tiles[y][x];
                m_tiles[y][x].setTuile(tilestemp);
            }
        }
        // Rajouter l'ajout des spawnpoint et des pions des joueurs
        revalidate();
        repaint();
    }


}
