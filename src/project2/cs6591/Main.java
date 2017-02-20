package project2.cs6591;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static List<Vertex> vertices = new ArrayList<>();
    public static List<Edge> edges = new ArrayList<>();
    public static int totalEdges = 0;

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
                totalWeight += e.getDistance();
                System.out.println(e.getSrc().getId() + " " + e.getDest().getId() + " " + Math.round(e.getDistance() * 1000d) / 1000d);
            }
        }

        System.out.println("MST Weight = " + Math.round(totalWeight * 1000d) / 1000d);
    }
}
