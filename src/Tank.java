public class Tank extends Space{

    int xPos;
    int yPos;
    int hp;
    double score; //score based on distance of shot

    public Tank(char symb, int xPos, int yPos) {
        super(symb);
        this.xPos = xPos;
        this.yPos = yPos;
        hp = 5;
    }
}
