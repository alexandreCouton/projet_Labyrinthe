package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * GameBoardPanel is a JPanel that represents the game board visually in the GUI.
 * It manages the tiles on the board and updates their appearance based on the game state.
 * It also handles player movement and tile updates, ensuring the board is correctly displayed and refreshed.
 *<br>
 * Key responsibilities:<br>
 * - Initializing and displaying the game tiles on a 7x7 grid.<br>
 * - Placing player pieces on the board.<br>
 * - Updating tiles when they are moved or rotated.<br>
 * - Handling the graphical representation of player movements.<br>
 *
 */

public class GameBoardPanel extends JPanel implements PlayerObserver {
    private GameBoard m_gameBoard;
    private TuileComponent[][] m_tilesComponent;

    public GameBoardPanel(GameBoard gameBoard) {
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
                m_tilesComponent[y][x].setImage(ImageHelper.merge(m_tilesComponent[y][x].getImage(), player.getImgPawn()));
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
        m_tilesComponent = new TuileComponent[7][7];
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                TuileComponent tuileComponent = new TuileComponent(m_gameBoard.getGameBoard()[y][x]);
                m_tilesComponent[y][x] = tuileComponent;
                add(tuileComponent);
            }
        }

        revalidate();
        repaint();
    }

    public TuileComponent getFlyingTileComponent(){
        return new TuileComponent(m_gameBoard.getFlyTile());
    }

    /**
     * @param pos : the row's position you want to update on the view
     */
    public void updateGameBoard(Position pos) {

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
            for (int row = 0; row < m_tilesComponent[y].length; row++) {
                TuileComponent tuileComponent = m_tilesComponent[y][row];
                tuileComponent.setTile(m_gameBoard.getGameBoard()[y][row]);
                for(int i=0;i < m_gameBoard.getGameBoard()[y][row].getRotateIndex();i++){
                    tuileComponent.setImage(ImageHelper.rotateClockwise(tuileComponent.getImage()));
                }
            }

        }

        if (isColumnMovable) {
            // Mettre à jour la colonne
            for (int col = 0; col < m_tilesComponent.length; col++) {
                TuileComponent tuileComponent = m_tilesComponent[col][x];
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
     * @param oldPos : the old position of the player's pawn
     * @param newPos : the new position of the player's pawn
     * @param path : the path of the player's pawn
     */
    @Override
    public void movePlayer(Position oldPos, Position newPos, String path) {
        int x = newPos.getPositionX();
        int y = newPos.getPositionY();
        try {
            updateTile(oldPos);
            updateTile(newPos);
            m_tilesComponent[y][x].setImage(ImageHelper.merge(m_tilesComponent[y][x].getImage(), path));

            } catch (IOException e) {
            throw new RuntimeException(e);
        }
        revalidate();
        repaint();

    }
    /**
     * Updates a specific tile visually.
     *
     * @param pos the position of the tile to update
     */
    public void updateTile(Position pos){
        int x = pos.getPositionX();
        int y = pos.getPositionY();
        TuileComponent tuileComponent = m_tilesComponent[y][x];
        tuileComponent.setTile(m_gameBoard.getTile(new Position(x, y)));
        for(int i=0;i < m_gameBoard.getGameBoard()[y][x].getRotateIndex();i++){
            tuileComponent.setImage(ImageHelper.rotateClockwise(tuileComponent.getImage()));
        }
        revalidate();
        repaint();
    }
    /**
     * Updates the visual representation of a player at their current position.
     *
     * @param player the player to update
     * @param path   the path to the player's pawn image
     */
    public void actualizePlayer(Player player, String path){
        try {
            int x = player.getPositionX();
            int y = player.getPositionY();
            m_tilesComponent[y][x].setImage(ImageHelper.merge(m_tilesComponent[y][x].getImage(), path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
