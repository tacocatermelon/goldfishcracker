public class Util {

    private Util(){};

    public static int[] toIdx(int[] coords){
        return new int[]{Board.getBoardSize()[0]-coords[0],coords[1]-1};
    }

    public static int[] toCoords(int[] idxs){
        return new int[]{Board.getBoardSize()[0]-idxs[0],idxs[1]+1};
    }

    public static int xToIdx(int x){
        return Board.getBoardSize()[0]-x;
    }

    public static int yToIdx(int y){
        return y-1;
    }

    public static int xToCoords(int x){
        return Board.getBoardSize()[0]-x;
    }

    public static int yToCoords(int y){
        return y+1;
    }
}
