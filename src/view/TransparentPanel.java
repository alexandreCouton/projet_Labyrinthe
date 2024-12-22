package view;

import javax.swing.*;
import java.awt.*;

/**
 * TransparentPanel is a subclass of JPanel that creates a panel with a transparent background,
 * allowing other components or backgrounds to show through.
 *<br>
 * Key responsibilities:<br>
 * - Ensure that the panel is not opaque, allowing transparency.<br>
 * - Provide the ability to override and customize the painting behavior if needed.<br>
 */
public class TransparentPanel extends JPanel {
    public TransparentPanel() {
        setOpaque(false);
    }
    /**
     * Override the paintComponent method to allow custom drawing on the panel if needed.
     *
     * @param g the graphics context used to paint the component
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
