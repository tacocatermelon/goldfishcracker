public class Player extends Tank{

    private Frame frame;

    public Player(char symb, int xPos, int yPos,Frame frame) {
        super(symb, xPos, yPos);
        this.frame = frame;
    }

    public void move(String direction, int dist){
        boolean valid = true;
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
            Board.newPos(getPos());
            frame.getPanel().repaint();
        }else{
            setxPos(origPos[0]);
            setyPos(origPos[1]);
            Ui.invalidMove();
        }
    }
}