
package Graphics;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
    private JFrame frame;

    public Window(String name, Dimension size, JPanel display) {
        this.frame = new JFrame(name);
        this.frame.setSize(size);
        display.setPreferredSize(size);
        display.setVisible(true);
        display.setFocusable(true);
        this.frame.setDefaultCloseOperation(3);
        this.frame.add(display);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}

