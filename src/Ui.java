import java.util.Scanner;

public class Ui {

    public static void play(){
        Scanner scan = new Scanner(System.in);
        System.out.print("How wide would you like the board to be? ");
        int temp = scan.nextInt();
        System.out.print("How tall would you like the board to be? ");
        Board board = new Board(scan.nextInt(),temp);
        board.createBoard();
        board.printBoard();
    }
}
