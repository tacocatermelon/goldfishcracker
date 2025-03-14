public class Board {

    static Space[][] board;

    public Board(int xSize, int ySize){
        board = new Space[xSize][ySize];
    }

    public static int[] getBoardSize(){
        return new int[]{board.length,board[0].length};
    }

    public void createBoard(){
        int row = (int)(Math.random()*board.length);
        int col = (int)(Math.random()*board[0].length);
        board[row][col] = new Player('✭',Util.xToCoords(row),Util.yToCoords(col));
        while(board[row][col]!=null&&board[row][col].getClass()==Player.class){
            row = (int)(Math.random()*board.length);
            col = (int)(Math.random()*board[0].length);
        }
        board[row][col] = new Enemy('✧',Util.xToCoords(row),Util.yToCoords(col));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j]==null){
                    board[i][j] = new Space('_');
                }
            }
        }
    }

    public void printBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j].getSymb());
            }
            System.out.println();
        }
    }
}
