public class Enemy extends Tank{
    public Enemy(char symb, int xPos, int yPos, Frame frame) {
        super(symb, xPos, yPos, frame);
    }

    public void takeTurn(){
        double rand;
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
        while(posIdxs[1]>=Board.getBoardSize()[1]||posIdxs[0]>=Board.getBoardSize()[0]||posIdxs[0]<0||posIdxs[1]<0){
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
        Board.newEnemyPos(getPos());
        frame.getPanel().repaint();
    }
}