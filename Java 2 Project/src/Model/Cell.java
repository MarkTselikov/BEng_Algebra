package Model;

import javafx.scene.layout.Pane;

import java.io.Serializable;

public class Cell extends Pane implements Serializable {

    private int x;
    private int y;
    private boolean isOccupied;

    public void initCell(int x, int y) {

        this.x = x;
        this.y = y;
        isOccupied = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setOccupied(boolean state) {
        isOccupied = state;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
