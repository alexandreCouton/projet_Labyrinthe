package view;

import controller.GameController;
import model.GameBoard;

import javax.swing.*;
import java.awt.*;
import model.Direction;

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
