package project2.cs6591;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Vertex> vertices = new ArrayList<>();
    public static List<Edge> edges = new ArrayList<>();
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
        for (Edge e : edges) {
            System.out.println(e.getSrc() + " " + e.getDest() + " " + Math.round(e.getDistance() * 1000d) / 1000d);
        }
    }
}
