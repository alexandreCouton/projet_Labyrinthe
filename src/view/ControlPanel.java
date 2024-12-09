package view;

import controller.GameController;
import model.GameBoard;

import javax.swing.*;
import java.awt.*;
import model.Direction;


/**
 * The ControlPanel class represents the control panel in the Labyrinth game interface.
 * It provides buttons that allow the user to move in the four cardinal directions:
 * up, down, left, and right.
 *
 * Key responsibilities include:
 * - Creating and setting up buttons for each direction (up, down, left, right).
 * - Handling button click events by calling the `deplacer()` method on the GameController
 *   to move the player in the selected direction.
 * - Using a GridLayout to arrange the buttons in a user-friendly layout.
 *
 * This class acts as a user interface component that interacts with the `GameController`
 * to control the player's movements. Each button triggers an action in the controller to
 * move the player in the specified direction.
 */

public class ControlPanel extends JComponent {
    private JButton m_btnHaut;
    private JButton m_btnBas;
    private JButton m_btnGauche;
    private JButton m_btnDroite;
    private GameController m_controller;

    public ControlPanel(GameController controller){
        m_controller = controller;
        m_btnHaut = new JButton("Haut");
        m_btnHaut.addActionListener(e -> {
            m_controller.deplacer(Direction.UP);
        });
        m_btnBas = new JButton("Bas");
        m_btnBas.addActionListener(e -> {
            m_controller.deplacer(Direction.DOWN);
        });
        m_btnGauche = new JButton("Gauche");
        m_btnGauche.addActionListener(e -> {
            m_controller.deplacer(Direction.LEFT);
        });
        m_btnDroite = new JButton("Droite");
        m_btnDroite.addActionListener(e -> {
            m_controller.deplacer(Direction.RIGHT);
        });

        GridLayout layout = new GridLayout(2, 3);

        setLayout(layout);
        add(new JLabel());
        add(m_btnHaut);
        add(new JLabel());
        add(m_btnGauche);
        add(m_btnBas);
        add(m_btnDroite);
    }


}
