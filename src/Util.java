import javax.swing.*;
import java.awt.*;

public class Util {

    private Util(){};

    public static int[] toIdx(int[] coords){
        return new int[]{Board.getBoardSize()[0]-coords[1],coords[0]-1};
    }

    public static int[] toCoords(int[] idxs){
        return new int[]{idxs[1]+1,Board.getBoardSize()[0]-idxs[0]};
    }

    public static void drawCentered(Graphics g, String s, int width, int XPos, int YPos){
        int stringLen = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        int start = width/2 - stringLen/2;
        g.drawString(s, start + XPos, YPos);
    }

    public static int centeredX(JTextField a, int width, int xPos){
        int size = a.getWidth();
        return (width/2 - size/2)+xPos;
    }

    public static int centeredX(JButton a, int width, int xPos){
        int size = a.getWidth();
        return (width/2 - size/2)+xPos;
    }

}
