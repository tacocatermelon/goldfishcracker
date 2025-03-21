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

        int[] posIdxs = Util.toIdx(getPos()); //transfer to idx for board

        if(!(posIdxs[1]>=Board.getBoardSize()[1]
                ||posIdxs[0]>=Board.getBoardSize()[0]
                ||posIdxs[0]<0||posIdxs[1]<0)
                &&Board.isEmpty(posIdxs[0],posIdxs[1])){ //in bounds check
            moveCount++;
            Board.newPos(getPos());
            frame.getPanel().repaint();
        }else{
            setxPos(origPos[0]); //reset pos if invalid move
            setyPos(origPos[1]);
        }
    }

    public boolean fire(double power, double angle){
        scored = 0; //scored reset
        angle = Util.toRadians(angle); //angle converted to radians

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x1 = power*cos+getxPos();
        double y1 = power*sin+getyPos();

        int[][] shots = new int[(int)power+1][2];
        double[] xs = new double[(int)power+1]; //storage of all shot x coordinates for score
        double[] ys = new double[(int)power+1]; //storage of all shot y coordinates for score
        shots[shots.length-1] = new int[]{(int)Math.round(x1),(int)Math.round(y1)}; //exact shot
        xs[shots.length-1] = x1; //exact x
        ys[shots.length-1] = y1; //exact y

        for (int i = 0; i < shots.length-1; i++) { //adding every shot along line for each int value up to power
            x1 = i*cos+getxPos();
            y1 = i*sin+getyPos();
            shots[i] = new int[]{(int)Math.round(x1),(int)Math.round(y1)};
            xs[i] = x1;
            ys[i] = y1;
        }

        for (int i = 0; i < shots.length; i++) {
            if(Arrays.equals(shots[i], Board.getEnemyPos())){ //check all shot positions
                scored = 100*(Math.sqrt(Math.pow(xs[i]-getxPos(),2)+Math.pow(ys[i]-getyPos(),2))); // 100x distance from tank to point
                score += scored; //added to total score
                return true;
            }
        }
        return false;
    }
}