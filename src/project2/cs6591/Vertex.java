package project2.cs6591;

public class Vertex {
    private int id;
    private int xcord, ycord;
    private int componentId;

    public Vertex() {}

    public Vertex(int id, int xcord, int ycord) {
        this.id = id;
        this.xcord = xcord;
        this.ycord = ycord;
        this.componentId = id;
    }

    public int getId() {
        return id;
    }

    public int getXcord() {
        return xcord;
    }

    public int getYcord() {
        return ycord;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }
}
