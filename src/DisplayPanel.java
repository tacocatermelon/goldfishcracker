import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

// this class is a subclass of JPanel
public class DisplayPanel extends JPanel implements ActionListener, KeyListener{
    private JButton w;
    private JButton a;
    private JButton s;
    private JButton d;
    private JTextField textField;
    private static String inputPrompt = "";
    private static String enteredText = "";

    public DisplayPanel() {
        setBackground(new Color(149,185,219));

        w = new JButton("W");
        a = new JButton("A");
        s = new JButton("S");
        d = new JButton("D");

        w.addActionListener(this);
        a.addActionListener(this);
        s.addActionListener(this);
        d.addActionListener(this);

        add(w);
        add(a);
        add(s);
        add(d);

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        textField = new JTextField(10);
        textField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {
                        int key = e.getKeyCode();
                        if(key==10){
                            enteredText = textField.getText();
                            textField.setText("");
                            repaint();
                            requestFocusInWindow();
                        }
                    }
                }
        );
        add(textField);
    }

    public static String getInputPrompt() {
        return inputPrompt;
    }

    public static String promptString(String inputPrompt, DisplayPanel panel) {
        enteredText = "";
        DisplayPanel.inputPrompt = inputPrompt;
        panel.repaint();
        while(enteredText.isEmpty()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        DisplayPanel.inputPrompt = "";
        return enteredText;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        for (JButton jButton : Arrays.asList(w, a, s, d)) {
            jButton.setVisible(Ui.isBoardMade());
        }
        if(Ui.isBoardMade()) {
            String[] board = Board.boardToStrings();
            int newLine = g.getFont().getSize() + 5;
            g.setFont(new Font("Arial", Font.BOLD, 20));
            for (int i = 0; i < board.length; i++) {
                Util.drawCentered(g,board[i].substring(4),800,0,50+(newLine*i));
            }
            g.setFont(new Font("Arial", Font.BOLD, 16));
        }
        w.setLocation(Util.centeredX(w,800,0), 575);
        a.setLocation(Util.centeredX(w,800,0)-50, 600);
        s.setLocation(Util.centeredX(w,800,0), 625);
        d.setLocation(Util.centeredX(w,800,0)+50, 600);
        Util.drawCentered(g,inputPrompt,800,0,545);
        textField.setLocation(Util.centeredX(textField,800,0), 550);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton casted) {
            if (casted == w) {
                Ui.getPlayer().move("w",1);
            }else if(casted==a){
                Ui.getPlayer().move("a",1);
            }else if(casted==s){
                Ui.getPlayer().move("s",1);
            }else if(casted==d){
                Ui.getPlayer().move("d",1);
            }
            refocus();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
        int key = e.getKeyCode();
        if (key == 38||key == 87) {  // up key/W
            Ui.getPlayer().move("w",1);
        } else if (key == 40||key == 83) { // down key/S
            Ui.getPlayer().move("s",1);
        } else if (key == 37||key == 65) { // left key/A
            Ui.getPlayer().move("a",1);
        } else if (key == 39||key == 68) {  // right key/D
            Ui.getPlayer().move("d",1);
        }
    }

    public void update(){
        repaint();
    }

    private void refocus(){
        requestFocusInWindow();
    }
}