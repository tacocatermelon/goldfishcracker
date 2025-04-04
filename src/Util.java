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

    public static double toDegrees(double radians){
        return (180/Math.PI)*radians;
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

    public static double[] calculateArc(double power, double angle, int yPos, double t){
        angle = toRadians(angle);

        double vx = power*Math.cos(angle)/2;
        double vi = power*Math.sin(angle);

        double xdist = vx*t;
        double ydist = yPos + (vi*t) - (0.5*9.81*Math.pow(t,2));

        return new double[]{xdist,ydist};
    }

    public static double getTime(double power, double angle){
        angle = toRadians(angle);
        double vi = power*Math.sin(angle);

        return (vi*Math.sin(angle))/9.81;
    }

    public static boolean hitsPoint(double checkX, double checkY, double power, double angle, int xPos, int yPos){
        angle = toRadians(angle);
        checkX -= xPos;

        double t = getTime(power,angle);
        double xdist = xPos+calculateArc(power, angle, yPos, t)[0];
        double ydist = yPos+calculateArc(power, angle, yPos, t)[1];

        if((Math.abs(checkY - ydist) <= 1)&&(Math.abs(checkX - xdist) <= 1)){
            return true;
        }

        if(Util.toDegrees(angle)==90&&xPos==checkX){
            return (yPos+(Math.pow(power,2)*Math.pow(Math.sin(angle),2))/(2*9.81))>=checkY;
        }

        if(Util.toDegrees(angle)==270&&xPos==checkX){
            return true;
        }

        double y = ((Math.tan(angle))*checkX)-((9.81*Math.pow(checkX,2))/(2*Math.pow(power*Math.cos(angle),2)))+yPos;
        return (Math.abs(checkY - y) <= 1);
    }
}
