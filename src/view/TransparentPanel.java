package view;

import javax.swing.*;
import java.awt.*;
public class TransparentPanel extends JPanel {
    public TransparentPanel() {
        setOpaque(false); // Assurez-vous que le panneau n'est pas opaque
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vous pouvez ajouter du code ici si vous avez besoin de dessiner quelque chose en plus
    }
}
