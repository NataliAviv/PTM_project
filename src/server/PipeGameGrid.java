package server;

import java.util.Objects;

public class PipeGameGrid {
    int x;
    int y;
    char value;
    int rotations;

    public int getRotations() {
        return rotations;
    }
    public PipeGameGrid(int x, int y, char value, int rotations) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.rotations = rotations;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public char getValue() {
        return value;
    }
    public String toString() {
        return x + "," + y + " , " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PipeGameGrid that = (PipeGameGrid) o;
        return x == that.x &&
                y == that.y &&
                value == that.value &&
                rotations == that.rotations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, value, rotations);
    }
}
