package view;

import model.GameBoard;

import javax.swing.*;
import java.awt.*;

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
                TuileComponent tuileComponent = new TuileComponent(null);
                m_tiles[y][x] = tuileComponent;
                add(tuileComponent);
            }
        }
    }

    public void updatePlateau() {
        Tiles[][] tiles = m_gameBoard.getGameBoard();
        for (int y = 0; y < tiles.length ; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                Tiles tilestemp = tiles[y][x];
                m_tiles[y][x].setTuile(tilestemp);
            }
        }
        revalidate();
        repaint();
    }

}
