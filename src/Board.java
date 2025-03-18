import java.util.Arrays;

public class Board {

    private static Space[][] board;
    private static Player player;
    private static Enemy enemy;
    static int[] playerPos;
    static int[] enemyPos;

    public Board(int xSize, int ySize, Player player, Enemy enemy){
        board = new Space[xSize][ySize];
        Board.player = player;
        Board.enemy = enemy;
    }

    public static int[] getBoardSize(){
        return new int[]{board.length,board[0].length};
    }

    public void createBoard(){
        int row = (int)(Math.random()*board.length);
        int col = (int)(Math.random()*board[0].length);
        playerPos = Util.toCoords(new int[]{row,col});
        player.setPos(playerPos);
        int[] playerIdxs = Util.toIdx(playerPos);
        board[playerIdxs[0]][playerIdxs[1]] = player;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j]==null){
                    board[i][j] = new Space('_');
                }
            }
        }
        enemyPos = Util.toCoords(new int[]{row,col});
        while(Arrays.equals(enemyPos, playerPos)){
            row = (int)(Math.random()*board.length);
            col = (int)(Math.random()*board[0].length);
            enemyPos = Util.toCoords(new int[]{row,col});
        }
        enemy.setPos(enemyPos);
        int[] enemyIdxs = Util.toIdx(enemyPos);
        board[enemyIdxs[0]][enemyIdxs[1]] = enemy;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j].getSymb());
            }
            System.out.println();
        }
    }

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

    }
}
