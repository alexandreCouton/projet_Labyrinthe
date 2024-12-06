package view;

import model.GameBoard;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JComponent {
    private JButton m_btnHaut;
    private JButton m_btnBas;
    private JButton m_btnGauche;
    private JButton m_btnDroite;
    private GameBoard m_gameBoard;

    public ControlPanel(GameBoard gameBoard){
        m_gameBoard = gameBoard;
        m_btnHaut = new JButton("Haut");
//        m_btnHaut.addActionListener(e -> {
//            gameBoard.deplacerJoueur(, Direction.HAUT);
//        });
        m_btnBas = new JButton("Bas");
/*        m_btnBas.addActionListener(e -> {
            gameBoard.deplacerJoueur();
        });*/
        m_btnGauche = new JButton("Gauche");
        m_btnDroite = new JButton("Droite");


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
