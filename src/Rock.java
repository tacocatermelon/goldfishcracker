public class Rock extends Space{

    int xPos;
    int yPos;

    public Rock(char symb, int xPos, int yPos) {
        super(symb);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int[] getPos(){
        return new int[]{xPos,yPos};
    }

    public void setPos(int[] pos){
        xPos = (pos[0]);
        yPos = (pos[1]);
    }

}
