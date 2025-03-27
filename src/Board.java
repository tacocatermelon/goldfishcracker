import java.util.Arrays;

public class Board {

    private static Space[][] board;
    private static Player player;
    private static Enemy enemy;
    private static int[] playerPos;
    private static int[] enemyPos;
    private static Rock[] rocks;

    public Board(int xSize, int ySize, Player player, Enemy enemy){
        board = new Space[xSize][ySize];
        Board.player = player;
        Board.enemy = enemy;
    }

    public static int[] getBoardSize(){
        return new int[]{board.length,board[0].length};
    }

    public static int[] getPlayerPos() {
        return playerPos;
    }

    public static int[] getEnemyPos() {
        return enemyPos;
    }

    public static Rock[] getRocks() {
        return rocks;
    }

    public void createBoard(){
        int row = (int)(Math.random()*board.length);
        int col = (int)(Math.random()*board[0].length);
        playerPos = Util.toCoords(new int[]{row,col});
        player.setPos(playerPos);
        int[] playerIdxs = Util.toIdx(playerPos);
        board[playerIdxs[0]][playerIdxs[1]] = player; //adding player

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j]==null){
                    board[i][j] = new Space('_'); //filling all spaces
                }
            }
        }

        enemyPos = Util.toCoords(new int[]{row,col});
        while(Arrays.equals(enemyPos, playerPos)){ //block same start pos
            row = (int)(Math.random()*board.length);
            col = (int)(Math.random()*board[0].length);
            enemyPos = Util.toCoords(new int[]{row,col});
        }

        enemy.setPos(enemyPos);
        int[] enemyIdxs = Util.toIdx(enemyPos);
        board[enemyIdxs[0]][enemyIdxs[1]] = enemy; //adding enemy

        rocks = new Rock[5];
        for (int i = 0; i < rocks.length; i++) {
            int a = (int)(Math.random()*board.length);
            int b = (int)(Math.random()*board[0].length);
            int[] tempPos = Util.toCoords(new int[]{a,b});
            while(Arrays.equals(tempPos, playerPos)||Arrays.equals(tempPos, enemyPos)){ //block same start pos
                a = (int)(Math.random()*board.length);
                b = (int)(Math.random()*board[0].length);
                tempPos = Util.toCoords(new int[]{a,b});
            }
            rocks[i] = new Rock('▩',tempPos[0],tempPos[1]);
            board[Util.toIdx(tempPos)[0]][Util.toIdx(tempPos)[1]] = rocks[i];
        }
    }

    /*public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j].getSymb());
            }
            System.out.println();
        }
    }*/

    public static String[] boardToStrings(){
        String[] out = new String[board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                out[i] += board[i][j].getSymb()+" ";
            }
        }
        return out;
    }

    public static void newPos(int[] newPos){
        int[] playerIdxs = Util.toIdx(playerPos);
        board[playerIdxs[0]][playerIdxs[1]] = new Space('_');
        playerPos = newPos;
        playerIdxs = Util.toIdx(playerPos);
        board[playerIdxs[0]][playerIdxs[1]] = player;

        replaceAll();
    }

    public static void newEnemyPos(int[] newPos){
        int[] enemyIdxs = Util.toIdx(enemyPos);
        board[enemyIdxs[0]][enemyIdxs[1]] = new Space('_');
        enemyPos = newPos;
        enemyIdxs = Util.toIdx(enemyPos);
        board[enemyIdxs[0]][enemyIdxs[1]] = enemy;

        replaceAll();
    }

    public static boolean isEmpty(int xPos, int yPos){
        return board[xPos][yPos].getSymb()=='_';
    }

    private static void replaceAll(){
        if((!(board[Util.toIdx(playerPos)[0]][Util.toIdx(playerPos)[1]] instanceof Player))
                &&(!(board[Util.toIdx(playerPos)[0]][Util.toIdx(playerPos)[1]] instanceof Enemy))){
            board[Util.toIdx(playerPos)[0]][Util.toIdx(playerPos)[1]] = player;
        }
        if((!(board[Util.toIdx(enemyPos)[0]][Util.toIdx(enemyPos)[1]] instanceof Enemy))
                &&(!(board[Util.toIdx(enemyPos)[0]][Util.toIdx(enemyPos)[1]] instanceof Player))){
            board[Util.toIdx(enemyPos)[0]][Util.toIdx(enemyPos)[1]] = enemy;
        }
        for (int i = 0; i < rocks.length; i++) {
            if((!(board[Util.toIdx(rocks[i].getPos())[0]][Util.toIdx(rocks[i].getPos())[1]] instanceof Enemy))
                    &&(!(board[Util.toIdx(rocks[i].getPos())[0]][Util.toIdx(rocks[i].getPos())[1]] instanceof Player))
                    &&(!(board[Util.toIdx(rocks[i].getPos())[0]][Util.toIdx(rocks[i].getPos())[1]] instanceof Rock))){
                board[Util.toIdx(rocks[i].getPos())[0]][Util.toIdx(rocks[i].getPos())[1]] = rocks[i];
            }
        }
    }

}
