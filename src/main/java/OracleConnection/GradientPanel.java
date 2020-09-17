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
                    final int R = 100;
                    final int G = 100;
                    final int B = 200;
                    Paint p =
                            new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                                    getWidth(), getHeight(), new Color(R, G, B, 255), true);
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
