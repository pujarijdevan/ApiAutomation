package models.request;



public class Destination {

    private int x;
    private int y;

    /**
     * No args constructor for use in serialization
     *
     */
    public Destination() {
    }

    /**
     *
     * @param x
     * @param y
     */
    public Destination(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}