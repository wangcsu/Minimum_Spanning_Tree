package project2.cs6591;

public class Vertex {
    private int id;
    private int xcord, ycord;

    public Vertex(int id, int xcord, int ycord) {
        this.id = id;
        this.xcord = xcord;
        this.ycord = ycord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getXcord() {
        return xcord;
    }

    public void setXcord(int xcord) {
        this.xcord = xcord;
    }

    public int getYcord() {
        return ycord;
    }

    public void setYcord(int ycord) {
        this.ycord = ycord;
    }
}
