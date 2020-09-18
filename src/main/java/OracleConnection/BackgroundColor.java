package OracleConnection;

import javax.swing.*;
import java.awt.*;

public class BackgroundColor {
    private final JFrame frame;

    public BackgroundColor(JFrame frame) {
        this.frame=frame;
    }


    public JPanel setGradientPanel() {
        return new JPanel() {
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {

                    Paint p =
                            new GradientPaint(0.0f, 0.0f, new Color(85,107,47, 255),
                                    getWidth(), getHeight(), new Color(0x94E772), true);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };
    }

    public void setButtonColor(JButton button) {
        button.setBackground(new Color(61, 78, 33, 255));
        button.setForeground(Color.white);
    }
}
