package project2.cs6591;

public class Edge implements Comparable<Edge>{
    private Vertex src, dest;
    private double distance;
    private boolean chosen;

    public Edge (Vertex v1, Vertex v2) {
        src = v1;
        dest = v2;
        distance = Math.sqrt(Math.pow(v1.getXcord() - v2.getXcord(), 2) +
                Math.pow(v1.getYcord() - v2.getYcord(), 2));
        chosen = false;
    }

    public Vertex getSrc() {
        return src;
    }

    public void setSrc(Vertex src) {
        this.src = src;
    }

    public Vertex getDest() {
        return dest;
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

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.distance, o.getDistance());
    }
}
