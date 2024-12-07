package view;

import model.GameBoard;
import model.ImageHelper;
import model.PlateauObserver;
import model.Position;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JFrame implements PlateauObserver {
    private PlateauPanel plateauPanel;
    private GameBoard gameBoard;
    private JPanel pPanel;
    private JPanel mainPanel;

    public GameDisplay(GameBoard gameBoard) {
        this.gameBoard = gameBoard;

        setTitle("Labyrinthe");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Utiliser BorderLayout pour la disposition principale
        setLayout(new BorderLayout());

        // Créer un panneau principal avec GridBagLayout
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Ajouter le plateauPanel au centre
        plateauPanel = new PlateauPanel(gameBoard);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(plateauPanel, gbc);

        // Ajouter le controlPanel en bas à droite
        ControlPanel controlPanel = new ControlPanel(gameBoard);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.15;
        gbc.weighty = 0.15;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainPanel.add(controlPanel, gbc);

        // Ajouter le mainPanel au centre de la fenêtre
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void updatePlateau() {
        plateauPanel.updatePlateau();
    }

    public void debutPartie() {
        updatePlateau();
    }

    public void deplacementTuile() {
        updatePlateau();
    }

    public void deplacementJoueur() {
        updatePlateau();
    }

    public void captureObjectif() {
        updatePlateau();
    }

    public void finPartie() {
        updatePlateau();
    }

    @Override
    public void updateTile(Position position) {

    }

}
