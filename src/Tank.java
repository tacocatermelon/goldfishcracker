public class Tank extends Space{

    int xPos;
    int yPos;
    int hp;
    Frame frame;

    public Tank(char symb, int xPos, int yPos, Frame frame) {
        super(symb);
        this.xPos = xPos;
        this.yPos = yPos;
        hp = 5;
        this.frame = frame;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getHp() {
        return hp;
    }

    public int[] getPos(){
        return new int[]{xPos,yPos};
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setPos(int[] pos){
        xPos = pos[0];
        yPos = pos[1];
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}