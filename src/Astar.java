import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Astar {
    private List<Node> openList;
    private List<Node> closedList;
    private List<Node> path;
    private int xGoal, yGoal;
    private Node current;
    private int[][] grid;
    private int xStart, yStart;

    Astar(int[][] oGrid, int xStart, int yStart){
        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        this.path = new ArrayList<>();
        this.grid = oGrid;
        this.current = new Node(null, xStart, yStart, 0, 0);
        this.xStart = xStart;
        this.yStart = yStart;
    }

    public List<Node> findPath(int xGoal, int yGoal){
        this.xGoal = xGoal;
        this.yGoal = yGoal;
        this.closedList.add(this.current);
        addNeigborsToOpenList();
        while (this.current.x != this.xGoal || this.current.y != this.yGoal) {
            if (this.openList.isEmpty()) { // Nothing to examine
                return null;
            }
            this.current = this.openList.get(0); // get first node (lowest f score)
            this.openList.remove(0); // remove it
            this.closedList.add(this.current); // and add to the closed
            addNeigborsToOpenList();
        }
        this.path.add(0, this.current);
        while (this.current.x != this.xStart || this.current.y != this.yStart) {
            this.current = this.current.parent;
            this.path.add(0, this.current);
        }
        return this.path;
    }

    private void addNeigborsToOpenList() {
        Node node;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                node = new Node(this.current, this.current.x + x, this.current.y + y, this.current.g, this.distance(x, y));
                if ((x != 0 || y != 0) // not this.now
                        && this.current.x + x >= 0 && this.current.x + x < this.grid[0].length // check maze boundaries
                        && this.current.y + y >= 0 && this.current.y + y < this.grid.length
                        && this.grid[this.current.y + y][this.current.x + x] != -1 // check if square is walkable
                        && !findNeighborInList(this.openList, node) && !findNeighborInList(this.closedList, node)) { // if not already done
                    node.g = node.parent.g + 1.; // Horizontal/vertical cost = 1.0
                    node.g += grid[this.current.y + y][this.current.x + x]; // add movement cost for this square

                    // diagonal cost = sqrt(hor_cost² + vert_cost²)
                    // in this example the cost would be 12.2 instead of 11
                        /*
                        if (diag && x != 0 && y != 0) {
                            node.g += .4;	// Diagonal movement cost = 1.4
                        }
                        */
                    this.openList.add(node);
                }
            }
        }
        Collections.sort(this.openList);
    }

    private static boolean findNeighborInList(List<Node> array, Node node) {
        return array.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
    }

    private double distance(int dx, int dy) {

            return Math.abs(this.current.x + dx - this.xGoal) + Math.abs(this.current.y + dy - this.yGoal); // else return "Manhattan distance
    }
}
