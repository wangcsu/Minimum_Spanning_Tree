package project2.cs6591;

public class Vertex {
    private int id;
    private int xcord, ycord;
    private int componentId;
    // private boolean isconnected;


    public Vertex() {}

    public Vertex(int id, int xcord, int ycord) {
        // this.isconnected = false;
        this.id = id;
        this.xcord = xcord;
        this.ycord = ycord;
        this.componentId = id;
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

    /*public boolean isIsconnected() {
        return isconnected;
    }

    public void setIsconnected(boolean isconnected) {
        this.isconnected = isconnected;
    }*/

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }
}
