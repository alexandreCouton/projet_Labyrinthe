package view;

import controller.GameController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * GameDisplay is a JFrame that represents the main graphical interface for the game.
 * It manages the game board, player controls, flying tile interactions, and game objectives display.
 * It also updates the game state based on user interactions.
 */
public class GameDisplay extends JFrame implements GameBoardObserver {
    private GameBoardPanel m_gameBoardPanel;
    private Game m_game;
    private JPanel m_mainPanel;
    private GameController m_gameController;
    private JPanel m_buttonSection;
    private JPanel m_fliyngTilePanel;
    private int m_colorRotation = 0;
    private JPanel objectivePanel;

    /**
     * Constructs a new GameDisplay window and initializes the various panels and controls.
     *<br>
     * @param game The game object that holds the current game state.<br>
     * @param gameBoardPanel The panel representing the game board.<br>
     */
    public GameDisplay(Game game, GameBoardPanel gameBoardPanel) {
        this.m_game = game;
        m_gameController = new GameController(m_game);
        initializeFrame();
        initializeMainPanel();
        initializePlateauPanel(gameBoardPanel);
        initializeControlPanel();
        generateButtons();
        generateFlyingTilePlace();
        initializeButtonSection();
        placePawns();
        add(m_mainPanel, BorderLayout.CENTER); // Ajoute le panneau principal à la fenêtre
        showObjective();
        setVisible(true);
    }

    /**
     * Initializes the main frame (JFrame) with the necessary properties.
     * This includes setting the title, size, and default close operation.
     */
    private void initializeFrame() {
        setTitle("Labyrinthe");
        setSize(1300, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    /**
     * Initializes the main panel that contains the background and layout for the game.
     */
    private void initializeMainPanel() {
        BufferedImage background = ImageHelper.loadImage("./img/background/fondLabyrinthe.jpg");
        m_mainPanel = new BackgroundPanel(background);
        m_mainPanel.setLayout(new GridBagLayout());

    }
    /**
     * Initializes and adds the game board panel to the main panel.
     *
     * @param gameBoardPanel The panel representing the game board.
     */
    private void initializePlateauPanel(GameBoardPanel gameBoardPanel) {
        m_gameBoardPanel = gameBoardPanel;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        m_mainPanel.add(m_gameBoardPanel, gbc);
    }

    /**
     * Initializes and adds the control panel to handle player actions like "Capture" and "Next Turn".
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
        m_mainPanel.add(controlPanel, gbc);
    }

    /**
     * Generates the directional buttons (left, right, top, bottom) for placing tiles on the board.
     */
    private void generateButtons() {
        generateButtonLeft();
        generateButtonRight();
        generateButtonTop();
        generateButtonBottom();
    }

    /**
     * Initializes the section of the interface with buttons for controlling game actions.
     */
    private void initializeButtonSection() {
        m_buttonSection = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton captureButton = createCaptureButton();
        captureButton.addActionListener(e -> { 

            m_gameController.captureObjective();

            updateObjectivePanel() ;
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        m_buttonSection.add(captureButton, gbc);

        JButton nextTurnButton = createNextTurnButton();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);
        m_buttonSection.add(nextTurnButton, gbc);

        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        m_mainPanel.add(m_buttonSection, gbc);
    }

    /**
     * Creates the "Capture" button and returns it.
     *
     * @return The capture button.
     */
    private JButton createCaptureButton() {
        JButton captureButton = new JButton("Capture");
        captureButton.addActionListener(e -> {
            m_gameController.captureObjective();
            updateObjectivePanel();
        });
        captureButton.setBackground(Color.GREEN);
        captureButton.setForeground(Color.WHITE);
        captureButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return captureButton;
    }

    /**
     * Creates the "Next Turn" button and returns it.
     *
     * @return The next turn button.
     */
    private JButton createNextTurnButton() {
        JButton nextTurnButton = new JButton("Tour Suivant");
        nextTurnButton.addActionListener(e -> {
            m_gameController.nextTurn();
            m_gameBoardPanel.actualizePlayer(m_game.getCurrentPlayer(), m_game.getCurrentPlayer().getPath());
            updateObjectivePanel();
            m_colorRotation = (m_colorRotation + 1) % 4;
            switch (m_colorRotation) {
                case 0:
                    m_gameBoardPanel.setBackground(Color.BLUE);
                    break;
                case 1:
                    m_gameBoardPanel.setBackground(Color.YELLOW);
                    break;
                case 2:
                    m_gameBoardPanel.setBackground(Color.RED);
                    break;
                case 3:
                    m_gameBoardPanel.setBackground(Color.GREEN);
                    break;
            }
        });
        nextTurnButton.setBackground(Color.ORANGE);
        nextTurnButton.setForeground(Color.WHITE);
        nextTurnButton.setBorder(BorderFactory.createBevelBorder(1));
        nextTurnButton.setFont(new Font("Arial", Font.BOLD, 16));
        return nextTurnButton;
    }

    /**
     * Places the players' pawns on the game board at the start of the game.
     */
    private void placePawns() {
        for (int i = 0; i < 4; i++) {
            m_gameBoardPanel.placePawn(m_game.getPlayer(i));
        }
    }

    /**
     * Generates and places buttons on the left side of the game board for tile placement.
     */
    private void generateButtonLeft() {
        GridBagConstraints gbc = new GridBagConstraints();
        TransparentPanel buttonPanel = new TransparentPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                TransparentJButton button = new TransparentJButton("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> m_gameController.placeFlyingTile(new Position(0, finalI)));
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
        m_mainPanel.add(buttonPanel, gbc);
    }
    /**
     * Generates and places buttons on the right side of the game board for tile placement.
     */
    private void generateButtonRight() {
        GridBagConstraints gbc = new GridBagConstraints();
        TransparentPanel buttonPanel = new TransparentPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                TransparentJButton button = new TransparentJButton("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> m_gameController.placeFlyingTile(new Position(6, finalI)));
                buttonPanel.add(button);
            }
        }
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;
        m_mainPanel.add(buttonPanel, gbc);
    }

    /**
     * Generates and places buttons on the top side of the game board for tile placement.
     */
    private void generateButtonTop() {
        GridBagConstraints gbc = new GridBagConstraints();
        TransparentPanel buttonPanel = new TransparentPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                TransparentJButton button = new TransparentJButton("inserer tuile");
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
        m_mainPanel.add(buttonPanel, gbc);
    }

    /**
     * Generates and places buttons on the bottom side of the game board for tile placement.
     */
    private void generateButtonBottom() {
        GridBagConstraints gbc = new GridBagConstraints();
        TransparentPanel buttonPanel = new TransparentPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                buttonPanel.add(new JLabel());
            } else {
                TransparentJButton button = new TransparentJButton("inserer tuile");
                int finalI = i;
                button.addActionListener(actionEvent -> m_gameController.placeFlyingTile(new Position(finalI, 6)));
                buttonPanel.add(button);
            }
        }
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        m_mainPanel.add(buttonPanel, gbc);
    }

    /**
     * Initializes and displays the flying tile control panel at the bottom left of the window.
     * This includes a rotate button to manipulate the flying tile.
     */
    private void generateFlyingTilePlace() {
        GridBagConstraints gbc = new GridBagConstraints();
        m_fliyngTilePanel = new TransparentPanel();
        m_fliyngTilePanel.setLayout(new GridBagLayout());
        TuileComponent flyingTile = m_gameBoardPanel.getFlyingTileComponent();
        Dimension preferredSize = new Dimension(100, 100); // Ajustez ces valeurs selon vos besoins
        flyingTile.setPreferredSize(preferredSize);

        JLabel clearLabel1 = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 2;
        m_fliyngTilePanel.add(clearLabel1, gbc);

        // Ajouter la tuile volante au panneau
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        m_fliyngTilePanel.add(flyingTile, gbc);

        // Ajouter le bouton de rotation au panneau
        Button flyingTileRotationButton = new Button("Tourner");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplir seulement horizontalement
        flyingTileRotationButton.addActionListener(e -> {
            m_gameController.rotateFlyingTile();
            flyingTile.updateRotateTile(m_game.getGameBoard().getFlyTile());
        });

        m_fliyngTilePanel.add(flyingTileRotationButton, gbc);
        JLabel clearLabel = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 2;
        m_fliyngTilePanel.add(clearLabel, gbc);

        gbc.gridx = 1; // Position x = 1
        gbc.gridy = 2; // Position y = 2
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 5;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        m_mainPanel.add(m_fliyngTilePanel, gbc);
    }
    /**
     * Updates the objective panel to reflect the current player’s objectives in the game.
     */
    private void updateObjectivePanel() {
        objectivePanel.removeAll();
        if (m_game.getCurrentPlayer().getLstObjective().isEmpty()) {
            GridLayout layout = new GridLayout(2,1);
            JLabel noObjective = new JLabel("Retourner à la case départ pour gagner !!!");
            objectivePanel.setLayout(layout);
            objectivePanel.add(noObjective);
            JButton endGameButton = new JButton("Valider fin de partie");
            endGameButton.addActionListener(e -> {
                m_gameController.finishGame();
            });
            endGameButton.setPreferredSize(new Dimension(100, 30));
            endGameButton.setBackground(new Color(255, 105, 180)); // Couleur de fond rose
            endGameButton.setForeground(Color.WHITE); // Texte en blanc
            endGameButton.setFont(new Font("Arial", Font.BOLD, 12));
            endGameButton.setBorder(BorderFactory.createRaisedBevelBorder());
            objectivePanel.add(endGameButton);
        }else {
            for (Objective obj : m_game.getCurrentPlayer().getLstObjective()) {
                ObjectiveComponent objComponent = new ObjectiveComponent(obj);
                objComponent.setPreferredSize(new Dimension(75, 75));
                objComponent.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                objectivePanel.add(objComponent);
            }
        }

        objectivePanel.revalidate();
        objectivePanel.repaint();
    }
    /**
     * Displays the objectives for the current player at the start of the game or when they change.
     */
    private void showObjective() {
        objectivePanel = new JPanel(new FlowLayout());
        objectivePanel.setOpaque(true);

        for (Objective obj : m_game.getCurrentPlayer().getLstObjective()) {
            ObjectiveComponent objComponent = new ObjectiveComponent(obj);
            objComponent.setPreferredSize(new Dimension(75, 75)); // Set a specific size
            objComponent.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            objectivePanel.add(objComponent);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx =0.1;
        gbc.weighty = 0.3;
        gbc.fill = GridBagConstraints.BOTH;

        m_buttonSection.add(objectivePanel, gbc);

        // Ensure the changes are displayed
        m_buttonSection.revalidate();
        m_buttonSection.repaint();
    }
    /**
     * Updates the flying tile on the panel when its state changes (e.g., after rotation).
     */
    public void updateFlyingTile() {
        TuileComponent tuile = ((TuileComponent) m_fliyngTilePanel.getComponent(1));
        tuile.setTile(m_game.getGameBoard().getFlyTile());
        for (int i = 0; i < m_game.getGameBoard().getFlyTile().getRotateIndex(); i++) {
            tuile.updateRotateTile(m_game.getGameBoard().getFlyTile());
        }
        revalidate();
        repaint();
    }

    /**
     * Updates the display of the game board when the board state changes.
     */
    @Override
    public void endGame() {

        m_mainPanel = new BackgroundPanel(ImageHelper.loadImage("./img/background/end.jpg"));
        m_mainPanel.setLayout(new GridBagLayout());

        JLabel congratsLabel = new JLabel("Félicitations, " + m_game.getCurrentPlayer().toString() + " a gagné !");
        congratsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        congratsLabel.setHorizontalAlignment(JLabel.CENTER);
        congratsLabel.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        
        m_mainPanel.add(congratsLabel, gbc);
        
        getContentPane().removeAll();
        getContentPane().add(m_mainPanel);
        
        revalidate();
        repaint();
    }


    /**
     * Updates the tile display at the specified position on the game board.
     *
     * @param position The position of the tile to be updated.
     */
    @Override
    public void updateTile(Position position) {
        m_gameBoardPanel.updateGameBoard(position);
        updateFlyingTile();
    }
}
