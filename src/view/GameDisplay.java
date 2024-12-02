package view;

import model.Plateau;
import javax.swing.*;
import java.awt.*;


public class GameDisplay extends JFrame implements PlateauObserver {
    private PlateauPanel plateauPanel;
    private Plateau plateau;
    private JPanel mainPanel;

    public GameDisplay(Plateau plateau) {
        this.plateau = plateau;

        setTitle("Labyrinthe");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//        GridLayout layout = new GridLayout(7,7);
//        mainPanel = new JPanel(layout);
//        updatePlateau();
//        add(mainPanel);
//        setVisible(true);
        GridLayout layout = new GridLayout(7, 7);
        mainPanel = new JPanel(layout);

        plateauPanel = new PlateauPanel(plateau);
        mainPanel.add(plateauPanel);

        add(plateauPanel);
        setVisible(true);

    }

    public void updatePlateau() {
//        mainPanel.removeAll();
//        Tuile[][] tuiles = plateau.getPlateau();
//
//        for (int y = 0; y < tuiles.length; y++) {
//            for (int x = 0; x < tuiles[y].length; x++) {
//                JLabel label = new JLabel();
//                Tuile tuile = tuiles[y][x];
//                if (tuile != null) {
//                    ImageIcon icon = new ImageIcon(tuile.getImage());
//                    label.setIcon(icon);
//                }
//                mainPanel.add(label);
//            }
//        }
//
//        mainPanel.revalidate();
//        mainPanel.repaint();
        plateauPanel.updatePlateau();
    }

    public void debutPartie() {
//        mainPanel.removeAll();
//        Tuile[][] tuiles = plateau.getPlateau();
//
//        for (int y = 0; y < 7; y++) {
//            for (int x = 0; x < 7; x++) {
//                JLabel label = new JLabel();
//                Tuile tuile = tuiles[y][x];
//                if (tuile != null) {
//                    ImageIcon icon = new ImageIcon(tuile.getImage());
//                    label.setIcon(icon);
//                }
//                mainPanel.add(label);
//            }
//        }
//        mainPanel.revalidate();
//        mainPanel.repaint();
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

}
