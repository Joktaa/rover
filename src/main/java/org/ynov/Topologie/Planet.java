package org.ynov.Topologie;

//Object valeur
public class Planet {
    private final int x_size;
    private final int y_size;

    public Planet(int x, int y) {
       x_size = x;
       y_size = y;
    }

    public int getX_size() {
        return x_size;
    }

    public int getY_size() {
        return y_size;
    }
}
