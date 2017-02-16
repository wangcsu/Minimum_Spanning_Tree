package project2.cs6591;

public class Edge {
    private Vertex src, dest;
    private double distance;

    public Edge (Vertex v1, Vertex v2) {
        src = v1;
        dest = v2;
        distance = Math.sqrt(Math.pow(v1.getXcord() - v2.getXcord(), 2) +
                Math.pow(v1.getYcord() - v2.getYcord(), 2));
    }

    public int getSrc() {
        return src.getId();
    }

    public void setSrc(Vertex src) {
        this.src = src;
    }

    public int getDest() {
        return dest.getId();
    }

    public void setDest(Vertex dest) {
        this.dest = dest;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
