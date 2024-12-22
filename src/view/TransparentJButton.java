package view;
import javax.swing.*;
import java.awt.*;

/**
 * TransparentJButton is a subclass of JButton that creates a button with a transparent appearance,
 * without borders or background. It allows customization of the button's visual appearance while
 * maintaining interactive behavior.
 *<br> * Responsibilities:<br>
 * - Customize the button's appearance by making the background transparent and removing the borders.<br>
 * - Apply a transparency effect to the button.<br>
 * - Provide a "hand" cursor when the user hovers over the button.<br>
 */
public class TransparentJButton extends JButton {
    /**
     * Constructor that initializes the button with a text.
     *
     * @param text the text to display on the button
     */
    public TransparentJButton(String text) {
        super(text);
        setOpaque(false); // Assurez-vous que le bouton n'est pas opaque
        setContentAreaFilled(false); // Assurez-vous que la zone de contenu n'est pas remplie
        setBorderPainted(false); // Assurez-vous que la bordure n'est pas peinte
        setFocusPainted(false); // Assurez-vous que le focus n'est pas peint
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }
    /**
     * Redraws the button with a rounded and semi-transparent background.
     *
     * @param g the graphics used to draw the button
     */
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
