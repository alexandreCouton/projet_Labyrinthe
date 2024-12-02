package view;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import model.Tuile;
public class TuileComponent extends JComponent {
    private Tuile m_tuile;
    private BufferedImage m_image;

    public TuileComponent(Tuile tuile) {
//        m_tuile = tuile;
//        m_image = tuile.getImage();
        setLayout(new BorderLayout());
        setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (m_image != null) {
            g.drawImage(m_image, 0, 0, getWidth(), getHeight(), this);
        }
    }
    public void setTuile(Tuile tuile) {
        m_tuile = tuile;
        m_image = tuile.getImage();
        repaint();
    }




}
