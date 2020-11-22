package Model;

public class Move {
    private int x;
    private int y;
    private int figure;

    public Move() {}

    public Move(int x, int y, int figure) {
        this.x = x;
        this.y = y;
        this.figure = figure;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFigure() {
        return figure;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }
}
