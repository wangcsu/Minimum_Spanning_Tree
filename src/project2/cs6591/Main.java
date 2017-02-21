package project2.cs6591;

import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLFault;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static List<Vertex> vertices = new ArrayList<>();
    public static List<Edge> edges = new ArrayList<>();
    public static List<Traffic> traffics = new ArrayList<>();
    public static int totalEdges = 0;
    public static List<Edge> MST = new ArrayList<>();

    public static void main(String[] args) {
        File file = new File("D:\\Documents\\CS6591\\Project2\\nodelocationsW17.txt");
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                int locationId = Integer.valueOf(scanner.next());
                int locationXcord = Integer.valueOf(scanner.next());
                int locationYcord = Integer.valueOf(scanner.next());
                Vertex v = new Vertex(locationId, locationXcord, locationYcord);
                vertices.add(v);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < vertices.size(); i++){
            for (int j = i + 1; j < vertices.size(); j++){
                Edge e = new Edge(vertices.get(i), vertices.get(j));
                edges.add(e);
            }
        }

        // Kruskal's algorithm begins as follow:
        // Step 1: Sort all the edges based on the weight

        Collections.sort(edges);

        /*for (Edge e : edges) {
            System.out.println(e.getSrc().getId() + " " + e.getDest().getId() + " " + Math.round(e.getDistance() * 1000d) / 1000d);
        }
        System.out.println("=========================================");*/

        // Step 2: Loop through all the edges
        // For each edge, if the two vertex are in different component,
        // merge the two component, until we have |v| -1 edges in total

        int i = 0;  // edge index

        do {
            Edge e = edges.get(i);
            int srcCompId = e.getSrc().getComponentId();
            int destCompId = e.getDest().getComponentId();
            if (srcCompId != destCompId) {
                e.setChosen(true);

                // Merge two differetn components

                for (Vertex v : vertices) {
                    if (v.getComponentId() == destCompId)
                        v.setComponentId(srcCompId);
                }
                /*e.getDest().setComponentId(e.getSrc().getComponentId());
                e.getSrc().setIsconnected(true);
                e.getDest().setIsconnected(true);*/
                totalEdges++;
            }
            i++;
        } while (totalEdges < (vertices.size() - 1));

        double totalWeight = 0.0;
        for (Edge e : edges) {
            if (e.isChosen()){
                MST.add(e);
                totalWeight += e.getDistance();
                System.out.println(e.getSrc().getId() + " " + e.getDest().getId() + " " + Math.round(e.getDistance() * 1000d) / 1000d);
            }
        }

        System.out.println("MST Weight = " + Math.round(totalWeight * 1000d) / 1000d);

        Graph g = new Graph(vertices, MST);
        /*AdjacencyList<Vertex> l = new AdjacencyList<> ();
        for (Vertex v : vertices) {
            l = (AdjacencyList<Vertex>) g.adj(v.getId() - 1);
            System.out.print(v.getId() + ": ");
            for (Vertex w : l) {
                System.out.print(w.getId() + "  ");
            }
            System.out.println();
        }*/

        file = new File("D:\\Documents\\CS6591\\Project2\\traffictableW17.txt");
        Vertex srcV, destV;
        srcV = new Vertex();
        destV = new Vertex();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                int srcId = Integer.valueOf(scanner.next());
                int destId = Integer.valueOf(scanner.next());
                int load = Integer.valueOf(scanner.next());
                for (Vertex v : vertices) {
                    if (v.getId() == srcId)
                        srcV = v;
                    if (v.getId() == destId)
                        destV = v;
                }
                Traffic t = new Traffic(srcV, destV, load);
                traffics.add(t);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*for (Traffic t : traffics)
            System.out.println(t.getSrc().getId() + "  " + t.getDest().getId() + "  " + t.getLoad());*/
        for (Traffic t : traffics) {
            Stack<Vertex> path = new Stack<>();
            path = findPath(g, t.getSrc(), t.getDest());
            /*System.out.print(t.getSrc().getId() + " to " + t.getDest().getId() + ": ");
            while (!path.empty()) {
                System.out.print(path.pop().getId() + "  ");
            }
            System.out.println();*/
            addLoad(MST, path, t);
        }

        for (Edge e : MST) {
            System.out.println(e.getSrc().getId() + " " + e.getDest().getId() + " " + e.getLoad());
        }
    }

    private static void addLoad(List<Edge> e, Stack<Vertex> v, Traffic traffic) {
        while (!v.empty()) {
            Vertex w = v.pop();
            if (v.isEmpty()) {
                break;
            }
            for (Edge x : e) {
                if ((x.getSrc().getId() == w.getId() && x.getDest().getId() == v.peek().getId())
                        ||(x.getDest().getId() == w.getId() && x.getSrc().getId() == v.peek().getId())) {
                    x.setLoad(x.getLoad() + traffic.getLoad());
                }
            }
        }
    }

    private static Stack<Vertex> findPath(Graph graph, Vertex src, Vertex dest) {
        Graph g = graph;
        DepthFirstPaths path = new DepthFirstPaths(g, src);
        // System.out.print(src.getId() + " to " + dest.getId() + ": ");
        Stack<Vertex> s = new Stack<>();
        s = (Stack<Vertex>) path.pathTo(dest);
        return s;
    }
}
