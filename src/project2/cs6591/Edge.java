package project2.cs6591;

public class Edge implements Comparable<Edge>{
    private Vertex src, dest;
    private double distance;
    private boolean chosen;
    private int load;
    private double utilization;

    public Edge (Vertex v1, Vertex v2) {
        src = v1;
        dest = v2;
        distance = Math.sqrt(Math.pow(v1.getXcord() - v2.getXcord(), 2) +
                Math.pow(v1.getYcord() - v2.getYcord(), 2));
        chosen = false;
        load = 0;
        utilization = 0.0;
    }

    public Vertex getSrc() {
        return src;
    }

    public Vertex getDest() {
        return dest;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public double getUtilization() {
        return utilization;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.distance, o.getDistance());
    }
}
