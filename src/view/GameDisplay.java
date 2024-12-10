package view;

import controller.GameController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameDisplay extends JFrame implements PlateauObserver {
    private PlateauPanel m_plateauPanel;
    private Game m_game;
    private JPanel pPanel;
    private JPanel mainPanel;
    private GameController m_gameController;
    private JPanel m_buttonSection;
    private JPanel m_fliyngTilePanel;
    private int m_colorRotation = 0;

    /**
     * Constructeur de la classe GameDisplay.
     * Initialise la fenêtre de jeu, le panneau principal, le panneau de plateau,
     * le panneau de contrôle, les boutons et la section de boutons.
     *
     * @param game le jeu
     * @param plateauPanel le panneau de plateau
     */
    public GameDisplay(Game game, PlateauPanel plateauPanel) {
        this.m_game = game;
        m_gameController = new GameController(m_game);
        initializeFrame();
        initializeMainPanel();
        initializePlateauPanel(plateauPanel);
        initializeControlPanel();
        generateButtons();
        generateFlyingTilePlace();
        initializeButtonSection();
        placePawns();
        add(mainPanel, BorderLayout.CENTER); // Ajoute le panneau principal à la fenêtre
        setVisible(true);
    }

    /**
     * Initialise les propriétés de la fenêtre.
     */
    private void initializeFrame() {
        setTitle("Labyrinthe");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    /**
     * Initialise le panneau principal avec un GridBagLayout.
     */
    private void initializeMainPanel() {
        mainPanel = new JPanel(new GridBagLayout());
    }

    /**
     * Initialise le panneau de plateau et l'ajoute au panneau principal.
     *
     * @param plateauPanel le panneau de plateau
     */
    private void initializePlateauPanel(PlateauPanel plateauPanel) {
        m_plateauPanel = plateauPanel;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(m_plateauPanel, gbc);
    }

    /**
     * Initialise le panneau de contrôle et l'ajoute au panneau principal.
     */
    private void initializeControlPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        ControlPanel controlPanel = new ControlPanel(m_gameController);
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.15;
        gbc.weighty = 0.15;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(controlPanel, gbc);
    }

    /**
     * Génère les boutons pour insérer des tuiles autour du plateau.
     */
    private void generateButtons() {
        generateButtonLeft();
        generateButtonRight();
        generateButtonTop();
        generateButtonBottom();
    }

    /**
     * Initialise la section de boutons avec les boutons de capture et de tour suivant.
     */
    private void initializeButtonSection() {
        m_buttonSection = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton captureButton = createCaptureButton();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        m_buttonSection.add(captureButton, gbc);

        JButton nextTurnButton = createNextTurnButton();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.EAST;
        m_buttonSection.add(nextTurnButton, gbc);

        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        mainPanel.add(m_buttonSection, gbc);
    }

    /**
     * Crée le bouton de capture.
     *
     * @return le bouton de capture
     */
    private JButton createCaptureButton() {
        JButton captureButton = new JButton("Capture");
        captureButton.addActionListener(e -> m_gameController.captureObjectif());
        captureButton.setBackground(Color.GREEN);
        captureButton.setForeground(Color.WHITE);
        captureButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        captureButton.setPreferredSize(new Dimension(150, 50)); // Ajustement de la taille
        return captureButton;
    }

    /**
     * Crée le bouton de tour suivant.
     *
     * @return le bouton de tour suivant
     */
    private JButton createNextTurnButton() {
        JButton nextTurnButton = new JButton("Tour Suivant");
        nextTurnButton.addActionListener(e -> {
            m_gameController.nextTurn();
            m_colorRotation = (m_colorRotation + 1) % 4;
            switch (m_colorRotation) {
                case 0:
                    m_plateauPanel.setBackground(Color.BLUE);
                    break;
                case 1:
                    m_plateauPanel.setBackground(Color.YELLOW);
                    break;
                case 2:
                    m_plateauPanel.setBackground(Color.RED);
                    break;
                case 3:
                    m_plateauPanel.setBackground(Color.GREEN);
                    break;
            }
        });
        nextTurnButton.setBackground(Color.ORANGE);
        nextTurnButton.setForeground(Color.WHITE);
        nextTurnButton.setBorder(BorderFactory.createBevelBorder(1));
        nextTurnButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextTurnButton.setPreferredSize(new Dimension(150, 50)); // Ajustement de la taille
        return nextTurnButton;
    }

    /**
     * Place les pions des joueurs sur le plateau.
     */
    private void placePawns() {
        for (int i = 0; i < 4; i++) {
            m_plateauPanel.placePawn(m_game.getPlayer(i));
        }
    }

    /**
     * Génère les boutons pour insérer des tuiles à gauche du plateau.
     */
    private void generateButtonLeft() {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                Button button = new Button("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> m_gameController.placeFlyingTile(new Position(0, finalI)));
                buttonPanel.add(button);
            }
        }
        gbc.gridx = 2; // Décalé de 1
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(buttonPanel, gbc);
    }

    /**
     * Génère les boutons pour insérer des tuiles à droite du plateau.
     */
    private void generateButtonRight() {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                Button button = new Button("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> m_gameController.placeFlyingTile(new Position(6, finalI)));
                buttonPanel.add(button);
            }
        }
        gbc.gridx = 4; // Décalé de 1
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(buttonPanel, gbc);
    }

    /**
     * Génère les boutons pour insérer des tuiles en haut du plateau.
     */
    private void generateButtonTop() {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                Button button = new Button("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> m_gameController.placeFlyingTile(new Position(finalI, 0)));
                buttonPanel.add(button);
            }
        }
        gbc.gridx = 3; // Décalé de 1
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(buttonPanel, gbc);
    }

    /**
     * Génère les boutons pour insérer des tuiles en bas du plateau.
     */
    private void generateButtonBottom() {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                Button button = new Button("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> m_gameController.placeFlyingTile(new Position(finalI, 6)));
                buttonPanel.add(button);
            }
        }
        gbc.gridx = 3; // Décalé de 1
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(buttonPanel, gbc);
    }

    /**
     * Génère le panneau de la tuile volante et le place en bas à gauche du plateau et des boutons de gauche.
     */
    private void generateFlyingTilePlace() {
        GridBagConstraints gbc = new GridBagConstraints();
        m_fliyngTilePanel = new JPanel(new GridBagLayout()); // Utiliser GridBagLayout pour plus de flexibilité
        TuileComponent flyingTile = m_plateauPanel.getFlyingTileComponent();
        // Ajuster la taille préférée de la tuile volante pour obtenir un bon rapport hauteur/largeur
        Dimension preferredSize = new Dimension(100, 100); // Ajustez ces valeurs selon vos besoins
        flyingTile.setPreferredSize(preferredSize);

        // Ajouter la tuile volante au panneau
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        m_fliyngTilePanel.add(flyingTile, gbc);

        // Ajouter le bouton de rotation au panneau
        Button flyingTileRotationButton = new Button("Tourner");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.10;
        m_fliyngTilePanel.add(flyingTileRotationButton, gbc);

        // Ajouter le panneau de la tuile volante au panneau principal
        gbc.gridx = 1; // Position x = 1
        gbc.gridy = 2; // Position y = 2
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.1; // Réduire le poids pour éviter l'écrasement
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER; // Centrer le panneau
        mainPanel.add(m_fliyngTilePanel, gbc);

    }

    /**
     * Met à jour l'affichage du plateau.
     */
    public void updatePlateau() {
        //plateauPanel.updatePlateau();
    }

    /**
     * Met à jour l'affichage du plateau au début de la partie.
     */
    public void debutPartie() {
        updatePlateau();
    }

    /**
     * Met à jour l'affichage du plateau après le déplacement d'une tuile.
     */
    public void deplacementTuile() {
        updatePlateau();
    }

    /**
     * Met à jour l'affichage du plateau après le déplacement d'un joueur.
     */
    public void deplacementJoueur() {
        updatePlateau();
    }

    /**
     * Met à jour l'affichage du plateau après la capture d'un objectif.
     */
    public void captureObjectif() {
        updatePlateau();
    }

    /**
     * Met à jour l'affichage du plateau à la fin de la partie.
     */
    public void finPartie() {
        updatePlateau();
    }

    /**
     * Met à jour l'affichage de la tuile à la position spécifiée.
     *
     * @param position la position de la tuile
     */
    @Override
    public void updateTile(Position position) {
        m_plateauPanel.updatePlateau(position);
    }
}
