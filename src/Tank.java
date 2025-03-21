public class Tank extends Rock{

    int hp;
    Frame frame;

    public Tank(char symb, int xPos, int yPos, Frame frame) {
        super(symb,xPos,yPos);
        hp = 5;
        this.frame = frame;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}