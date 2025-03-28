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
    private final JButton w;
    private final JButton a;
    private final JButton s;
    private final JButton d;
    private final JButton end;
    private final JTextField textField;
    private static String inputPrompt = "";
    private static String enteredText = "";
    private static String outputText = "";
    private static String[] gameEndText = new String[2];

    public DisplayPanel() {
        setBackground(new Color(149,185,219));

        w = new JButton("W");
        a = new JButton("A");
        s = new JButton("S");
        d = new JButton("D");
        end = new JButton("Play Again?");

        w.addActionListener(this);
        a.addActionListener(this);
        s.addActionListener(this);
        d.addActionListener(this);
        end.addActionListener(this);

        add(w);
        add(a);
        add(s);
        add(d);
        add(end);

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
                    public void keyReleased(KeyEvent e) { //keys while typing in textbox
                        int key = e.getKeyCode();
                        if(key==10){ //enter
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

    public static void setGameEndText(String[] gameEndText) {
        DisplayPanel.gameEndText = gameEndText;
    }

    public static void setOutputText(String outputText) {
        DisplayPanel.outputText = outputText;
    }

    public void visibleTextBox(boolean a){
        textField.setVisible(a);
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
        if(!Ui.isGameOver()) {
            super.paintComponent(g);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.RED);
            for (JButton jButton : Arrays.asList(w, a, s, d)) {
                jButton.setVisible(Ui.isBoardMade()); //buttons hidden until board made
            }

            if (Ui.isBoardMade()) {
                String[] board = Board.boardToStrings();
                int newLine = g.getFont().getSize() + 5;
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.setColor(new Color(88, 40, 103));
                for (int i = 0; i < board.length; i++) { //board drawing
                    Util.drawCentered(g, board[i].substring(4), 800, 0, 50 + (newLine * i));
                }

                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.setColor(Color.BLUE);
            }

            //buttons
            w.setLocation(Util.centeredX(w, 800, 0), 575);
            a.setLocation(Util.centeredX(w, 800, 0) - 50, 600);
            s.setLocation(Util.centeredX(w, 800, 0), 625);
            d.setLocation(Util.centeredX(w, 800, 0) + 50, 600);

            if (Ui.isBoardMade()) {
                Util.drawCentered(g, inputPrompt, 800, 0, 545); //prompt at top
            } else {
                Util.drawCentered(g, inputPrompt, 800, 0, 625); //prompt at bottom
            }

            if (!inputPrompt.isEmpty()) {
                outputText = "";
            }

            if (Ui.isBoardMade()) {
                g.setColor(new Color(9, 161, 15));
                Util.drawCentered(g, outputText, 800, 0, 525);

                g.setColor(new Color(129, 31, 31));
                g.setFont(new Font("Arial", Font.BOLD, 22));
                Util.drawCentered(g, "Enemy Health: " + Ui.getEnemy().getHp(), 300, 0, 600);
                g.setColor(new Color(37, 66, 129));
                Util.drawCentered(g, "Player Health: " + Ui.getPlayer().getHp(), 300, 0, 625);
                g.setColor(new Color(8, 161, 14));
                Util.drawCentered(g, "Score: " + Ui.getPlayer().getScore(), 300, 500, 615);
                g.setFont(new Font("Arial", Font.BOLD, 16));
            }

            if (Ui.isBoardMade()) {
                textField.setLocation(Util.centeredX(textField, 800, 0), 550); //textbox at top
            } else {
                textField.setLocation(Util.centeredX(textField, 800, 0), 630); //textbox at bottom
            }
            end.setVisible(false);
        }else{
            end.setSize(150, 50);
            end.setFont(new Font("Arial", Font.BOLD, 22));
            end.setLocation(Util.centeredX(end, 800, 0), Util.centeredY(end, 700, 0));
            end.setVisible(true);
            for (JButton jButton : Arrays.asList(w, a, s, d)) {
                jButton.setVisible(false);
            }
            g.setFont(new Font("Arial", Font.BOLD, 25));
            if(gameEndText[0].equals("You win!!! Your score was:")){
                g.setColor(new Color(51, 177, 110));
            }else{
                g.setColor(new Color(218, 59, 59));
            }
            Util.drawCentered(g,gameEndText[0],800,0,400);
            Util.drawCentered(g,gameEndText[1],800,0,435);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton casted) {
            if(Ui.isNotShooting()){
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
            if(casted == end){
                Ui.setGameOver(false);
            }
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
        if(Ui.isNotShooting()) {
            if (key == 38 || key == 87) {  // up key/W
                Ui.getPlayer().move("w", 1);
            } else if (key == 40 || key == 83) { // down key/S
                Ui.getPlayer().move("s", 1);
            } else if (key == 37 || key == 65) { // left key/A
                Ui.getPlayer().move("a", 1);
            } else if (key == 39 || key == 68) {  // right key/D
                Ui.getPlayer().move("d", 1);
            }
        }
    }

    public void update(){
        repaint();
    }

    private void refocus(){
        requestFocusInWindow();
    }
}