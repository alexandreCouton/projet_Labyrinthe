package view;

import controller.GameController;
import model.*;

import javax.swing.*;
import java.awt.*;

public class GameDisplay extends JFrame implements PlateauObserver {
    private PlateauPanel m_plateauPanel;
    private Game m_game;
    private JPanel pPanel;
    private JPanel mainPanel;
    private GameController m_gameController;

    public GameDisplay(Game game, PlateauPanel plateauPanel) {
        this.m_game = game;
        m_gameController = new GameController(m_game);
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
        m_plateauPanel =plateauPanel;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(m_plateauPanel, gbc);

        // Ajouter le controlPanel en bas à droite
        ControlPanel controlPanel = new ControlPanel(m_game.getGameBoard());
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.15;
        gbc.weighty = 0.15;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainPanel.add(controlPanel, gbc);

        generateButtonLeft();
        generateButtonRight();
        generateButtonTop();
        generateButtonBottom();




        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void generateButtonLeft(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < 7; i ++ ) {
            if(i%2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                Button button = new Button("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> {
                    m_gameController.placeFlyingTile(new Position(0, finalI));
                });
                buttonPanel.add(button);
            }
        }
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(buttonPanel, gbc);
    }

    private void generateButtonRight(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < 7; i ++ ) {
            if(i%2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                Button button = new Button("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> {
                    m_gameController.placeFlyingTile(new Position(6, finalI));
                });
                buttonPanel.add(button);
            }
        }
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(buttonPanel, gbc);
    }

    private void generateButtonTop(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < 7; i ++ ) {
            if(i%2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                Button button = new Button("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> {
                    m_gameController.placeFlyingTile(new Position(finalI, 0));
                });
                buttonPanel.add(button);
            }
        }
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(buttonPanel, gbc);
    }

    private void generateButtonBottom(){
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < 7; i ++ ) {
            if(i%2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                Button button = new Button("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> {
                    m_gameController.placeFlyingTile(new Position(finalI, 6));
                });
                buttonPanel.add(button);
            }
        }
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(buttonPanel, gbc);
    }

    public void updatePlateau() {
        //plateauPanel.updatePlateau();
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
        m_plateauPanel.updatePlateau(position);

    }

}
