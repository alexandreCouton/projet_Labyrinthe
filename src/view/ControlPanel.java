package view;

import model.Plateau;
import model.TuileOuverture;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JComponent {
    private JButton m_btnHaut;
    private JButton m_btnBas;
    private JButton m_btnGauche;
    private JButton m_btnDroite;
    private Plateau m_plateau;

    public ControlPanel(Plateau plateau){
        m_plateau = plateau;
        m_btnHaut = new JButton("Haut");
//        m_btnHaut.addActionListener(e -> {
//            plateau.deplacerJoueur(, TuileOuverture.HAUT);
//        });
        m_btnBas = new JButton("Bas");
/*        m_btnBas.addActionListener(e -> {
            plateau.deplacerJoueur();
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
