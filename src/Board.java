public class Board {

    static String[][] board;

    public Board(int xSize, int ySize){
        board = new String[xSize][ySize];
    }

    public static int[] getBoardSize(){
        return new int[]{board.length,board[0].length};
    }

}
