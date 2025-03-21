import java.util.Arrays;

public class Player extends Tank{

    private int moveCount;
    private double score; //score based on distance of shot
    private double scored = 0;

    public Player(char symb, int xPos, int yPos,Frame frame) {
        super(symb, xPos, yPos, frame);
        moveCount = 0;
        score = 0;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public double getScore() {
        return score;
    }

    public double getScored() {
        return scored;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    /*public void setScore(double score) {
        this.score = score;
    }*/

    public void move(String direction, int dist){
        int[] origPos = new int[]{getxPos(),getyPos()};
        if(direction.equals("w")){
            setyPos(getyPos()+dist);
        }else if(direction.equals("s")){
            setyPos(getyPos()-dist);
        }else if(direction.equals("d")){
            setxPos(getxPos()+dist);
        }else if(direction.equals("a")){
            setxPos(getxPos()-dist);
        }

        int[] posIdxs = Util.toIdx(getPos());

        if(!(posIdxs[1]>=Board.getBoardSize()[1]||posIdxs[0]>=Board.getBoardSize()[0]||posIdxs[0]<0||posIdxs[1]<0||Arrays.equals(getPos(),Ui.getEnemy().getPos()))){ //in bounds check
            moveCount++;
            Board.newPos(getPos());
            frame.getPanel().repaint();
        }else{
            setxPos(origPos[0]);
            setyPos(origPos[1]);
        }
    }

    public boolean fire(double power, double angle){
        scored = 0;
        angle = Util.toRadians(angle);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x1 = power*cos+getxPos();
        double y1 = power*sin+getyPos();
        int[][] shots = new int[(int)power+1][2];
        double[] xs = new double[(int)power+1];
        double[] ys = new double[(int)power+1];
        shots[shots.length-1] = new int[]{(int)Math.round(x1),(int)Math.round(y1)};
        xs[shots.length-1] = x1;
        ys[shots.length-1] = y1;

        for (int i = 0; i < shots.length-1; i++) {
            x1 = i*cos+getxPos();
            y1 = i*sin+getyPos();
            shots[i] = new int[]{(int)Math.round(x1),(int)Math.round(y1)};
            xs[i] = x1;
            ys[i] = y1;
        }

        for (int i = 0; i < shots.length; i++) {
            if(Arrays.equals(shots[i], Board.getEnemyPos())){
                scored = 100*(Math.sqrt(Math.pow(xs[i]-getxPos(),2)+Math.pow(ys[i]-getyPos(),2))); //distance from tank to point
                score += scored;
                return true;
            }
        }
        return false;
    }
}