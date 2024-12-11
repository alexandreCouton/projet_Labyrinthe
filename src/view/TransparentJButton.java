package view;
import javax.swing.*;
import java.awt.*;

public class TransparentJButton extends JButton {
    public TransparentJButton(String text) {
        super(text);
        setOpaque(false); // Assurez-vous que le bouton n'est pas opaque
        setContentAreaFilled(false); // Assurez-vous que la zone de contenu n'est pas remplie
        setBorderPainted(false); // Assurez-vous que la bordure n'est pas peinte
        setFocusPainted(false); // Assurez-vous que le focus n'est pas peint
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f)); // 0.5f pour 50% d'opacité
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Dessine un rectangle arrondi
        g2.dispose();
        super.paintComponent(g);
    }
}
