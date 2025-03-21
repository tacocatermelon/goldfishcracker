import java.util.Arrays;

public class Enemy extends Tank{
    public Enemy(char symb, int xPos, int yPos, Frame frame) {
        super(symb, xPos, yPos, frame);
    }

    public void movementTurn(){
        double rand;
        int[] origPos= new int[]{getxPos(),getyPos()};
        for (int i = 0; i < 2; i++) {
            rand = Math.random();
            if(rand <= 0.24){
                setyPos(getyPos()+1);
            }else if(rand <= 0.49){
                setyPos(getyPos()-1);
            }else if(rand <= 0.74){
                setxPos(getxPos()+1);
            }else{
                setxPos(getxPos()-1);
            }
        }
        int[] posIdxs = Util.toIdx(getPos());
        if(posIdxs[1]>=Board.getBoardSize()[1]||posIdxs[0]>=Board.getBoardSize()[0]||posIdxs[0]<0||posIdxs[1]<0||Arrays.equals(getPos(),Ui.getPlayer().getPos())){
            setxPos(origPos[0]);
            setyPos(origPos[1]);
        }
        Board.newEnemyPos(getPos());
        frame.getPanel().repaint();
    }

    public boolean combatTurn(){
        if(Ui.getPlayer().getxPos()==getxPos()||Ui.getPlayer().getyPos()==getyPos()){
            return true;
        }

        if(Math.sqrt(Math.pow(Ui.getPlayer().getxPos()-getxPos(),2) +Math.pow(Ui.getPlayer().getyPos()-getyPos(),2))<=3){
            return true;
        }

        int angle;
        if(Ui.getPlayer().getxPos()>=getxPos()&&Ui.getPlayer().getyPos()>=getyPos()) {
            angle = (int)(Math.random()*90);
        }else if(Ui.getPlayer().getxPos()>=getxPos()&&Ui.getPlayer().getyPos()<getyPos()) {
            angle = (int)(Math.random()*90)+90;
        }else if(Ui.getPlayer().getxPos()<getxPos()&&Ui.getPlayer().getyPos()>=getyPos()) {
            angle = (int)(Math.random()*90)+180;
        }else {
            angle = (int)(Math.random()*90)+270;
        }

        int power = (int)(Math.random()*(Board.getBoardSize()[0]/2.0)-1)+2;

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x1 = power*cos+getxPos();
        double y1 = power*sin+getyPos();

        int[][] shots = new int[power+1][2];
        shots[shots.length-1] = new int[]{(int)Math.round(x1),(int)Math.round(y1)};

        for (int i = 0; i < shots.length-1; i++) {
            x1 = i*cos+getxPos();
            y1 = i*sin+getyPos();
            shots[i] = new int[]{(int)Math.round(x1),(int)Math.round(y1)};
        }

        for (int i = 0; i < shots.length; i++) {
            if(Arrays.equals(shots[i], Board.getPlayerPos())){
                return true;
            }
        }
        return false;
    }
}