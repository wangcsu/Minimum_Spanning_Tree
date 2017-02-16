package project2.cs6591;

/**
 * Created by Frank on 2/15/17.
 */
public class Edge {
    private Vertex src, dest;
    private int distance;

    Edge (Vertex v1, Vertex v2) {
        src = v1;
        dest = v2;
        distance = (int) Math.sqrt(Math.pow(v1.getXcord() - v2.getXcord(), 2) +
                Math.pow(v1.getYcord() - v2.getYcord(), 2));
    }
}
