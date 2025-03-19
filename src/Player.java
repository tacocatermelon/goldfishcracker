public class Player extends Tank{

    private int moveCount;

    public Player(char symb, int xPos, int yPos,Frame frame) {
        super(symb, xPos, yPos, frame);
        moveCount = 0;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

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
        if(!(posIdxs[1]>=Board.getBoardSize()[1]||posIdxs[0]>=Board.getBoardSize()[0]||posIdxs[0]<0||posIdxs[1]<0)){
            moveCount++;
            Board.newPos(getPos());
            frame.getPanel().repaint();
        }else{
            setxPos(origPos[0]);
            setyPos(origPos[1]);
        }
    }
}