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

// this class is a subclass of JPanel
public class DisplayPanel extends JPanel implements ActionListener, KeyListener{
    private JButton w;
    private JButton a;
    private JButton s;
    private JButton d;
    private JTextField textField;

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
        add(textField);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        if(Ui.isBoardMade()) {
            String[] board = Board.boardToStrings();
            int newLine = g.getFont().getSize() + 5;
            for (int i = 0; i < board.length; i++) {
                g.drawString(board[i].substring(4), 100, 50+(newLine*i));
            }
        }
        w.setLocation(350, 575);
        a.setLocation(300, 600);
        s.setLocation(350, 625);
        d.setLocation(400, 600);
        textField.setLocation(325, 550);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            if (casted == w) {

            }else if(casted==a){

            }else if(casted==s){

            }else if(casted==d){

            }
        }
    }

    // these methods are "required" by the KeyListener interface
    @Override
    public void keyTyped(KeyEvent e) {}

    // this method is called by the system automatically whenever the user
    // presses ANY key on the keyboard
    @Override
    public void keyPressed(KeyEvent e) {
        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
        int key = e.getKeyCode();
        if (key == 38||key == 87) {  // up key/W

        } else if (key == 40||key == 83) { // down key/S

        } else if (key == 37||key == 65) { // left key

        } else if (key == 39||key == 68) {  // right key

        }else if(key==10){
            String enteredText = textField.getText();
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    public void update(){
        repaint();
    }
}