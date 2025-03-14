import java.util.Scanner;

public class Ui {

    private static final Player player = new Player('✭',5,5);
    private static final Enemy enemy = new Enemy('✧',0,0);
    private static Scanner scan = new Scanner(System.in);

    public static void play(){
        System.out.print("How wide would you like the board to be? (min 8): ");
        int temp = scan.nextInt();
        while (temp<8){
            System.out.print("Please enter a value >= 8: ");
            temp = scan.nextInt();
        }
        System.out.print("How tall would you like the board to be? (min 8): ");
        int temp2 = scan.nextInt();
        while (temp2<8){
            System.out.print("Please enter a value >= 8: ");
            temp2 = scan.nextInt();
        }
        Board board = new Board(temp2,temp,player,enemy);
        board.createBoard();
        while(player.getHp()>0){
            board.printBoard();
            scan.nextLine();
            System.out.print("Enter a direction (WASD): ");
            String choice = scan.nextLine().toLowerCase();
            while(!(choice.equals("w")||choice.equals("a")||choice.equals("s")||choice.equals("d"))){
                System.out.print("Please enter a valid direction (WASD): ");
                choice = scan.nextLine().toLowerCase();
            }
            System.out.print("How much would you like to move? (up to "+ Board.getBoardSize()[0]/6+") ");
            int move = scan.nextInt();
            while(move<1||move>Board.getBoardSize()[0]/6){
                System.out.print("Please enter a valid number: ");
                move = scan.nextInt();
            }
            player.move(choice,move);
        }
    }

    public static void invalidMove(){
        scan.nextLine();
        System.out.print("Enter a direction (WASD): ");
        String choice = scan.nextLine().toLowerCase();
        while(!(choice.equals("w")||choice.equals("a")||choice.equals("s")||choice.equals("d"))){
            System.out.print("Please enter a valid direction (WASD): ");
            choice = scan.nextLine().toLowerCase();
        }
        System.out.print("How much would you like to move? (up to "+ Board.getBoardSize()[1]/6+") ");
        int move = scan.nextInt();
        while(move<1||move>Board.getBoardSize()[0]/6){
            System.out.print("Please enter a valid number: ");
            move = scan.nextInt();
        }
        player.move(choice,move);
    }
}
