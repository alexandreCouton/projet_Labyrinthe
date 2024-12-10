package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * PlateauPanel is a JPanel that represents the game board visually in the GUI.
 * It manages the tiles on the board and updates their appearance based on the game state.
 * It also handles player movement and tile updates, ensuring the board is correctly displayed and refreshed.
 *
 * Key responsibilities:
 * - Initializing and displaying the game tiles on a 7x7 grid.
 * - Placing player pieces on the board.
 * - Updating tiles when they are moved or rotated.
 * - Handling the graphical representation of player movements.
 */

public class PlateauPanel extends JPanel implements PlayerObserver {
    private GameBoard m_gameBoard;
    private TuileComponent[][] m_tiles;
    private JPanel m_panel;
    private int m_colorRotation;
    
    public PlateauPanel(GameBoard gameBoard) {
        m_gameBoard = gameBoard;
        setLayout(new GridLayout(7, 7));
        initTuilesComponents();
        setBackground(Color.BLUE);

    }

    /**
     * @param player : the player's pawn
     */
    public void placePawn(Player player) {

            int x = player.getPosition().getPositionX();
            int y = player.getPosition().getPositionY();
            try {
                m_tiles[y][x].setImage(ImageHelper.merge(m_tiles[y][x].getImage(), player.getImgPion()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        revalidate();
        repaint();
    }

    /**
     * Initializes all the Tiles on the view board
     */
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
            m_tiles[0][6].setImage(ImageHelper.merge(m_tiles[0][6].getImage(), "src/img/imgDepart/departJaune.png"));
            m_tiles[6][0].setImage(ImageHelper.merge(m_tiles[6][0].getImage(), "src/img/imgDepart/departRouge.png"));
            m_tiles[6][6].setImage(ImageHelper.merge(m_tiles[6][6].getImage(), "src/img/imgDepart/departVert.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        revalidate();
        repaint();
    }

    /**
     * @param pos : the row's position you want to update on the view
     */
    public void updatePlateau(Position pos) {

        int x = pos.getPositionX();
        int y = pos.getPositionY();
        Boolean isRow = false;
        Boolean isColumnMovable = false;
        if(x == 0 || x == 6){
            isRow = true;
        }
        if(y == 0 || y == 6){
            isColumnMovable = true;
        }

        if (isRow) {
            // Update the row
            for (int row = 0; row < m_tiles[y].length; row++) {
                TuileComponent tuileComponent = m_tiles[y][row];
                tuileComponent.setTile(m_gameBoard.getGameBoard()[y][row]);
                for(int i=0;i < m_gameBoard.getGameBoard()[y][row].getRotateIndex();i++){
                    tuileComponent.setImage(ImageHelper.rotateClockwise(tuileComponent.getImage()));
                }
            }

        }

        if (isColumnMovable) {
            // Mettre à jour la colonne
            for (int col = 0; col < m_tiles.length; col++) {
                TuileComponent tuileComponent = m_tiles[col][x];
                tuileComponent.setTile(m_gameBoard.getGameBoard()[col][x]);
                for(int i=0;i < m_gameBoard.getGameBoard()[col][x].getRotateIndex();i++){
                    tuileComponent.setImage(ImageHelper.rotateClockwise(tuileComponent.getImage()));

                }
            }
        }

        // Rajouter l'ajout des spawnpoint et des pions des joueurs
        revalidate();
        repaint();
    }


    /**
     * @param pos : The position where the pawn has to move on
     * @param path : the path of the player's pawn
     */
    @Override
    public void movePlayer(Position pos, String path) {
        int x = pos.getPositionX();
        int y = pos.getPositionY();
        try {
            m_tiles[y][x].setImage(ImageHelper.merge(m_tiles[y][x].getImage(), path));
            m_colorRotation++;
            m_colorRotation = m_colorRotation % 4;
            switch (m_colorRotation){
                case 0 :
                    setBackground(Color.BLUE);
                    break;
                case 1 :
                    setBackground(Color.YELLOW);
                    break;
                case 2 :
                    setBackground(Color.RED);
                    break;
                case 3 :
                    setBackground(Color.GREEN);
                    break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        revalidate();
        repaint();

    }
}
