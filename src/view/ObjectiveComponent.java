package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import model.Objective;

public class ObjectiveComponent extends JComponent {
    private Objective m_objective;
    private BufferedImage m_image;

    public ObjectiveComponent(Objective objective) {
        m_objective = objective;
        setLayout(new BorderLayout());
        if(m_objective.getPath() != null){
            m_image = ImageHelper.loadImage(objective.getPath());
        }

        setVisible(true);
    }

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
