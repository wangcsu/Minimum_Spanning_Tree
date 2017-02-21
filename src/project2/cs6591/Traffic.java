package project2.cs6591;

/**
 * Created by Ge Wang on 2/20/2017.
 */
public class Traffic {
    private Vertex src;
    private Vertex dest;
    private int load;

    public Traffic(Vertex src, Vertex dest, int load) {
        this.src = src;
        this.dest = dest;
        this.load = load;
    }

    public Vertex getSrc() {
        return src;
    }

    public Vertex getDest() {
        return dest;
    }

    public int getLoad() {
        return load;
    }
}
