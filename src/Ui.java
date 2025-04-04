public class Ui {

    private static final Frame frame = new Frame();
    private static final Player player = new Player('✭',5,5,frame);
    private static final Enemy enemy = new Enemy('✧',0,0, frame);
    private static boolean boardMade = false;
    private static boolean shooting = false;
    private static boolean gameOver = false;
    private static boolean hardMode = false;
    private static Board board;

    public static boolean isBoardMade() {
        return boardMade;
    }

    public static boolean isNotShooting() {
        return !shooting;
    }

    public static boolean isHardMode() {
        return hardMode;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Enemy getEnemy() {
        return enemy;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        Ui.gameOver = gameOver;
    }

    public static void play(){
        shooting = false;
        gameOver = false;
        boardMade = false;
        player.setHp(5);
        enemy.setHp(5);
        player.setMoveCount(0);
        frame.getPanel().visibleTextBox(true); //hide text box after board made

        String tempstr = DisplayPanel.promptString("How wide would you like the board to be? (min 10, max 40)", frame.getPanel());
        int temp = Integer.parseInt(tempstr);
        while (temp < 10 || temp > 40) {
            tempstr = DisplayPanel.promptString("Please enter a value between 10 and 40 (inclusive)", frame.getPanel());
            temp = Integer.parseInt(tempstr);
        }
        String tempstr2 = DisplayPanel.promptString("How tall would you like the board to be? (min 10, max 22)", frame.getPanel());
        int temp2 = Integer.parseInt(tempstr2);
        while (temp2 < 10 || temp2 > 22) {
            tempstr2 = DisplayPanel.promptString("Please enter a value between 10 and 22 (inclusive)", frame.getPanel());
            temp2 = Integer.parseInt(tempstr2);
        }

        String hardPrompt = DisplayPanel.promptString("Hard mode? (y/n)", frame.getPanel());
        if(hardPrompt.toLowerCase().equals("y")){
            hardMode = true;
        }

        board = new Board(temp2, temp, player, enemy);
        boardMade = true;
        board.createBoard();
        frame.getPanel().update(); //show board once made
        frame.getPanel().visibleTextBox(false); //hide text box after board made

        while (player.getHp()>0&&enemy.getHp()>0){
            int moves = player.getMoveCount();
            while (player.getMoveCount()<=3){ //wait for player to move 3 times
                try {
                    if(player.getMoveCount()>moves){
                        moves = player.getMoveCount();
                        if(hardMode){
                            enemy.movementTurn();
                        }
                    }
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
            boolean hit;
            if(hardMode){
                hit = player.arcShot(power,angle);
            }else{
                hit = player.fire(power, angle);//hit
            }

            if(hit){
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
        if(player.getHp()<1){
            DisplayPanel.setGameEndText(new String[]{"You lose!!! Your score was:", String.valueOf(player.getScore())});
        }else{
            DisplayPanel.setGameEndText(new String[]{"You win!!! Your score was:", String.valueOf(player.getScore())});
        }
        shooting = true;
        gameOver = true;
        boardMade = false;
        frame.getPanel().update();
        while (gameOver){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        play();
    }
}
