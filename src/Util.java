import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Util {

    private Util(){}

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

    public static int centeredY(JButton a, int height, int yPos){
        int size = a.getHeight();
        return (height/2 - size/2)+yPos;
    }

    public static double toRadians(double degrees){
        return (Math.PI/180)*degrees;
    }

    public static boolean hasRock(int[][] shots){
        for (int i = 0; i < shots.length; i++) {
            for (int j = 0; j < Board.getRocks().length; j++) {
                if(Arrays.equals(shots[i],Board.getRocks()[j].getPos())){
                    return true;
                }
            }
        }
        return false;
    }
}
