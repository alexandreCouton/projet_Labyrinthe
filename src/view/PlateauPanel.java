package view;

import model.Plateau;
import javax.swing.*;
import java.awt.*;
import model.Tuile;

public class PlateauPanel extends JPanel {
    private Plateau m_plateau;
    private TuileComponent[][] m_tuiles;
    private JPanel m_panel;

    public PlateauPanel(Plateau plateau) {
        m_plateau = plateau;
        setLayout(new GridLayout(7, 7));
        initTuilesComponents();
    }

    private void initTuilesComponents() {
        m_tuiles = new TuileComponent[7][7];
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                TuileComponent tuileComponent = new TuileComponent(null);
                m_tuiles[y][x] = tuileComponent;
                add(tuileComponent);
            }
        }
    }

    public void updatePlateau() {
        Tuile[][] tuiles = m_plateau.getPlateau();
        for (int y = 0; y < tuiles.length; y++) {
            for (int x = 0; x < tuiles[y].length; x++) {
                Tuile tuile = tuiles[y][x];
                m_tuiles[y][x].setTuile(tuile);
            }
        }
        revalidate();
        repaint();
    }

}
