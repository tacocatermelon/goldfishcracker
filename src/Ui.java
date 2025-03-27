public class Ui {

    private static Player player;
    private static Enemy enemy;
    private static boolean boardMade = false;
    private static boolean shooting = false;

    public static boolean isBoardMade() {
        return boardMade;
    }

    public static boolean isNotShooting() {
        return !shooting;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Enemy getEnemy() {
        return enemy;
    }

    public static void play(){
        Frame frame = new Frame();
        player = new Player('✭',5,5,frame);
        enemy = new Enemy('✧',0,0, frame);

        String tempstr = DisplayPanel.promptString("How wide would you like the board to be? (min 10, max 40)", frame.getPanel());
        int temp = Integer.parseInt(tempstr);
        while (temp<10||temp>40){
            tempstr = DisplayPanel.promptString("Please enter a value between 10 and 40 (inclusive)", frame.getPanel());
            temp = Integer.parseInt(tempstr);
        }
        String tempstr2 = DisplayPanel.promptString("How tall would you like the board to be? (min 10, max 22)", frame.getPanel());
        int temp2 = Integer.parseInt(tempstr2);
        while (temp2<10||temp2>22){
            tempstr2 = DisplayPanel.promptString("Please enter a value between 10 and 22 (inclusive)", frame.getPanel());
            temp2 = Integer.parseInt(tempstr2);
        }

        Board board = new Board(temp2, temp, player, enemy);
        board.createBoard();
        boardMade = true;
        frame.getPanel().update(); //show board once made
        frame.getPanel().visibleTextBox(false); //hide text box after board made

        while (player.getHp()>0&&enemy.getHp()>0){
            while (player.getMoveCount()<=3){ //wait for player to move 3 times
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            shooting = true; //stop movement
            frame.getPanel().visibleTextBox(true); //show text box for fire inputs
            String tempstr3 = DisplayPanel.promptString("What shot power would you like?", frame.getPanel());
            double power = Double.parseDouble(tempstr3);
            String tempstr4 = DisplayPanel.promptString("What shot angle would you like?", frame.getPanel());
            double angle = Double.parseDouble(tempstr4);

            if(player.fire(power, angle)){ //hit
                enemy.setHp(enemy.getHp()-1);
                enemy.movementTurn();
                double tempscore = player.getScored()*Math.pow(10,2); //rounding to 2 decimal places
                tempscore = Math.round(tempscore);
                DisplayPanel.setOutputText("Hit!!   +"+tempscore*Math.pow(10,-2));
            }else{ //miss
                DisplayPanel.setOutputText("Miss!!");
            }
            frame.getPanel().update(); //update new positions

            shooting = false; //re-enable movement and hide text box for 1 move
            frame.getPanel().visibleTextBox(false);
            while (player.getMoveCount()<=4){ //wait for next move
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            shooting = true; //disable movement, let enemy move
            enemy.movementTurn();
            player.setMoveCount(0); //reset move count

            if(enemy.combatTurn()){ //enemy hit
                player.setHp(player.getHp()-1);
                DisplayPanel.setOutputText("Player Hit!!   "+player.getHp()+" Health Left!!");
            }else{ //enemy miss
                DisplayPanel.setOutputText("Enemy Missed!!");
            }
            frame.getPanel().update();
            shooting = false;
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
