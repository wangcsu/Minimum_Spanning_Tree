package project2.cs6591;

import java.util.List;

/**
 * Created by Ge Wang on 2/20/17.
 */
public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;
    private AdjacencyList<Vertex>[] adj;

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        adj = new AdjacencyList[vertices.size()];
        for (int v = 0; v < vertices.size(); v++)
            adj[v] = new AdjacencyList<Vertex>();
        addEdges(edges);
    }

    private void addEdges(List<Edge> edges) {
        for (Edge e : edges) {
            adj[e.getSrc().getId() - 1].add(e.getDest());
            adj[e.getDest().getId() - 1].add(e.getSrc());
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Iterable<Vertex> adj(int v) {
        return adj[v];
    }
}
