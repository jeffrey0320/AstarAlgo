import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AstarAlgo {
    public static void main(String[] args) throws FileNotFoundException {

        final int SIZE = 32;
        int[][] oGrid = new int[SIZE][SIZE];
        algo alg = new algo(oGrid, 3, 4);

        readMap(oGrid);
        writeMap(oGrid);

        List<Node> path = alg.findPath(27, 27);
        if (path != null) {
            path.forEach((n) -> {
                System.out.print("[" + n.x + ", " + n.y + "] ");
                oGrid[n.y][n.x] = 2;
            });

            for(int i = oGrid.length - 1; i >= 0; i--){
                for(int j = 0; j < oGrid.length; j++){
                    System.out.print(oGrid[i][j] + " ");
                }
                System.out.println();
            }

        }
    }

    public static void readMap(int[][] oGrid) throws FileNotFoundException {
        File mapFile = new File("map.txt");
        Scanner scan = new Scanner(mapFile);

        while(scan.hasNext()){
            for(int i = oGrid.length - 1; i >= 0; i--){
                for(int j = 0; j < oGrid.length; j++){
                    oGrid[i][j] = Integer.parseInt(scan.next());
                }
            }
        }
    }

    public static void writeMap(int[][] oGrid){
        for(int i = oGrid.length - 1; i >= 0; i--){
            for(int j = 0; j < oGrid.length; j++){
                System.out.print(oGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

}
