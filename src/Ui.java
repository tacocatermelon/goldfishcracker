import java.util.Scanner;

public class Ui {

    private static Player player;
    private static Enemy enemy;
    private static Scanner scan = new Scanner(System.in);
    private static boolean boardMade = false;

    public static boolean isBoardMade() {
        return boardMade;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void play(){
        Frame frame = new Frame();
        player = new Player('✭',5,5,frame);
        enemy = new Enemy('✧',0,0, frame);
        String tempstr = DisplayPanel.promptString("How wide would you like the board to be? (min 8)", frame.getPanel());
        int temp = Integer.parseInt(tempstr);
        while (temp<8){
            tempstr = DisplayPanel.promptString("Please enter a value >= 8", frame.getPanel());
            temp = Integer.parseInt(tempstr);
        }
        String tempstr2 = DisplayPanel.promptString("How tall would you like the board to be? (min 8)", frame.getPanel());
        int temp2 = Integer.parseInt(tempstr2);
        while (temp2<8){
            tempstr2 = DisplayPanel.promptString("Please enter a value >= 8", frame.getPanel());
            temp2 = Integer.parseInt(tempstr2);
        }
        Board board = new Board(temp2,temp,player,enemy);
        board.createBoard();
        boardMade = true;
        frame.getPanel().update();
        while (player.getHp()>0){
            while (player.getMoveCount()<=3){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            enemy.takeTurn();
            player.setMoveCount(0);
        }
    }

    /*public static void invalidMove(){
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
    }*/
}
