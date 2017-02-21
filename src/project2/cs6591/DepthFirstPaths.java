package project2.cs6591;

import java.util.Stack;

/**
 * Created by Ge Wang on 2/20/2017.
 */
public class DepthFirstPaths {
    private boolean[] marked;
    private Vertex[] edgeTo;
    private final Vertex s;

    public DepthFirstPaths(Graph g, Vertex s) {
        marked = new boolean[g.getVertices().size()];
        edgeTo = new Vertex[g.getVertices().size()];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(Graph g, Vertex v) {
        marked[v.getId() - 1] = true;
        for (Vertex w : g.adj(v.getId() - 1)) {
            if (!marked[w.getId() - 1]) {
                edgeTo[w.getId() - 1] = v;
                dfs(g, w);
            }
        }
    }

    public Iterable<Vertex> pathTo (Vertex v) {
        Stack<Vertex> path = new Stack<> ();
        for (Vertex x = v; x != s; x = edgeTo[x.getId() - 1])
            path.push(x);
        path.push(s);
        return path;
    }
}
