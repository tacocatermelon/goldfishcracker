import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class Frame {

    DisplayPanel panel;

    public Frame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        panel = new DisplayPanel();
        frame.setVisible(true);
        frame.add(panel);
        panel.setSize(300,100);
        frame.setVisible(true);
    }

    public DisplayPanel getPanel() {
        return panel;
    }

}
