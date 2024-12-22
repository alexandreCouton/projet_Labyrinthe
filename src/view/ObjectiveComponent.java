package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import model.Objective;
/**
 * ObjectiveComponent is a JComponent that visually represents an objective in the game.
 * It displays the image of the objective and ensures it is properly scaled and centered
 * within the component.<br>
 *<br>
 * Responsibilities:<br>
 * - Display the image of the objective.<br>
 * - Dynamically adjust the image size to fit the component while maintaining its aspect ratio.<br>
 * - Update the displayed image when the objective changes.<br>
 */
public class ObjectiveComponent extends JComponent {
    private Objective m_objective;
    private BufferedImage m_image;

    /**
     * Constructor that initializes the component with a specific objective.
     *
     * @param objective the objective model to be represented
     */
    public ObjectiveComponent(Objective objective) {
        m_objective = objective;
        setLayout(new BorderLayout());
        if(m_objective.getPath() != null){
            m_image = ImageHelper.loadImage(objective.getPath());
        }

        setVisible(true);
    }
    /**
     * Paints the component by drawing the objective's image, scaled and centered.
     *
     * @param g the Graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (m_image != null) {
            // Scale image to fit component while maintaining aspect ratio
            int componentWidth = getWidth();
            int componentHeight = getHeight();

            // Calculate scaling
            double scaleFactor = Math.min(
                    (double)componentWidth / m_image.getWidth(),
                    (double)componentHeight / m_image.getHeight()
            );

            int scaledWidth = (int)(m_image.getWidth() * scaleFactor);
            int scaledHeight = (int)(m_image.getHeight() * scaleFactor);

            // Center the image
            int x = (componentWidth - scaledWidth) / 2;
            int y = (componentHeight - scaledHeight) / 2;

            // Draw the scaled image
            g.drawImage(m_image, x, y, scaledWidth, scaledHeight, this);
        }
    }

    /**
     * @param objective : The objective you want to set with an image for the view
     */
    public void setObjective(Objective objective) {
        m_objective = objective;

        m_image = ImageHelper.loadImage(objective.getPath());
        repaint();
    }

    /**
     * @param image : set the image in the objective (inside the class)
     */
    public void setImage(BufferedImage image) {
        this.m_image = image;
    }

    /**
     * @return the image of the objective
     */
    public BufferedImage getImage() {
        return this.m_image;
    }
}
