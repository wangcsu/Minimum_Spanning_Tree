package project2.cs6591;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static List<Vertex> vertices = new ArrayList<>();
    public static List<Edge> edges = new ArrayList<>();
    public static List<Traffic> traffics = new ArrayList<>();
    public static int totalEdges = 0;
    public static List<Edge> MST = new ArrayList<>();
    public static final double LINE_CAPACITY = 1544.0;
    public static final double Tbar = 0.0054;

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
                totalEdges++;
            }
            i++;
        } while (totalEdges < (vertices.size() - 1));

        for (Edge e : edges) {
            if (e.isChosen()){
                MST.add(e);
            }
        }

        Graph g = new Graph(vertices, MST);

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

        for (Traffic t : traffics) {
            Stack<Vertex> path = new Stack<>();
            path = findPath(g, t.getSrc(), t.getDest());
            t.setHops(path.size() - 1);
            addLoad(MST, path, t);
        }

        for (Edge e : MST) {
            e.setLoad(e.getLoad() * 2);
            e.setUtilization(e.getLoad() / LINE_CAPACITY);
        }
        output(MST, traffics);
    }

    private static void output(List<Edge> edges, List<Traffic> traffic) {
        double totalWeight = 0.0;
        double totalUtilization = 0;
        double maxUtilization = 0;
        double averageUtilization = 0.0;
        double totalTrafficHops = 0.0;
        double totalTraffic = 0.0;
        double averageHops = 0.0;
        double averageDelay = 0.0;

        System.out.println("========================= Minimum Spanning Tree =========================");
        System.out.format("%12s%16s%15s%16s%16s", "Source", "Destination", "Weight(Km)", "Load(Kbps)", "Utilization");
        System.out.println();
        for (Edge e : edges) {
            totalWeight += e.getDistance();
            totalUtilization += e.getUtilization();
            if (e.getUtilization() > maxUtilization)
                maxUtilization = e.getUtilization();
            System.out.format("%12d%16d%15.3f%16d%15.2f%-1s", e.getSrc().getId(), e.getDest().getId(),
                    e.getDistance(), e.getLoad(), e.getUtilization()*100, "%");
            System.out.println();
        }
        averageUtilization = totalUtilization / edges.size();
        System.out.format("%-10s%6.3f", "MST Weight= ", totalWeight);
        System.out.println();
        System.out.format("%-15s%5.2f%-1s", "Max Utilization= ", maxUtilization * 100, "%");
        System.out.println();
        System.out.format("%-19s%5.2f%-1s", "Average Utilization= ", averageUtilization * 100, "%");
        System.out.println();
        System.out.println();
        System.out.println("======================= Traffic Table =======================");
        System.out.format("%12s%16s%22s%10s", "Source", "Destination", "Data Rate(Kbps)", "Hops");
        System.out.println();
        for (Traffic t : traffic) {
            totalTrafficHops += (t.getLoad() * t.getHops());
            totalTraffic += t.getLoad();
            System.out.format("%12d%16d%22d%10d", t.getSrc().getId(), t.getDest().getId(),
                    t.getLoad(), t.getHops());
            System.out.println();
        }
        averageHops = totalTrafficHops / totalTraffic;
        System.out.format("%-14s%4.2f", "Average Hops= ", averageHops);
        System.out.println();
        System.out.format("%-7s%5.4f%24s%5.2f%24s%6.2f%-1s", "TBar =", Tbar, "seconds; Average Hops =",
                averageHops, "hops; Max Utilization =", maxUtilization * 100, "%");
        System.out.println();
        averageDelay = (Tbar * averageHops) / (1 - maxUtilization);
        System.out.format("%-15s%6.3f%8s", "Average Delay =", averageDelay, "seconds");
        System.out.println();
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
