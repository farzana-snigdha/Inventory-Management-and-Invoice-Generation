package OracleConnection;

import javax.swing.*;
import java.awt.*;

public class GradientPanel {
    private final JFrame frame;

    public GradientPanel(JFrame frame) {
        this.frame=frame;
    }


    public JPanel setGradientPanel() {
        return new JPanel() {
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {

                    Paint p =
                            new GradientPaint(0.0f, 0.0f, new Color(217, 185, 242, 255),
                                    getWidth()/2, getHeight()/2, new Color(118, 28, 187, 255), true);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };
    }
}
