public class Node implements Comparable<Node>{
    int x;
    int y;
    double cost;
    double g;
    double h;
    Node parent;

    public Node(Node o, int xStart, int yStart, double g, double h) {
        this.parent = o;
        this.x = xStart;
        this.y = yStart;
        this.g = g;
        this.h = h;
    }

    public double getCost(){
        return cost = g + h;
    }

    @Override
    public int compareTo(Node o) {
        return(int)((this.g + this.h) - (((Node) o).g - ((Node) o).h));
    }
}
