public class Util {

    private Util(){};

    public static int[] toIdx(int[] coords){
        return new int[]{Board.getBoardSize()[0]-coords[1],coords[0]-1};
    }

    public static int[] toCoords(int[] idxs){
        return new int[]{idxs[1]+1,Board.getBoardSize()[0]-idxs[0]};
    }
}
