package org.ynov.shared;

public class Position {
    public int X;
    public int Y;

    public Position() {
        X = 0;
        Y = 0;
    }

    public String toString(){
        return "My position is : X = " + this.X + " Y = " + this.Y;
    }
}
